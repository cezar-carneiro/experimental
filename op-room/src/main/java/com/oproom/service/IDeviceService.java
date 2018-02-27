package com.oproom.service;

import com.oproom.model.DeviceInfo;
import com.oproom.model.Stats;

/**
 * Created by Cezar Carneiro on 27/02/2018.
 */

public interface IDeviceService {

    DeviceInfo getDeviceInfo();
    Stats getLiveStats();

}
