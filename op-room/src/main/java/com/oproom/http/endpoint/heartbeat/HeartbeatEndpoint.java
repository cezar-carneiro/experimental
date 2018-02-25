package com.oproom.http.endpoint.heartbeat;

import android.content.Context;

import com.koushikdutta.async.http.server.AsyncHttpServerRequest;
import com.koushikdutta.async.http.server.AsyncHttpServerResponse;
import com.oproom.http.endpoint.BaseEndpoint;

/**
 * Created by Cezar Carneiro on 16/1/2018.
 */

public class HeartbeatEndpoint extends BaseEndpoint {

    public HeartbeatEndpoint(Context mContext) {
        super(mContext);
    }

    @Override
    protected void handleRequest(AsyncHttpServerRequest request, AsyncHttpServerResponse response) {
        success(response,"Server is up and running.");
    }
}
