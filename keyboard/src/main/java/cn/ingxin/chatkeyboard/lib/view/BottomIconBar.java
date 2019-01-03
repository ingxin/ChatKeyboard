package cn.ingxin.chatkeyboard.lib.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import cn.ingxin.chatkeyboard.lib.utils.CKLogger;
import cn.ingxin.chatkeyboard.lib.utils.Utils;

/**
 * 底部选项卡view
 */
public class BottomIconBar extends LinearLayout implements View.OnClickListener {

    private int paddingLeftRight;
    private int paddingTopBottom;
    private int lastSelectPosition;

    private OnIconSelectListener onIconSelectListener;

    public BottomIconBar(Context context) {
        this(context, null);
    }

    public BottomIconBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BottomIconBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOrientation(HORIZONTAL);
        paddingLeftRight = Utils.dip2px(getContext(), 10);
        paddingTopBottom = Utils.dip2px(getContext(), 6);
    }

    @Override
    public void addView(View child) {
        super.addView(child);
    }

    @Override
    public void addView(View child, int index) {
        super.addView(child, index);
        checkView(child);
    }

    @Override
    public void addView(View child, int width, int height) {
        super.addView(child, width, height);
        checkView(child);
    }

    @Override
    public void addView(View child, ViewGroup.LayoutParams params) {
        super.addView(child, params);
        checkView(child);
    }

    @Override
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        super.addView(child, index, params);
        checkView(child);
    }

    @Override
    public void onClick(View v) {
        Icon icon = (Icon) v;
        int clickPosition = icon.getPosition();
        if (clickPosition == lastSelectPosition) {
            CKLogger.i("click same position");
            return;
        }

        //改变状态
        Icon last = (Icon) getChildAt(lastSelectPosition);
        last.disSelect();
        icon.select();
        lastSelectPosition = clickPosition;

        if (onIconSelectListener != null) {
            onIconSelectListener.onIconSelect(icon, icon.getPosition());
        }
    }

    private void checkView(View view) {
        if (!(view instanceof Icon)) {
            throw new UnsupportedOperationException("all child view must be cn.ingxin.chatkeyboard.lib.view.Icon");
        }
    }

    /**
     * 添加一个tab图标
     *
     * @param imageRes 增加的图标
     */
    public void addIcon(int imageRes) {
        if (imageRes == 0) {
            throw new UnsupportedOperationException("must set icon,see cn.ingxin.chatkeyboard.lib.data.PageSet");
        }
        //分配index给新增的icon
        int position = getChildCount();
        Icon icon = new Icon(getContext());
        icon.initPosition(position);
        icon.setPadding(paddingLeftRight, paddingTopBottom, paddingLeftRight, paddingTopBottom);
        icon.setImageResource(imageRes);
        if (position == 0) {
            lastSelectPosition = 0;
            icon.select();
        }
        //set click listener
        icon.setOnClickListener(this);
        addView(icon);
    }

    /**
     * 选中position位置的icon
     *
     * @param position 选中的位置
     */
    public void select(int position) {
        if (position == lastSelectPosition) {
            CKLogger.i("click same position");
            return;
        }
        Icon lastIcon = (Icon) getChildAt(lastSelectPosition);
        Icon icon = (Icon) getChildAt(position);
        icon.select();
        lastIcon.disSelect();
        lastSelectPosition = position;
    }

    /**
     * 设置icon选中接口回调
     *
     * @param listener listener
     */
    public void setOnIconSelectListener(OnIconSelectListener listener) {
        this.onIconSelectListener = listener;
    }

    /**
     * 图标被选中事件
     */
    public interface OnIconSelectListener {
        /**
         * icon被选中事件回调
         *
         * @param position 不选中的位置
         */
        void onIconSelect(Icon icon, int position);
    }

}
