package cn.ingxin.chatkeyboard.lib.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * 工具
 */
public class Utils {

    private static final String SP_NAME = "keyboard";
    private static final String DEF_KEYBOARD_HEIGHT = "def_keyboard_height";

    private static int sDefKeyboardHeight = 0;

    /**
     * 获取Keyboard高度，默认情况为屏幕高度3/7,最终应该为输入法键盘弹出后的高度
     */
    public static int getDefKeyboardHeight(Context context) {
        if (sDefKeyboardHeight == 0) {
            sDefKeyboardHeight = getDisplayHeightPixels(context) * 4 / 7;
        }
        SharedPreferences sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        int height = sp.getInt(DEF_KEYBOARD_HEIGHT, 0);
        if (height > 0 && sDefKeyboardHeight != height) {
            //高度保存到本地
            Utils.setDefKeyboardHeight(context, height);
        }
        return sDefKeyboardHeight;
    }

    /**
     * 保存键盘高度
     */
    public static void setDefKeyboardHeight(Context context, int height) {
        if (sDefKeyboardHeight != height) {
            SharedPreferences sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
            sp.edit().putInt(DEF_KEYBOARD_HEIGHT, height).apply();
        }
        sDefKeyboardHeight = height;
    }

    /**
     * 获取屏幕高度
     */
    public static int getDisplayHeightPixels(Context context) {
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        if (manager != null) {
            manager.getDefaultDisplay().getMetrics(metrics);
        }
        return metrics.heightPixels;
    }

    /**
     * dp转px单位
     *
     * @param context context
     * @param dpValue dp的值
     * @return dp转化成px后的值
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 重置所有的值
     */
    public static void reset(Context context) {
        //不能改变执行顺序
        Utils.setDefKeyboardHeight(context, 0);
        sDefKeyboardHeight = 0;
    }

}
