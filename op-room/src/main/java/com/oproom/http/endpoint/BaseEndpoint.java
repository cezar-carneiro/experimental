package com.oproom.http.endpoint;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.koushikdutta.async.http.server.AsyncHttpServerRequest;
import com.koushikdutta.async.http.server.AsyncHttpServerResponse;
import com.koushikdutta.async.http.server.HttpServerRequestCallback;
import com.oproom.http.DefaultResponse;
import com.oproom.http.Http;

/**
 * Created by Cezar Carneiro on 16/1/2018.
 */

public abstract class BaseEndpoint implements HttpServerRequestCallback {

    protected Context mContext;

    private Gson gson;

    public BaseEndpoint(Context mContext) {
        this.mContext = mContext;
        this.gson = new Gson();
    }

    @Override
    final public void onRequest(AsyncHttpServerRequest request, AsyncHttpServerResponse response) {
        try {
            handleRequest(request, response);
        } catch (Exception e){
            Log.e("OP-ROOM", "Error processing request.", e);
            internalError(response, e);
        }
    }

    protected abstract void handleRequest(AsyncHttpServerRequest request, AsyncHttpServerResponse response) throws Exception;

    protected void success(AsyncHttpServerResponse response){
        success(response, "Success");
    }

    protected void success(AsyncHttpServerResponse response, String message){
        success(response, new DefaultResponse(true, message));
    }

    protected void success(AsyncHttpServerResponse response, Object body){
        response.send(Http.CONTENT_TYPE_JSON, buildResponse(body));
    }

    protected byte[] buildResponse(Object response) {
        return gson.toJson(response).getBytes();
    }

    protected <T> T getEntity(AsyncHttpServerRequest req, Class<T> clazz) {
        return gson.fromJson(req.getBody().get().toString(), clazz);
    }

    protected void badRequest(AsyncHttpServerResponse response) {
        badRequest(response, Http.BAD_REQUEST_MESSAGE);
    }

    protected void badRequest(AsyncHttpServerResponse response, String message) {
        response.code(Http.BAD_REQUEST_CODE)
                .send(Http.CONTENT_TYPE_JSON,
                        buildResponse(new DefaultResponse(false, message)));
    }

    protected void notFound(AsyncHttpServerResponse response) {
        notFound(response, Http.NOT_FOUND_MESSAGE);
    }

    protected void notFound(AsyncHttpServerResponse response, String message) {
        response.code(Http.NOT_FOUND_CODE)
                .send(Http.CONTENT_TYPE_JSON,
                        buildResponse(new DefaultResponse(false, message)));
    }

    protected void internalError(AsyncHttpServerResponse response, Exception e){
        response.code(Http.INTERNAL_ERROR_CODE)
                .send(Http.CONTENT_TYPE_JSON,
                        buildResponse(new DefaultResponse(false, e.getMessage())));
    }
}
