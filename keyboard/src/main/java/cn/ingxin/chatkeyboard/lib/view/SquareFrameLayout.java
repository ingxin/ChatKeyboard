package cn.ingxin.chatkeyboard.lib.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

/**
 * 正方形的FrameLayout
 */
public class SquareFrameLayout extends FrameLayout {

    //ratio = 宽/高
    private static final int RATIO = 1;

    public SquareFrameLayout(@NonNull Context context) {
        super(context);
    }

    public SquareFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SquareFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(View.getDefaultSize(0, widthMeasureSpec), View.getDefaultSize(0, heightMeasureSpec));
        int widthSpec = MeasureSpec.makeMeasureSpec(getMeasuredWidth(), MeasureSpec.EXACTLY);
        //通过宽度设置高度
        int heightSpec = MeasureSpec.makeMeasureSpec(((getMeasuredWidth() / RATIO)), MeasureSpec.EXACTLY);
        super.onMeasure(widthSpec, heightSpec);
    }
}
