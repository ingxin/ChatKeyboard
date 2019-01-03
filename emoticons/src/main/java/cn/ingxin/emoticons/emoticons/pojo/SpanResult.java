package cn.ingxin.emoticons.emoticons.pojo;

import android.graphics.drawable.Drawable;

/**
 * 替换文本中特殊表情字符结果
 */
public class SpanResult {

    //表情Drawable对象
    public Drawable emoticonDrawable;

    //该表情插入的index
    public int start;

    //该表情插入的重点index
    public int end;

}
