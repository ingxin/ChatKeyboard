package cn.ingxin.chatkeyboard.lib;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import java.util.ArrayList;
import java.util.List;

import cn.ingxin.chatkeyboard.lib.callback.OnEmoticonClickListener;
import cn.ingxin.chatkeyboard.lib.data.PageSet;
import cn.ingxin.chatkeyboard.lib.ui.EmoticonPageFragment;
import cn.ingxin.chatkeyboard.lib.ui.XEmoticonPageFragment;
import cn.ingxin.chatkeyboard.lib.utils.CKLogger;
import cn.ingxin.chatkeyboard.lib.view.SoftHandleLayout;

/**
 * 表情面板控制类
 */
public class Kb {

    //没有弹出操作面板
    public static final int KEYBOARD_STATE_NONE = SoftHandleLayout.KEYBOARD_STATE_NONE;
    //弹出了操作面板
    public static final int KEYBOARD_STATE_FUNC = SoftHandleLayout.KEYBOARD_STATE_FUNC;
    //软键盘和操作面板都弹出来了
    public static final int KEYBOARD_STATE_BOTH = SoftHandleLayout.KEYBOARD_STATE_BOTH;

    //表情功能fragment容器名字
    private static final String EMOTICONS_CONTAINER_NAME = XEmoticonPageFragment.class.getName();

    //外部softHandleLayout
    private SoftHandleLayout softHandleLayout;
    //fragment manager
    private FragmentManager mFragmentManager;
    //面板容器布局id，用来切换各种fragment
    private final int containerId;
    //记录上一个其他fragment名字
    private String lastName;

    private Kb(@NonNull Builder builder, int containerId) {
        this.softHandleLayout = builder.softHandleLayout;
        this.mFragmentManager = builder.mFragmentManager;
        this.containerId = containerId;
    }

    /**
     * 显示表情面板
     */
    public void showKb() {
        softHandleLayout.showAutoView();
    }

    /**
     * 收起表情面板
     */
    public void hideKb() {
        softHandleLayout.hideAutoView();
    }

    /**
     * 打开输入法软键盘
     *
     * @param view 触发显示的view
     */
    public void openSoftKeyboard(View view) {
        softHandleLayout.openSoftKeyboard(view);
    }

    /**
     * 收起输入法软键盘
     */
    public void closeSoftKeyboard() {
        softHandleLayout.closeSoftKeyboard();
    }

    /**
     * 处理返回按键事件
     *
     * @return true表示消费了该次返回，false表示不消费当次返回事件
     */
    public boolean onBackPressed() {
        if (softHandleLayout.getState() != SoftHandleLayout.KEYBOARD_STATE_NONE) {
            //面板已经弹出就收起面板
            softHandleLayout.hideAutoView();
            return true;
        }
        return false;
    }

    /**
     * 获取kb弹出状态see {@link #KEYBOARD_STATE_BOTH},{@link #KEYBOARD_STATE_FUNC},{@link #KEYBOARD_STATE_BOTH}
     *
     * @return 返回状态
     */
    public int getKbState() {
        if (softHandleLayout != null) {
            return softHandleLayout.getState();
        } else {
            return -1;
        }
    }

    /**
     * 显示其他功能模块fragment
     *
     * @param fragment 其他功能模块fragment
     */
    public void showOther(Fragment fragment) {
        if (fragment == null) {
            CKLogger.e("show other fragment is null");
            return;
        }
        FragmentTransaction ts = mFragmentManager.beginTransaction();
        Fragment emoticonsFragment = mFragmentManager.findFragmentByTag(EMOTICONS_CONTAINER_NAME);
        //隐藏表情fragment
        if (emoticonsFragment != null && !emoticonsFragment.isHidden()) {
            ts.hide(emoticonsFragment);
        }
        //移除上一个fragment(除了表情容器)
        if (!TextUtils.isEmpty(lastName)) {
            Fragment last = mFragmentManager.findFragmentByTag(lastName);
            if (last != null) {
                ts.remove(last);
            }
        }
        lastName = fragment.getClass().getName();
        ts.add(containerId, fragment, lastName);
        ts.commitAllowingStateLoss();
    }


    /**
     * 当显示其他功能模块的fragment时，通过调用该方法重新显示表情布局
     */
    public void showEmoticion() {
        Fragment emoticonsFragment = mFragmentManager.findFragmentByTag(EMOTICONS_CONTAINER_NAME);
        if (emoticonsFragment != null && !emoticonsFragment.isHidden()) {
            //正在显示表情容器
            return;
        }
        FragmentTransaction ts = mFragmentManager.beginTransaction();
        //隐藏正在显示fragment
        if (!TextUtils.isEmpty(lastName)) {
            Fragment last = mFragmentManager.findFragmentByTag(lastName);
            if (last != null) {
                ts.remove(last);
            }
            lastName = null;
        }
        //显示表情
        ts.show(emoticonsFragment);
        //提交事务
        ts.commitAllowingStateLoss();
    }

    /**
     * builder
     */
    public static class Builder {
        private Context context;
        //fragment manager
        private FragmentManager mFragmentManager;
        //表情page
        private List<PageSet> pageSets;
        //表情容器
        private ViewGroup faceContent;
        //外部softHandleLayout
        private SoftHandleLayout softHandleLayout;
        //表情点击事件
        private OnEmoticonClickListener onEmoticonClickListener;
        //显示表情容器的fragment
        private XEmoticonPageFragment emoticonPageFragment;

        public Builder(@NonNull FragmentActivity activity) {
            this(activity, activity.getSupportFragmentManager());
        }

        public Builder(@NonNull Fragment fragment) {
            this(fragment.getContext(), fragment.getChildFragmentManager());
        }

        public Builder(Context context, @NonNull FragmentManager fragmentManager) {
            this.mFragmentManager = fragmentManager;
            this.pageSets = new ArrayList<>();
            this.context = context;
        }

        /**
         * 添加一个表情page页
         *
         * @param pageSet 表情page页
         * @return Kb
         */
        public Builder addPageSet(PageSet pageSet) {
            this.pageSets.add(pageSet);
            return this;
        }

        /**
         * 添加一组page页
         *
         * @param pageSets page list
         * @return kb
         */
        public Builder addPageSets(@NonNull List<PageSet> pageSets) {
            this.pageSets.addAll(pageSets);
            return this;
        }

        /**
         * 绑定表情容器
         *
         * @param faceContent 表情容器
         * @return kb
         */
        public Builder with(@NonNull ViewGroup faceContent) {
            this.faceContent = faceContent;
            //找到faceContent的爷爷(SoftHandleLayout)
            ViewParent parent1 = faceContent.getParent();
            ViewParent parent2 = parent1.getParent();
            if (!(parent2 instanceof SoftHandleLayout)) {
                throw new UnsupportedOperationException("faceContent parent parent must be SoftHandleLayout");
            }
            softHandleLayout = (SoftHandleLayout) parent2;
            return this;
        }

        /**
         * 设置表情点击事件
         *
         * @param onEmoticonClickListener 点击事件
         * @return kb
         */
        public Builder setOnEmoticonClickListener(OnEmoticonClickListener onEmoticonClickListener) {
            this.onEmoticonClickListener = onEmoticonClickListener;
            return this;
        }

        /**
         * 设置入口自定义的fragment
         *
         * @param fragment fragment
         * @return kb
         */
        public Builder setCustomFragment(XEmoticonPageFragment fragment) {
            this.emoticonPageFragment = fragment;
            return this;
        }

        /**
         * 创建
         */
        public Kb build() {
            if (faceContent == null) {
                throw new UnsupportedOperationException("must invoking with method!");
            }
            int id = faceContent.getId();
            if (id == View.NO_ID) {
                throw new UnsupportedOperationException("faceContent Required id~~!");
            }

            //入口fragment
            XEmoticonPageFragment targetFragment = emoticonPageFragment == null ?
                    EmoticonPageFragment.newInstance() : emoticonPageFragment;

            //默认添加表情布局
            FragmentTransaction ts = mFragmentManager.beginTransaction();
            targetFragment.setData(pageSets, onEmoticonClickListener);
            ts.replace(id, targetFragment, EMOTICONS_CONTAINER_NAME);
            ts.commitAllowingStateLoss();

            //bind view
            softHandleLayout.bindTargetView(faceContent);
            return new Kb(this, id);
        }

    }

}
