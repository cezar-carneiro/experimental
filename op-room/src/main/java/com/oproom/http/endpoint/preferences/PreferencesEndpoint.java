package com.oproom.http.endpoint.preferences;

import android.content.Context;

import com.koushikdutta.async.http.Multimap;
import com.koushikdutta.async.http.server.AsyncHttpServerRequest;
import com.koushikdutta.async.http.server.AsyncHttpServerResponse;
import com.oproom.http.Http;
import com.oproom.http.endpoint.BaseEndpoint;
import com.oproom.service.PreferencesService;
import com.oproom.util.Strings;

/**
 * Created by Cezar Carneiro on 16/1/2018.
 */

public class PreferencesEndpoint extends BaseEndpoint {

    private PreferencesService mService;

    public PreferencesEndpoint(Context context) {
        super(context);
        this.mService = new PreferencesService(context);
    }

    @Override
    public void handleRequest(AsyncHttpServerRequest request, AsyncHttpServerResponse response) {
        Object responseBody = null;
        Multimap map = request.getQuery();

        String storage = map.getString("storage");
        if(Strings.isNotEmpty(storage)){
            responseBody = mService.getValues(storage);
        } else {
            responseBody = mService.getStorages();
        }

        response.send(Http.CONTENT_TYPE_JSON, buildResponse(responseBody));
    }
}
