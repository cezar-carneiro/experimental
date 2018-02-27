package com.oproom.service;

import com.oproom.model.PreferenceType;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Cezar Carneiro on 27/02/2018.
 */

public interface IPreferenceService {

    List<String> getStorages();
    Map<String, ?> getValues(String storage);
    boolean deleteValue(String storage, String key);
    boolean putValue(String storage, String key, PreferenceType type, String value);
    boolean putStringSet(String storage, String key, Set<String> value);

}
