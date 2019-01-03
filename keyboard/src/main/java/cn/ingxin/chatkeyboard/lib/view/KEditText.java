package cn.ingxin.chatkeyboard.lib.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewParent;

public class KEditText extends android.support.v7.widget.AppCompatEditText {

    private SoftHandleLayout mSoftHandleLayout;

    public KEditText(Context context) {
        super(context);
    }

    public KEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public KEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean superResult = super.onTouchEvent(event);
        int action = event.getAction();
        if (action == MotionEvent.ACTION_UP) {
            if (mSoftHandleLayout != null) {
                mSoftHandleLayout.popSoftKeyboard();
            }
        }
        return superResult;
    }

    /**
     * 在父布局中找到SoftHandleLayout
     */
    private void findSoftHandleLayout() {
        if (mSoftHandleLayout == null) {
            SoftHandleLayout soft = null;
            ViewParent parent = getParent();
            while (parent != null) {
                if (parent instanceof SoftHandleLayout) {
                    soft = (SoftHandleLayout) parent;
                    break;
                }
                parent = parent.getParent();
            }
            mSoftHandleLayout = soft;
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        if (hasWindowFocus) {
            findSoftHandleLayout();
        }
    }

}
