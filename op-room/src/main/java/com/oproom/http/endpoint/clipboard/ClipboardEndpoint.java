package com.oproom.http.endpoint.clipboard;

import android.content.Context;

import com.koushikdutta.async.http.server.AsyncHttpServerRequest;
import com.koushikdutta.async.http.server.AsyncHttpServerResponse;
import com.oproom.http.endpoint.BaseEndpoint;
import com.oproom.model.Clipboard;
import com.oproom.service.ClipboardService;

/**
 * Created by Cezar Carneiro on 23/1/2018.
 */

public class ClipboardEndpoint extends BaseEndpoint {

    private ClipboardService mService;

    public ClipboardEndpoint(Context context) {
        super(context);
        this.mService = new ClipboardService(context);
    }

    @Override
    protected void handleRequest(AsyncHttpServerRequest request, AsyncHttpServerResponse response) throws Exception {
        Clipboard clipboard = mService.getClipboard();
        success(response, clipboard);
    }
}
