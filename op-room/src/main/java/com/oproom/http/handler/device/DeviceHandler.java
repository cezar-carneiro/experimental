package com.oproom.http.handler.device;

import android.content.Context;

import com.oproom.http.handler.BaseHandler;
import com.oproom.model.DeviceInfo;
import com.oproom.service.impl.DeviceService;

import java.util.Map;

import fi.iki.elonen.NanoHTTPD;
import fi.iki.elonen.router.RouterNanoHTTPD;

/**
 * Created by Cezar Carneiro on 25/1/2018.
 */

public class DeviceHandler extends BaseHandler {

    private DeviceService mService;

    public DeviceHandler(Context mContext) {
        super(mContext);
        this.mService = new DeviceService(mContext);
    }

    @Override
    public NanoHTTPD.Response get(RouterNanoHTTPD.UriResource uriResource, Map<String, String> urlParams, NanoHTTPD.IHTTPSession session) {
        DeviceInfo di = mService.getDeviceInfo();
        return success(di);
    }

}
