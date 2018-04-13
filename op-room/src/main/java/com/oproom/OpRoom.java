package com.oproom;

import android.content.Context;
import android.util.Log;

import com.oproom.http.Router;

import java.io.IOException;

public class OpRoom {

    private static final int DEFAULT_PORT = 5000;

    private OpRoom() {
    }

    public static Server init(Context context) {
        return new Server(context, DEFAULT_PORT);
    }

    public static Server init(Context context, int port) {
        return new Server(context, port);
    }

    public static class Server {

        private Context mContext;
        private Router mServer;

        public Server(Context context, int port) {
            this.mContext = context;
            this.mServer = new Router(port);
        }

        public Server withDefaults() {
            mServer.addMappings();
            return this;
        }

        public void start() {
            try {
                this.mServer.start();
            } catch (IOException e) {
                Log.e("OP ROOM", "Error starting http server.", e);
            }
        }

    }

}
