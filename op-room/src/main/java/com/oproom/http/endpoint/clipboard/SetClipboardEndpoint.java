package com.oproom.http.endpoint.clipboard;

import android.content.Context;

import com.koushikdutta.async.http.server.AsyncHttpServerRequest;
import com.koushikdutta.async.http.server.AsyncHttpServerResponse;
import com.oproom.http.endpoint.BaseEndpoint;
import com.oproom.model.Clipboard;
import com.oproom.service.ClipboardService;

/**
 * Created by Cezar Carneiro on 24/1/2018.
 */

public class SetClipboardEndpoint extends BaseEndpoint {

    private ClipboardService mService;

    public SetClipboardEndpoint(Context context) {
        super(context);
        this.mService = new ClipboardService(context);
    }

    @Override
    protected void handleRequest(AsyncHttpServerRequest request, AsyncHttpServerResponse response) throws Exception {
        Clipboard clipboard = getEntity(request, Clipboard.class);
        mService.setClipboard(clipboard);
        success(response, "Successfully set.");
    }

}
