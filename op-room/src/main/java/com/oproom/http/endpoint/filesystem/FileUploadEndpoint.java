package com.oproom.http.endpoint.filesystem;

import android.content.Context;

import com.koushikdutta.async.callback.CompletedCallback;
import com.koushikdutta.async.http.body.MultipartFormDataBody;
import com.koushikdutta.async.http.body.Part;
import com.koushikdutta.async.http.server.AsyncHttpServerRequest;
import com.koushikdutta.async.http.server.AsyncHttpServerResponse;
import com.koushikdutta.async.stream.OutputStreamDataCallback;
import com.oproom.http.endpoint.BaseEndpoint;
import com.oproom.service.impl.FileSystemService;
import com.oproom.util.Holder;
import com.oproom.util.Strings;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by Cezar Carneiro on 27/1/2018.
 *
 * I don't really like this implementation but that's what I can do for now.
 */
public class FileUploadEndpoint extends BaseEndpoint{

    private FileSystemService mService;

    public FileUploadEndpoint(Context context) {
        super(context);
        this.mService = new FileSystemService(context);
    }

    @Override
    protected void handleRequest(AsyncHttpServerRequest request, final AsyncHttpServerResponse response) throws Exception {
        if (!(request.getBody() instanceof MultipartFormDataBody)) {
            badRequest(response);
        }

        final Holder<OutputStream> outStream = new Holder<>();
        final MultipartFormDataBody body = (MultipartFormDataBody) request.getBody();
        body.setMultipartCallback(new MultipartFormDataBody.MultipartCallback() {
            @Override
            public void onPart(Part part) {//really wish i could get an InputStream here
                if (part.isFile() && "file".equals(part.getName())) {
                    String dest = body.getField("dest");
                    if(Strings.isEmpty(dest)){
                        badRequest(response, "No 'dest' received. You need to define 'dest' parameter before 'file' in the request.");
                        return;
                    }

                    try {
                        outStream.held = mService.createNewFileOutputStream(dest, part.getFilename());
                        body.setDataCallback(new OutputStreamDataCallback(outStream.held));
                    } catch (Exception e) {
                        internalError(response, e);
                    }

                }

            }
        });

        request.setEndCallback(new CompletedCallback() {
            @Override
            public void onCompleted(Exception ex) {
                if(ex != null) {
                    internalError(response, ex);
                    return;
                }
                if(outStream.held == null){
                    badRequest(response, "Not file receive. You need to stream a file in the 'file' parameter.");
                    return;
                }

                try {
                    outStream.held.flush();
                    outStream.held.flush();
                } catch (IOException e) {
                    internalError(response, ex);
                } finally {
                    try { outStream.held.close();} catch (IOException e){}
                }

                success(response);
            }
        });
    }
}
