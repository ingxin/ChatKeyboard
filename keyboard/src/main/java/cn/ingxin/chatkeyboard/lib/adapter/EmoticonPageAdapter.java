package cn.ingxin.chatkeyboard.lib.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

import cn.ingxin.chatkeyboard.lib.data.PageSet;
import cn.ingxin.chatkeyboard.lib.ui.EmoticonFragment;

/**
 * view pager 包含所有的表情分页
 */
public class EmoticonPageAdapter extends FragmentStatePagerAdapter {

    private List<PageSet> pageSets;

    public EmoticonPageAdapter(FragmentManager fm, List<PageSet> pageSets) {
        super(fm);
        this.pageSets = pageSets;
    }

    @Override
    public Fragment getItem(int position) {
        PageSet set = pageSets.get(position);
        return EmoticonFragment.newInstance(set.column, position, set.emoticons);
    }

    @Override
    public int getCount() {
        return pageSets == null ? 0 : pageSets.size();
    }
}
