package com.oproom;

import android.content.Context;

import com.koushikdutta.async.http.server.AsyncHttpServer;
import com.oproom.http.endpoint.clipboard.ClipboardEndpoint;
import com.oproom.http.endpoint.clipboard.SetClipboardEndpoint;
import com.oproom.http.endpoint.device.DeviceEndpoint;
import com.oproom.http.endpoint.filesystem.DirectoryEndpoint;
import com.oproom.http.endpoint.filesystem.FileDownloadEndpoint;
import com.oproom.http.endpoint.filesystem.FileUploadEndpoint;
import com.oproom.http.endpoint.heartbeat.HeartbeatEndpoint;
import com.oproom.http.endpoint.preferences.DeletePreferenceEndpoint;
import com.oproom.http.endpoint.preferences.PreferencesEndpoint;
import com.oproom.http.endpoint.preferences.PutPreferenceEndpoint;
import com.oproom.http.endpoint.stats.StatsEndpoint;

public class OpRoom {

    private OpRoom() {
    }

    public static Server init(Context context) {
        return new Server(context);
    }

    public static class Server {

        private static final int DEFAULT_PORT = 5000;

        private Context mContext;
        private AsyncHttpServer mServer;
        private int port;

        public Server(Context mContext) {
            this.mContext = mContext;
            this.mServer = new AsyncHttpServer();
        }

        public Server withDefaults() {
            heartbeat();
            device();
            stats();
            prefs();
            clipboard();
            filesystem();

            port(DEFAULT_PORT);

            return this;
        }

        public Server port (int port) {
            this.port = port;
            return this;
        }

        public Server heartbeat() {
            this.mServer.addAction("GET", "/", new HeartbeatEndpoint(mContext));
            return this;
        }

        public Server device() {
            this.mServer.addAction("GET", "/device", new DeviceEndpoint(mContext));
            return this;
        }

        public Server stats() {
            this.mServer.addAction("GET","/stats", new StatsEndpoint(mContext));
            return this;
        }

        public Server prefs() {
            this.mServer.addAction("GET", "/prefs", new PreferencesEndpoint(mContext));
            this.mServer.addAction("PUT", "/prefs", new PutPreferenceEndpoint(mContext));
            this.mServer.addAction("DELETE", "/prefs", new DeletePreferenceEndpoint(mContext));
            return this;
        }

        public Server clipboard() {
            this.mServer.addAction("GET", "/clipboard", new ClipboardEndpoint(mContext));
            this.mServer.addAction("POST", "/clipboard", new SetClipboardEndpoint(mContext));
            return this;
        }

        public Server filesystem() {
            this.mServer.addAction("GET", "/file", new DirectoryEndpoint(mContext));
            this.mServer.addAction("GET", "/download", new FileDownloadEndpoint(mContext));
            this.mServer.addAction("POST", "/upload", new FileUploadEndpoint(mContext));
            return this;
        }

        public void start() {
            this.mServer.listen(port);
        }

    }

}
