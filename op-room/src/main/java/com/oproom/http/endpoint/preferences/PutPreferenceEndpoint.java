package com.oproom.http.endpoint.preferences;

import android.content.Context;

import com.koushikdutta.async.http.server.AsyncHttpServerRequest;
import com.koushikdutta.async.http.server.AsyncHttpServerResponse;
import com.oproom.http.DefaultResponse;
import com.oproom.http.Http;
import com.oproom.http.endpoint.BaseEndpoint;
import com.oproom.model.Preference;
import com.oproom.service.PreferencesService;

/**
 * Created by Cezar Carneiro on 22/1/2018.
 */

public class PutPreferenceEndpoint extends BaseEndpoint{

    private PreferencesService mService;

    public PutPreferenceEndpoint(Context context) {
        super(context);
        this.mService = new PreferencesService(context);
    }

    @Override
    protected void handleRequest(AsyncHttpServerRequest request, AsyncHttpServerResponse response) throws Exception {
        Preference pref = getEntity(request, Preference.class);

        Boolean status = mService.putValue(pref.getStorage(), pref.getKey(), pref.getType(), pref.getValue());
        String msg = status ? "Successfuly put" : "Error putting preference value";

        response.send(Http.CONTENT_TYPE_JSON, buildResponse(new DefaultResponse(status, msg)));
    }
}
