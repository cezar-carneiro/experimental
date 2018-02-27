package com.oproom.http.endpoint.stats;

import android.content.Context;

import com.koushikdutta.async.http.server.AsyncHttpServerRequest;
import com.koushikdutta.async.http.server.AsyncHttpServerResponse;
import com.oproom.http.endpoint.BaseEndpoint;
import com.oproom.model.Stats;
import com.oproom.service.impl.DeviceService;

/**
 * Created by Cezar Carneiro on 16/1/2018.
 */

public class StatsEndpoint extends BaseEndpoint {

    private DeviceService mService;

    public StatsEndpoint(Context context) {
        super(context);
        mService = new DeviceService(context);
    }

    @Override
    protected void handleRequest(AsyncHttpServerRequest request, AsyncHttpServerResponse response) {
        Stats stats = mService.getLiveStats();
        success(response, stats);
    }

}
