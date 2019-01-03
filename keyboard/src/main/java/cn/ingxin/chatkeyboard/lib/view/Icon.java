package cn.ingxin.chatkeyboard.lib.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * 图标
 */
public class Icon extends android.support.v7.widget.AppCompatImageView {

    private static int selectColor = Color.rgb(217, 217, 217);

    //记录是否选中,预留字段
    private boolean isSelect;

    //我的位置，该值应该在创建的时候立即设置
    private int mPosition;

    public Icon(Context context) {
        this(context, null);
    }

    public Icon(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Icon(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LinearLayout.LayoutParams params = new LinearLayout
                .LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
        setLayoutParams(params);
    }

    /**
     * 给该Icon初始化一个位置
     *
     * @param position position
     */
    public void initPosition(int position) {
        this.mPosition = position;
    }

    /**
     * 获取当前icon的位置
     *
     * @return 返回该icon的位置
     */
    public int getPosition() {
        return mPosition;
    }

    /**
     * 选中
     */
    public void select() {
        isSelect = true;
        setBackgroundColor(selectColor);
    }

    /**
     * 取消选中
     */
    public void disSelect() {
        isSelect = false;
        setBackgroundColor(Color.TRANSPARENT);
    }

}
