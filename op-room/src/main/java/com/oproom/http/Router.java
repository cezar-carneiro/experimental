package com.oproom.http;

import com.oproom.http.handler.clipboard.ClipboardHandler;
import com.oproom.http.handler.clipboard.SetClipboardHandler;
import com.oproom.http.handler.device.DeviceHandler;
import com.oproom.http.handler.filesystem.DirectoryHandler;
import com.oproom.http.handler.filesystem.FileDownloadHandler;
import com.oproom.http.handler.filesystem.FileUploadHandler;
import com.oproom.http.handler.heartbeat.HeartbeatHandler;
import com.oproom.http.handler.preferences.PreferencesHandler;
import com.oproom.http.handler.stats.StatsHandler;

import fi.iki.elonen.router.RouterNanoHTTPD;

/**
 * Created by Cezar Carneiro on 13/04/2018.
 */

public class Router extends RouterNanoHTTPD {

    public Router(int port) {
        super(port);
        addMappings();
    }

    @Override
    public void addMappings() {
        super.addMappings();
        addRoute("/", HeartbeatHandler.class);
        addRoute("/device", DeviceHandler.class);
        addRoute("/stats", StatsHandler.class);
        addRoute("/prefs/:storage", PreferencesHandler.class);
        addRoute("GET", "/clipboard", new ClipboardHandler(mContext));
        addRoute("POST", "/clipboard", new SetClipboardHandler(mContext));
        addRoute("GET", "/file", new DirectoryHandler(mContext));
        addRoute("GET", "/download", new FileDownloadHandler(mContext));
        addRoute("POST", "/upload", new FileUploadHandler(mContext));
    }
}
