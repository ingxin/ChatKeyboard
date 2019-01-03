package cn.ingxin.chatkeyboard.lib.data;

import android.support.annotation.IntRange;
import android.support.annotation.Nullable;

import java.util.ArrayList;

import cn.ingxin.emoticons.emoticons.Emoticon;

/**
 * 一个表情page页
 */
public final class PageSet {

    public final int column;
    public final int icon;
    public final ArrayList<Emoticon> emoticons;

    private PageSet(Builder builder) {
        this.column = builder.column;
        this.emoticons = builder.emoticons;
        this.icon = builder.icon;
    }

    /**
     * builder
     */
    public final static class Builder {
        //多少列
        private int column = 7;
        //底部显示图标
        private int icon;
        //表情
        private ArrayList<Emoticon> emoticons;

        public Builder setColumn(@IntRange(from = 1, to = 10) int column) {
            this.column = column;
            return this;
        }

        public Builder setEmoticons(@Nullable ArrayList<Emoticon> emoticons) {
            this.emoticons = emoticons;
            return this;
        }

        public Builder setIcon(int icon) {
            this.icon = icon;
            return this;
        }

        public PageSet build() {
            return new PageSet(this);
        }

    }

}
