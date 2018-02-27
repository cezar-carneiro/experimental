package com.oproom.service.impl;

import android.content.Context;
import android.os.Build;

import com.oproom.model.DeviceInfo;
import com.oproom.model.Stats;
import com.oproom.service.BaseService;
import com.oproom.service.IDeviceService;

/**
 * Created by Cezar Carneiro on 16/1/2018.
 */

public class DeviceService extends BaseService implements IDeviceService {

    public DeviceService(Context context) {
        super(context);
    }

    public DeviceInfo getDeviceInfo() {
        DeviceInfo di = new DeviceInfo();
        di.setBrand(Build.BRAND);
        di.setDevice(Build.DEVICE);
        di.setManufacturer(Build.MANUFACTURER);
        di.setModel(Build.MODEL);
        di.setProduct(Build.PRODUCT);
        di.setCodename( Build.VERSION.CODENAME);
        di.setRelease(Build.VERSION.RELEASE);
        di.setSdk(Build.VERSION.SDK_INT);

        return di;
    }

    public Stats getLiveStats() {
        final Runtime runtime = Runtime.getRuntime();

        long totalMemory = runtime.totalMemory();
        long maxMemory = runtime.maxMemory();
        long freeMemory = runtime.freeMemory();
        int cpus = runtime.availableProcessors();

        Stats stats = new Stats();
        stats.setTotalMemory(totalMemory);
        stats.setMaxMemory(maxMemory);
        stats.setFreeMemory(freeMemory);
        stats.setCpus(cpus);

        return stats;
    }
}
