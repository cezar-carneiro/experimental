package com.oproom.http.handler.stats;

import android.content.Context;

import com.oproom.http.handler.BaseHandler;
import com.oproom.model.Stats;
import com.oproom.service.impl.DeviceService;

import java.util.Map;

import fi.iki.elonen.NanoHTTPD;
import fi.iki.elonen.router.RouterNanoHTTPD;

/**
 * Created by Cezar Carneiro on 16/1/2018.
 */

public class StatsHandler extends BaseHandler {

    private DeviceService mService;

    public StatsHandler(Context context) {
        super(context);
        mService = new DeviceService(context);
    }

    @Override
    public NanoHTTPD.Response get(RouterNanoHTTPD.UriResource uriResource, Map<String, String> urlParams, NanoHTTPD.IHTTPSession session) {
        Stats stats = mService.getLiveStats();
        return success(stats);
    }

}
