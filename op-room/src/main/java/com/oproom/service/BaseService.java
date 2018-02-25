package com.oproom.service;

import android.content.Context;

/**
 * Created by Cezar Carneiro on 16/1/2018.
 */

public class BaseService {

    protected Context mContext;

    public BaseService(Context context) {
        this.mContext = context;
    }
}
