package com.oproom.http.handler;

import android.content.Context;

import com.google.gson.Gson;

import java.util.Map;

import fi.iki.elonen.NanoHTTPD;
import fi.iki.elonen.router.RouterNanoHTTPD;

/**
 * Created by Cezar Carneiro on 16/1/2018.
 */

public class BaseHandler implements RouterNanoHTTPD.UriResponder {

    static private final String MIME_JSON = "application/json";
    static private final String MIME_TEXT = "text/plain";

    protected Context mContext;

    private Gson gson;

    public BaseHandler(Context mContext) {
        this.mContext = mContext;
        this.gson = new Gson();
    }

    @Override
    public NanoHTTPD.Response get(RouterNanoHTTPD.UriResource uriResource, Map<String, String> urlParams, NanoHTTPD.IHTTPSession session) {
        throw new UnsupportedOperationException("GET request not supported.");
    }

    @Override
    public NanoHTTPD.Response put(RouterNanoHTTPD.UriResource uriResource, Map<String, String> urlParams, NanoHTTPD.IHTTPSession session) {
        throw new UnsupportedOperationException("PUT request not supported.");
    }

    @Override
    public NanoHTTPD.Response post(RouterNanoHTTPD.UriResource uriResource, Map<String, String> urlParams, NanoHTTPD.IHTTPSession session) {
        throw new UnsupportedOperationException("POST request not supported.");
    }

    @Override
    public NanoHTTPD.Response delete(RouterNanoHTTPD.UriResource uriResource, Map<String, String> urlParams, NanoHTTPD.IHTTPSession session) {
        throw new UnsupportedOperationException("DELETE request not supported.");
    }

    @Override
    public NanoHTTPD.Response other(String method, RouterNanoHTTPD.UriResource uriResource, Map<String, String> urlParams, NanoHTTPD.IHTTPSession session) {
        throw new UnsupportedOperationException("Request not supported.");
    }

    protected NanoHTTPD.Response success(String body){
        return NanoHTTPD.newFixedLengthResponse(NanoHTTPD.Response.Status.OK,
                MIME_TEXT, body);
    }

    protected NanoHTTPD.Response success(Object body){
        return NanoHTTPD.newFixedLengthResponse(NanoHTTPD.Response.Status.OK,
                MIME_JSON, gson.toJson(body));
    }

//
//    protected byte[] buildResponse(Object response) {
//        return gson.toJson(response).getBytes();
//    }
//
//    protected <T> T getEntity(AsyncHttpServerRequest req, Class<T> clazz) {
//        return gson.fromJson(req.getBody().get().toString(), clazz);
//    }
//
//    protected void badRequest(AsyncHttpServerResponse response) {
//        badRequest(response, Http.BAD_REQUEST_MESSAGE);
//    }
//
//    protected void badRequest(AsyncHttpServerResponse response, String message) {
//        response.code(Http.BAD_REQUEST_CODE)
//                .send(Http.CONTENT_TYPE_JSON,
//                        buildResponse(new DefaultResponse(false, message)));
//    }
//
//    protected void notFound(AsyncHttpServerResponse response) {
//        notFound(response, Http.NOT_FOUND_MESSAGE);
//    }
//
//    protected void notFound(AsyncHttpServerResponse response, String message) {
//        response.code(Http.NOT_FOUND_CODE)
//                .send(Http.CONTENT_TYPE_JSON,
//                        buildResponse(new DefaultResponse(false, message)));
//    }
//
//    protected void internalError(AsyncHttpServerResponse response, Exception e){
//        response.code(Http.INTERNAL_ERROR_CODE)
//                .send(Http.CONTENT_TYPE_JSON,
//                        buildResponse(new DefaultResponse(false, e.getMessage())));
//    }
}
