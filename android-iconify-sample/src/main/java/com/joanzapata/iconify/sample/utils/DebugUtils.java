package com.joanzapata.iconify.sample.utils;

/**
 * Created by baurine on 4/10/16.
 */
public class DebugUtils {

    public static void assertFalse(boolean condition) {
        if (!condition) {
            throw new RuntimeException("assert false");
        }
    }

}
