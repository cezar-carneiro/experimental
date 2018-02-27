package com.oproom.http.endpoint.preferences;

import android.content.Context;

import com.koushikdutta.async.http.server.AsyncHttpServerRequest;
import com.koushikdutta.async.http.server.AsyncHttpServerResponse;
import com.oproom.http.DefaultResponse;
import com.oproom.http.Http;
import com.oproom.http.endpoint.BaseEndpoint;
import com.oproom.model.Preference;
import com.oproom.service.impl.PreferencesService;

/**
 * Created by Cezar Carneiro on 22/1/2018.
 */

public class DeletePreferenceEndpoint extends BaseEndpoint {

    private PreferencesService mService;

    public DeletePreferenceEndpoint(Context context) {
        super(context);
        this.mService = new PreferencesService(context);
    }

    @Override
    protected void handleRequest(AsyncHttpServerRequest request, AsyncHttpServerResponse response) throws Exception {
        Preference pref = getEntity(request, Preference.class);

        Boolean status = mService.deleteValue(pref.getStorage(), pref.getKey());
        String msg = status ? "Successfuly deleted" : "Error deleting preference value";

        response.send(Http.CONTENT_TYPE_JSON, buildResponse(new DefaultResponse(status, msg)));
    }

}
