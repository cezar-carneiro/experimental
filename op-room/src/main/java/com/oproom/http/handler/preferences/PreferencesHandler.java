package com.oproom.http.handler.preferences;

import android.content.Context;

import com.koushikdutta.async.http.Multimap;
import com.oproom.http.DefaultResponse;
import com.oproom.http.Http;
import com.oproom.http.handler.BaseHandler;
import com.oproom.model.Preference;
import com.oproom.service.impl.PreferencesService;
import com.oproom.util.Strings;

import java.util.Map;

import fi.iki.elonen.NanoHTTPD;
import fi.iki.elonen.router.RouterNanoHTTPD;

/**
 * Created by Cezar Carneiro on 16/1/2018.
 */

public class PreferencesHandler extends BaseHandler {

    private PreferencesService mService;

    public PreferencesHandler(Context context) {
        super(context);
        this.mService = new PreferencesService(context);
    }

    @Override
    public NanoHTTPD.Response get(RouterNanoHTTPD.UriResource uriResource, Map<String, String> urlParams, NanoHTTPD.IHTTPSession session) {
        Object responseBody = null;

        String storage = urlParams.get("storage");
        if(Strings.isNotEmpty(storage)){
            responseBody = mService.getValues(storage);
        } else {
            responseBody = mService.getStorages();
        }

        response.send(Http.CONTENT_TYPE_JSON, buildResponse(responseBody));
        return super.get(uriResource, urlParams, session);
    }

    @Override
    public NanoHTTPD.Response put(RouterNanoHTTPD.UriResource uriResource, Map<String, String> urlParams, NanoHTTPD.IHTTPSession session) {
        Preference pref = getEntity(request, Preference.class);

        Boolean status = mService.putValue(pref.getStorage(), pref.getKey(), pref.getType(), pref.getValue());
        String msg = status ? "Successfuly put" : "Error putting preference value";

        response.send(Http.CONTENT_TYPE_JSON, buildResponse(new DefaultResponse(status, msg)));

        return super.put(uriResource, urlParams, session);
    }

    @Override
    public NanoHTTPD.Response delete(RouterNanoHTTPD.UriResource uriResource, Map<String, String> urlParams, NanoHTTPD.IHTTPSession session) {
        Preference pref = getEntity(request, Preference.class);

        Boolean status = mService.deleteValue(pref.getStorage(), pref.getKey());
        String msg = status ? "Successfuly deleted" : "Error deleting preference value";

        response.send(Http.CONTENT_TYPE_JSON, buildResponse(new DefaultResponse(status, msg)));

        return super.delete(uriResource, urlParams, session);
    }
}
