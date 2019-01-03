package cn.ingxin.chatkeyboard.lib.view;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.RelativeLayout;

import cn.ingxin.chatkeyboard.lib.R;
import cn.ingxin.chatkeyboard.lib.utils.CKLogger;
import cn.ingxin.chatkeyboard.lib.utils.Utils;

/**
 * 表情控制面板，只包含两个直接子view，第一个view为LinearLayout（包含表情和表情上方的输入框bar等）。
 * 第二个view为真正的内容布局，该布局会自动添加到第一个view的上方。
 * 通过动态设置第一个view的高度达到键盘和表情的切换
 */
public class SoftHandleLayout extends SoftListenLayout {

    //没有弹出操作面板
    public static final int KEYBOARD_STATE_NONE = 100;
    //弹出了操作面板
    public static final int KEYBOARD_STATE_FUNC = 101;
    //软键盘和操作面板都弹出来了
    public static final int KEYBOARD_STATE_BOTH = 102;

    protected int mKeyboardState = KEYBOARD_STATE_NONE;

    //第一个view的id,该view应该包裹上方控制和输入的top bar，以及下发的表情容器
    protected int mAutoHeightLayoutId;
    //Keyboard的高度
    protected int mAutoViewHeight;
    //表情部分view,动态设置高度[0，键盘高度]
    protected View mAutoHeightLayoutView;
    //if soft keyboard close by itself, close auto
    private boolean isAutoViewNeedHide = true;

    public SoftHandleLayout(Context context) {
        this(context, null);
    }

    public SoftHandleLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SoftHandleLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mAutoViewHeight = Utils.getDefKeyboardHeight(context);
    }

    @Override
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        int childSum = getChildCount();
        if (childSum > 1) {
            //只能包含两个儿子，第一个为操作面板（输入框，表情选择栏等作为一个group）
            //第二个为除去操作面板后的真正布局
            throw new IllegalStateException("can host only one direct child");
        }
        super.addView(child, index, params);
        if (childSum == 0) {
            //拿到第一个view作为最底部的操作面板
            mAutoHeightLayoutId = child.getId();
            if (mAutoHeightLayoutId < 0) {
                child.setId(R.id.keyboard_layout_id);
                mAutoHeightLayoutId = R.id.keyboard_layout_id;
            }
            LayoutParams paramsChild = (LayoutParams) child.getLayoutParams();
            paramsChild.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            child.setLayoutParams(paramsChild);
        } else if (childSum == 1) {
            //第二个view作为真正在的ui布局，设置在操作面板上面
            LayoutParams paramsChild = (LayoutParams) child.getLayoutParams();
            paramsChild.addRule(RelativeLayout.ABOVE, mAutoHeightLayoutId);
            child.setLayoutParams(paramsChild);
        }
    }

    @Override
    protected void onKeyboardPop(int height) {
        if (height > 0 && height != mAutoViewHeight) {
            mAutoViewHeight = height;
            Utils.setDefKeyboardHeight(getContext(), mAutoViewHeight);
        }
        //if soft keyboard popped, auto view must be visible, soft keyboard covers it
        if (mKeyboardState != KEYBOARD_STATE_BOTH) {
            showAutoView();
        } else {
            // else, just update auto view height
            postDelayed(new Runnable() {
                @Override
                public void run() {
                    setAutoViewHeight(mAutoViewHeight);
                }
            }, 150);
        }
        mKeyboardState = KEYBOARD_STATE_BOTH;
    }

    @Override
    protected void onKeyboardClose() {
        //如果输入法键盘收起时，当前状态为KEYBOARD_STATE_BOTH则只收起输入法保留Keyboard，即退回到KEYBOARD_STATE_FUNC
        mKeyboardState = mKeyboardState == KEYBOARD_STATE_BOTH ? KEYBOARD_STATE_FUNC :
                KEYBOARD_STATE_NONE;
        // if keyboard closed isn't by calling close, but by pressing back button or keyboard
        // hide, hide auto view
        if (isAutoViewNeedHide) {
            hideAutoView();
        }
        isAutoViewNeedHide = true;
    }

    @Override
    protected void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mAutoViewHeight = mKeyboardHeight;
    }

    /**
     * 绑定表情容器部分的view，通过动态设置这一部分的高度达到适应键盘的高度
     *
     * @param view view
     */
    public void bindTargetView(View view) {
        mAutoHeightLayoutView = view;
    }

    private void setAutoViewHeight(final int height) {
        if (mAutoHeightLayoutView == null) {
            CKLogger.e("must invoking bindTargetView");
            return;
        }
        if (height == 0) {
            mAutoHeightLayoutView.setVisibility(GONE);
        } else {
            //必须设置调用bindTargetView
            mAutoHeightLayoutView.setVisibility(VISIBLE);
            ViewGroup.LayoutParams params = mAutoHeightLayoutView.getLayoutParams();
            params.height = height;
            mAutoHeightLayoutView.setLayoutParams(params);
        }
        // it will take some time for view draw
        this.postDelayed(new Runnable() {
            @Override
            public void run() {
                autoViewHeightChanged(height);
            }
        }, 100);
    }

    /**
     * 隐藏表情面板
     */
    public void hideAutoView() {
        post(new Runnable() {
            @Override
            public void run() {
                setAutoViewHeight(0);
            }
        });
        mKeyboardState = KEYBOARD_STATE_NONE;
    }

    /**
     * 显示表情面板
     */
    public void showAutoView() {
        // show auto view is after keyboard show will be better
        // there exist time during keyboard popping
        postDelayed(new Runnable() {
            @Override
            public void run() {
                setAutoViewHeight(mAutoViewHeight);
            }
        }, 150);
        isAutoViewNeedHide = true;
        mKeyboardState = KEYBOARD_STATE_FUNC;
    }

    @SuppressWarnings("unused")
    protected void autoViewHeightChanged(final int height) {
        // rent the place for child use
    }

    public void closeSoftKeyboard() {
        Activity activity = getActivity();
        if (activity == null) {
            return;
        }
        InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService
                (Context.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null && activity.getCurrentFocus() != null) {
            inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), InputMethodManager
                    .HIDE_NOT_ALWAYS);
        }
        //只收起软键盘，则不需要收起表情
        isAutoViewNeedHide = false;
    }

    public void openSoftKeyboard(View view) {
        view.setFocusable(true);
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        InputMethodManager inputManager = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputManager != null) {
            inputManager.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
            //触发弹起表情面板
            popSoftKeyboard();
        }
    }

    /**
     * 获取当前面板的状态
     */
    public int getState() {
        return mKeyboardState;
    }


}