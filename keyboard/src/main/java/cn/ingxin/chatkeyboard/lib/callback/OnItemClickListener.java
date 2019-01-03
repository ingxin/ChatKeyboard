package cn.ingxin.chatkeyboard.lib.callback;

public interface OnItemClickListener<T> {

    /**
     * item 点击事件
     *
     * @param item     点击的item
     * @param position 点击的位置
     */
    void onItemClick(T item, int position);

}
