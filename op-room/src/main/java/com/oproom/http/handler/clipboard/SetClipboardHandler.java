package com.oproom.http.handler.clipboard;

import android.content.Context;

import com.koushikdutta.async.http.server.AsyncHttpServerRequest;
import com.koushikdutta.async.http.server.AsyncHttpServerResponse;
import com.oproom.http.handler.BaseHandler;
import com.oproom.model.Clipboard;
import com.oproom.service.impl.ClipboardService;

/**
 * Created by Cezar Carneiro on 24/1/2018.
 */

public class SetClipboardHandler extends BaseHandler {

    private ClipboardService mService;

    public SetClipboardHandler(Context context) {
        super(context);
        this.mService = new ClipboardService(context);
    }

}
