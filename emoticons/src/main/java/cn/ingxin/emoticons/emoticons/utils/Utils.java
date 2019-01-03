package cn.ingxin.emoticons.emoticons.utils;

import android.support.annotation.Nullable;

public class Utils {

    public static <T> T checkNotNull(@Nullable T object, String message) {
        if (object == null) {
            throw new NullPointerException(message);
        }
        return object;
    }

}
