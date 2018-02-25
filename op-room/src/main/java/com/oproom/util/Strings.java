package com.oproom.util;

/**
 * Created by Cezar Carneiro on 22/1/2018.
 */

public class Strings {

    public static boolean isEmpty(String str){
        return str == null || str.length() == 0;
    }

    public static boolean isNotEmpty(String str){
        return str != null && str.length() > 0;
    }
}
