package cn.ingxin.emoticons.emoticons.utils;

import android.util.Log;

public class XELogger {

    private static final String TAG = "emoticons";
    private static final boolean LOG_ENABLE = true;

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
