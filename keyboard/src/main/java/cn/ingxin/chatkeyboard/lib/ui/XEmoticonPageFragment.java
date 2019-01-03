package cn.ingxin.chatkeyboard.lib.ui;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import java.util.List;

import cn.ingxin.chatkeyboard.lib.callback.OnEmoticonClickListener;
import cn.ingxin.chatkeyboard.lib.data.PageSet;
import cn.ingxin.emoticons.emoticons.Emoticon;

public class XEmoticonPageFragment extends Fragment {

    //所有page页面
    private List<PageSet> pageSets;
    //表情点击事件
    private OnEmoticonClickListener onEmoticonClickListener;

    /**
     * 设置初始化数据
     * todo 这种直接set不安全 待优化
     *
     * @param pageSets pageSet
     * @param listener 点击回调
     */
    public void setData(List<PageSet> pageSets, OnEmoticonClickListener listener) {
        this.pageSets = pageSets;
        this.onEmoticonClickListener = listener;
    }

    /**
     * 表情点击事件，直接调用
     *
     * @param item     点击的表情
     * @param position 点击的位置
     */
    public void onEmoticonClick(Emoticon item, int page, int position) {
        if (onEmoticonClickListener != null) {
            onEmoticonClickListener.onEmoticonClick(item, position, page);
        }
    }

    /**
     * 其他操作选项点击事件
     *
     * @param operationName 操作名字
     */
    public void onOperation(String operationName) {
        if (onEmoticonClickListener != null) {
            onEmoticonClickListener.onOperation(operationName);
        }
    }

    /**
     * 获取表情
     *
     * @return {@link #pageSets}
     */
    @Nullable
    protected List<PageSet> getPageSets() {
        return pageSets;
    }
}
