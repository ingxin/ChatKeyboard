package cn.ingxin.chatkeyboard.lib.view;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.RelativeLayout;

import cn.ingxin.chatkeyboard.lib.utils.CKLogger;
import cn.ingxin.chatkeyboard.lib.utils.Utils;

/**
 * 监听表情面板的弹入和弹出，当软键盘弹出或弹入时该表情面板应该修改高度去适应
 * 输入法键盘
 */
public abstract class SoftListenLayout extends RelativeLayout {

    //最小Layout高度
    private int mMinLayoutHeight;
    //触发Keyboard弹起的阈值
    private int mMinKeyboardHeight;
    //Keyboard高度
    protected int mKeyboardHeight;
    //记录当前window可见的bottom位置
    private int mGlobalBottom;

    protected int mOrientation;
    private ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener;

    public SoftListenLayout(Context context) {
        this(context, null);
    }

    public SoftListenLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SoftListenLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initParameters(context);
        //记录当前屏幕方向
        Activity activity = getActivity();
        if (activity != null) {
            mOrientation = activity.getRequestedOrientation();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int measureHeight = MeasureSpec.getSize(heightMeasureSpec);
        if (measureHeight < mMinLayoutHeight) {
            // if keyboard show, this layout height will be shrank, we should extend it
            int heightMode = MeasureSpec.getMode(heightMeasureSpec);
            int expandSpec = MeasureSpec.makeMeasureSpec(measureHeight + mKeyboardHeight,
                    heightMode);
            super.onMeasure(widthMeasureSpec, expandSpec);
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        getViewTreeObserver().addOnGlobalLayoutListener(getOnGlobalLayoutListener());
    }

    @Override
    protected void onDetachedFromWindow() {
        if (onGlobalLayoutListener != null) {
            getViewTreeObserver().removeGlobalOnLayoutListener(onGlobalLayoutListener);
        }
        super.onDetachedFromWindow();
    }

    @Override
    protected void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation != mOrientation) {
            //屏幕旋转了重置相关参数
            Utils.reset(getContext());
            initParameters(getContext());
            closeSoft();
            mOrientation = newConfig.orientation;
            postDelayed(new Runnable() {
                @Override
                public void run() {
                    onKeyboardClose();
                }
            }, 150);
        }
    }

    /**
     * 初始化相关参数
     */
    private void initParameters(Context context) {
        int displayHeight = Utils.getDisplayHeightPixels(context);
        mMinLayoutHeight = displayHeight * 2 / 3;
        mMinKeyboardHeight = displayHeight / 3;
        mKeyboardHeight = Utils.getDefKeyboardHeight(context);
        mGlobalBottom = 0;
    }

    /**
     * 创建视图监听回调
     */
    private ViewTreeObserver.OnGlobalLayoutListener getOnGlobalLayoutListener() {
        if (onGlobalLayoutListener == null) {
            onGlobalLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    Window window = getActWindow();
                    if (window == null) {
                        CKLogger.e("window is null~~!");
                        return;
                    }
                    Rect r = new Rect();
                    window.getDecorView().getWindowVisibleDisplayFrame(r);
                    //获取弹起视图高度
                    int popHeight = Utils.getDisplayHeightPixels(getContext()) - r.bottom;
                    if ((mGlobalBottom != 0 && mGlobalBottom - r.bottom > mMinKeyboardHeight)
                            || (popHeight != 0 && mKeyboardHeight != popHeight)) {
                        //弹起的高度大于阈值mMinKeyboardHeight 或者 弹起的视图不为mKeyboardHeight(即不是自己弹出)
                        mKeyboardHeight = popHeight;
                        //因为在屏幕旋转或者其他弹窗弹起会误触发弹起键盘逻辑，所以移除该逻辑采用点击EditText触发弹起
//                    onKeyboardPop(mKeyboardHeight);
                    } else if (mGlobalBottom != 0 && r.bottom - mGlobalBottom > mMinKeyboardHeight) {
                        //收起的高度大于阈值
                        onKeyboardClose();
                    }
                    mGlobalBottom = r.bottom;
                }
            };
        }
        return onGlobalLayoutListener;
    }

    /**
     * 弹出keyboard
     */
    void popSoftKeyboard() {
        postDelayed(new Runnable() {
            @Override
            public void run() {
                onKeyboardPop(mKeyboardHeight);
            }
        }, 150);
    }

    /**
     * 获取activity
     */
    protected Activity getActivity() {
        Context context = getContext();
        if (context instanceof Activity) {
            return ((Activity) context);
        } else if (context instanceof ContextWrapper) {
            while (context instanceof ContextWrapper) {
                if (context instanceof Activity) {
                    return ((Activity) context);
                }
                context = ((ContextWrapper) context).getBaseContext();
            }
        }
        return null;
    }

    /**
     * 获取Activity Window对象
     */
    protected Window getActWindow() {
        Window window = null;
        Activity activity = getActivity();
        if (activity != null) {
            window = activity.getWindow();
        }
        return window;
    }

    private void closeSoft() {
        Activity activity = getActivity();
        if (activity != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService
                    (Context.INPUT_METHOD_SERVICE);
            if (inputMethodManager != null && activity.getCurrentFocus() != null) {
                inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), InputMethodManager
                        .HIDE_NOT_ALWAYS);
            }
        }
    }

    /**
     * 打开表情面板
     */
    protected abstract void onKeyboardPop(int height);

    /**
     * 关闭表情面板
     */
    protected abstract void onKeyboardClose();

}