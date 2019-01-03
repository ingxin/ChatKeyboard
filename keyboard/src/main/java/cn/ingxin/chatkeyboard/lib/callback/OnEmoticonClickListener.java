package cn.ingxin.chatkeyboard.lib.callback;

import cn.ingxin.emoticons.emoticons.Emoticon;

public interface OnEmoticonClickListener {

    /**
     * 表情点击事件回调
     *
     * @param item     点击的表情
     * @param position 点击的位置
     * @param page     当前表情页数
     */
    void onEmoticonClick(Emoticon item, int position, int page);

    /**
     * 其他选项操作按钮回调事件
     *
     * @param operationName 操作的名字
     */
    void onOperation(String operationName);

}
