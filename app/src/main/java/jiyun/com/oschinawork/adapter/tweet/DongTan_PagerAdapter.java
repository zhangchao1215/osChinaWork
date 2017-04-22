package jiyun.com.oschinawork.adapter.tweet;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import jiyun.com.oschinawork.base.BaseFragment;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;

/**
 * Created by Administrator on 2017/4/16.
 */

public class DongTan_PagerAdapter extends FragmentStatePagerAdapter {
    private List<String> ListName;
    private List<BaseFragment> mList;


    public DongTan_PagerAdapter(FragmentManager fm, List<String> ListName, List<BaseFragment> mList) {
        super(fm);
        this.ListName = ListName;
        this.mList = mList;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

    }

    @Override
    public Fragment getItem(int position) {
        return mList.get(position);
    }

    @Override
    public int getCount() {
        return mList.size();

    }

    @Override
    public CharSequence getPageTitle(int position) {
        return ListName.get(position);
    }
}
