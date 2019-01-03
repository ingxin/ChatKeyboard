package cn.ingxin.emoticons.emoticons;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.ingxin.emoticons.emoticons.pojo.SpanResult;
import cn.ingxin.emoticons.emoticons.view.EmoticonSpan;

import static cn.ingxin.emoticons.emoticons.utils.Utils.checkNotNull;

/**
 * 显示表情到文本中相关管理类
 */
public class XEmoticon {

    private List<ResolverFactory> resolverFactories;

    private XEmoticon(List<ResolverFactory> resolverFactories) {
        this.resolverFactories = resolverFactories;
    }

    /**
     * 显示表情{@link #displayEmoticon(TextView, String)}
     */
    public void displayEmoticon(@NonNull TextView view, Editable editable) {
        if (editable != null) {
            displayEmoticon(view, editable.toString());
        }
    }

    /**
     * 显示表情
     *
     * @param view    需要显示view（继承TextView）
     * @param content 需要设置的内容
     */
    public void displayEmoticon(@NonNull TextView view, String content) {
        if (TextUtils.isEmpty(content)) {
            view.setText(content);
            return;
        }
        int fontHeight = getFontHeight(view);
        CharSequence spannableStringBuilder = parserContent(view.getContext(), content, fontHeight);
        if (view instanceof EditText) {
            //设置光标
            int selectionPosition = view.getSelectionStart();
            view.setText(spannableStringBuilder);
            ((EditText) view).setSelection(selectionPosition);
        } else {
            view.setText(spannableStringBuilder);
        }
    }

    /**
     * 解析文本里面的表情，并返回图文模式
     *
     * @param context    上下文
     * @param content    需要解析的文本
     * @param fontHeight 表情的高度
     */
    public CharSequence parserContent(Context context, String content, int fontHeight) {
        if (TextUtils.isEmpty(content)) {
            return content;
        }
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(content);
        //获取解析工厂进行解析
        for (ResolverFactory factory : resolverFactories) {
            List<SpanResult> spanResults = factory.scanSpan(context, content);
            handleScanRes(spanResults, spannableStringBuilder, fontHeight);
        }
        return spannableStringBuilder;
    }

    /**
     * 替换表情
     */
    private void handleScanRes(List<SpanResult> spanResults, SpannableStringBuilder spannable, int fontHeight) {
        if (spanResults != null) {
            for (SpanResult sr : spanResults) {
                if (sr.emoticonDrawable != null) {
                    setSpan(spannable, sr.emoticonDrawable, fontHeight, sr.start, sr.end);
                }
            }
        }
    }

    /**
     * 插入表情到view
     *
     * @param emoticon 插入的表情
     * @param view     继承自TextView
     */
    public void insert2View(@NonNull TextView view, @NonNull Emoticon emoticon) {
        int index = view.getSelectionStart();
        Editable editable = view.getEditableText();
        editable.insert(index, emoticon.key);
        displayEmoticon(view, editable.toString());
    }

    /**
     * 发出一次键盘删除事件
     *
     * @param view 继承自TextView
     */
    public static void deleteEvent(@NonNull TextView view) {
        //模拟触发一次完整的键盘删除事件
        KeyEvent downEvent = new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DEL);
        view.onKeyDown(KeyEvent.KEYCODE_DEL, downEvent);
        KeyEvent upEvent = new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_DEL);
        view.onKeyUp(KeyEvent.KEYCODE_DEL, upEvent);
    }

    /**
     * 向spannable中设置对应的span
     *
     * @param spannable 需要设置的spannable对象
     * @param drawable  需要嵌入的表情
     */
    private void setSpan(Spannable spannable, Drawable drawable, int fontSize, int start, int end) {
        if (drawable != null) {
            int itemHeight;
            int itemWidth;
            if (fontSize == -1) {
                itemHeight = drawable.getIntrinsicHeight();
                itemWidth = drawable.getIntrinsicWidth();
            } else {
                itemHeight = fontSize;
                itemWidth = fontSize;
            }
            //noinspection SuspiciousNameCombination
            drawable.setBounds(0, 0, itemHeight, itemWidth);
            EmoticonSpan imageSpan = new EmoticonSpan(drawable);
            spannable.setSpan(imageSpan, start, end, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        }
    }

    /**
     * 获取字体高度
     *
     * @param textView 需要测量的textView
     * @return 测量后的高度
     */
    private static int getFontHeight(TextView textView) {
        Paint paint = new Paint();
        paint.setTextSize(textView.getTextSize());
        Paint.FontMetrics fm = paint.getFontMetrics();
        return (int) Math.ceil(fm.bottom - fm.top);
    }

    /**
     * builder创建
     */
    public static final class Builder {

        //所有解析类
        private final List<ResolverFactory> resolverFactories = new ArrayList<>();

        /**
         * 添加一个解析表情的逻辑
         *
         * @param factory factory
         * @return this
         */
        public Builder addResolverFactory(ResolverFactory factory) {
            resolverFactories.add(checkNotNull(factory, "factory == null"));
            return this;
        }

        /**
         * 创建XEmoticon
         *
         * @return XEmoticon
         */
        public XEmoticon build() {
            return new XEmoticon(resolverFactories);
        }
    }


}
