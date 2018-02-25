package com.oproom.service;

import android.content.Context;
import android.content.SharedPreferences;

import com.oproom.model.PreferenceType;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Cezar Carneiro on 16/1/2018.
 */

public class PreferencesService extends com.oproom.service.BaseService {

    public PreferencesService(Context context) {
        super(context);
    }

    public List<String> getStorages(){
        ArrayList<String> tags = new ArrayList<>();

        String rootPath = mContext.getApplicationInfo().dataDir + "/shared_prefs";
        File root = new File(rootPath);
        if (root.exists()) {
            for (File file : root.listFiles()) {
                String fileName = file.getName();
                if (fileName.endsWith(".xml")) {
                    tags.add(fileName.substring(0, fileName.length() - 4)); // 4 because of ".xml"
                }
            }
        }

        Collections.sort(tags);

        return tags;
    }

    public Map<String, ?> getValues(String storage) {
        SharedPreferences pref = mContext.getSharedPreferences(storage, 0); // 0 - for private mode
        Map<String, ?> all = pref.getAll();
        return all;
    }

    public boolean deleteValue(String storage, String key) {
        SharedPreferences pref = mContext.getSharedPreferences(storage, 0); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();
        editor.remove(key);
        return editor.commit();
    }

    public boolean putValue(String storage, String key, PreferenceType type, String value) {
        SharedPreferences pref = mContext.getSharedPreferences(storage, 0); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();

        switch (type) {
            case STRING:
                editor.putString(key, value);
                break;
            case BOOLEAN:
                editor.putBoolean(key, Boolean.valueOf(value));
                break;
            case FLOAT:
                editor.putFloat(key, Float.valueOf(value));
                break;
            case INT:
                editor.putInt(key, Integer.valueOf(value));
                break;
            case LONG:
                editor.putLong(key, Long.valueOf(value));
                break;
        }
        return editor.commit();

    }

    public boolean putStringSet(String storage, String key, Set<String> value) {
        SharedPreferences pref = mContext.getSharedPreferences(storage, 0); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();
        editor.putStringSet(key, value);
        return editor.commit();
    }

}
