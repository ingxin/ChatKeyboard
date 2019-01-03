package cn.ingxin.chatkeyboard.lib.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import cn.ingxin.chatkeyboard.lib.R;
import cn.ingxin.chatkeyboard.lib.adapter.EmoticonPageAdapter;
import cn.ingxin.chatkeyboard.lib.data.PageSet;
import cn.ingxin.chatkeyboard.lib.view.BottomIconBar;
import cn.ingxin.chatkeyboard.lib.view.Icon;

/**
 * 管理和展示所有表情页面
 */
public class EmoticonPageFragment extends XEmoticonPageFragment {

    public static final String DELETE = "DELETE_OPERATION";

    private ViewPager mViewPager;
    private BottomIconBar iconBar;

    public static EmoticonPageFragment newInstance() {
        return new EmoticonPageFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.ckb_fragment_emoticon_page, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        iconBar = (BottomIconBar) view.findViewById(R.id.icon_bar);
        mViewPager = (ViewPager) view.findViewById(R.id.view_pager);

        //删除事件
        view.findViewById(R.id.ckb_del).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOperation(DELETE);
            }
        });

        //添加到底部选项卡
        List<PageSet> pageSetList = getPageSets();
        if (pageSetList != null) {
            for (PageSet set : pageSetList) {
                iconBar.addIcon(set.icon);
            }
        }

        iconBar.setOnIconSelectListener(new BottomIconBar.OnIconSelectListener() {
            @Override
            public void onIconSelect(Icon icon, int position) {
                mViewPager.setCurrentItem(position, false);
            }
        });
        //初始化view pager
        EmoticonPageAdapter adapter = new EmoticonPageAdapter(getChildFragmentManager(), getPageSets());
        mViewPager.setOffscreenPageLimit(4);
        mViewPager.setAdapter(adapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                iconBar.select(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

}
