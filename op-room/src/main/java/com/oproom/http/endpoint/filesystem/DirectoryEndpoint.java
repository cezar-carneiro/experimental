package com.oproom.http.endpoint.filesystem;

import android.content.Context;

import com.koushikdutta.async.http.Multimap;
import com.koushikdutta.async.http.server.AsyncHttpServerRequest;
import com.koushikdutta.async.http.server.AsyncHttpServerResponse;
import com.oproom.http.endpoint.BaseEndpoint;
import com.oproom.model.DirectoryInfo;
import com.oproom.service.FileSystemService;

/**
 * Created by Cezar Carneiro on 24/1/2018.
 */

public class DirectoryEndpoint extends BaseEndpoint {

    private FileSystemService mService;

    public DirectoryEndpoint(Context context) {
        super(context);
        this.mService = new FileSystemService(context);
    }

    @Override
    protected void handleRequest(AsyncHttpServerRequest request, AsyncHttpServerResponse response) throws Exception {
        Multimap map = request.getQuery();
        String path = map.getString("path");

        DirectoryInfo di = mService.getDir(path);
        success(response, di);
    }
}
