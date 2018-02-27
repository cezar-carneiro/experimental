package com.oproom.http.endpoint.device;

import android.content.Context;

import com.koushikdutta.async.http.server.AsyncHttpServerRequest;
import com.koushikdutta.async.http.server.AsyncHttpServerResponse;
import com.oproom.http.endpoint.BaseEndpoint;
import com.oproom.model.DeviceInfo;
import com.oproom.service.impl.DeviceService;

/**
 * Created by Cezar Carneiro on 25/1/2018.
 */

public class DeviceEndpoint extends BaseEndpoint {

    private DeviceService mService;

    public DeviceEndpoint(Context mContext) {
        super(mContext);
        this.mService = new DeviceService(mContext);
    }

    @Override
    protected void handleRequest(AsyncHttpServerRequest request, AsyncHttpServerResponse response) throws Exception {
        DeviceInfo di = mService.getDeviceInfo();
        success(response, di);
    }

}
