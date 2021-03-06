package com.oproom.http.handler.filesystem;

import android.content.Context;

import com.koushikdutta.async.http.Multimap;
import com.koushikdutta.async.http.server.AsyncHttpServerRequest;
import com.koushikdutta.async.http.server.AsyncHttpServerResponse;
import com.oproom.http.handler.BaseHandler;
import com.oproom.service.impl.FileSystemService;
import com.oproom.util.Strings;

import java.io.File;

/**
 * Created by Cezar Carneiro on 25/1/2018.
 */

public class FileDownloadHandler extends BaseHandler {

    private FileSystemService mService;

    public FileDownloadHandler(Context context) {
        super(context);
        this.mService = new FileSystemService(context);
    }

    @Override
    protected void handleRequest(AsyncHttpServerRequest request, AsyncHttpServerResponse response) throws Exception {
        Multimap map = request.getQuery();
        String path = map.getString("path");
        if(Strings.isEmpty(path)){
            badRequest(response, "You need to specify query parameter 'path'");
            return;
        }

        File file = mService.getFile(path);
        response.sendFile(file);
    }
}
