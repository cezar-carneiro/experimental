package com.oproom.http.handler.heartbeat;

import android.content.Context;

import com.oproom.http.handler.BaseHandler;

import java.util.Map;

import fi.iki.elonen.NanoHTTPD;
import fi.iki.elonen.router.RouterNanoHTTPD;

/**
 * Created by Cezar Carneiro on 16/1/2018.
 */

public class HeartbeatHandler extends BaseHandler {

    public HeartbeatHandler(Context mContext) {
        super(mContext);
    }

    @Override
    public NanoHTTPD.Response get(RouterNanoHTTPD.UriResource uriResource, Map<String, String> urlParams, NanoHTTPD.IHTTPSession session) {
        return success("Server is up and running.");
    }

}
