package com.oproom.http.handler.clipboard;

import android.content.Context;

import com.koushikdutta.async.http.server.AsyncHttpServerRequest;
import com.koushikdutta.async.http.server.AsyncHttpServerResponse;
import com.oproom.http.handler.BaseHandler;
import com.oproom.model.Clipboard;
import com.oproom.service.IClipboardService;
import com.oproom.service.impl.ClipboardService;

import java.util.Map;

import fi.iki.elonen.NanoHTTPD;
import fi.iki.elonen.router.RouterNanoHTTPD;

/**
 * Created by Cezar Carneiro on 23/1/2018.
 */

public class ClipboardHandler extends BaseHandler {

    private IClipboardService mService;

    public ClipboardHandler(Context context) {
        super(context);
        this.mService = new ClipboardService(context);
    }

    @Override
    public NanoHTTPD.Response get(RouterNanoHTTPD.UriResource uriResource, Map<String, String> urlParams, NanoHTTPD.IHTTPSession session) {
        Clipboard clipboard = mService.getClipboard();
        return success(clipboard);
    }

    @Override
    public NanoHTTPD.Response post(RouterNanoHTTPD.UriResource uriResource, Map<String, String> urlParams, NanoHTTPD.IHTTPSession session) {
        Clipboard clipboard = getEntity(request, Clipboard.class);
        mService.setClipboard(clipboard);
        return success("Successfully set.");
    }
}
