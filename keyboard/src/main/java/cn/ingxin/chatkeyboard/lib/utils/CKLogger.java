package cn.ingxin.chatkeyboard.lib.utils;

import android.util.Log;

import cn.ingxin.chatkeyboard.lib.BuildConfig;

public class CKLogger {

    private static final String TAG = "KEYBOARD";
    private static final boolean LOG_ENABLE = BuildConfig.DEBUG;

    public static void i(String msg) {
        if (LOG_ENABLE) {
            Log.i(TAG, msg);
        }
    }

    public static void e(String msg) {
        if (LOG_ENABLE) {
            Log.e(TAG, msg);
        }
    }

}
