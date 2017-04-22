package jiyun.com.oschinawork.fragment.searchFragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import jiyun.com.oschinawork.R;
import jiyun.com.oschinawork.adapter.search.Search_PagerAdapter;
import jiyun.com.oschinawork.base.BaseFragment;

/**
 * Created by Administrator on 2017/4/14.
 */

public class MainSearchFragment extends BaseFragment {

    @BindView(R.id.SearchTabLayout)
    TabLayout SearchTabLayout;
    @BindView(R.id.Search_ViewPager)
    ViewPager newsViewPager;
    private List<String> listName;
    private List<BaseFragment> mList;
    private Search_PagerAdapter adapter;

    @Override
    protected int layoutId() {
        return R.layout.search_fragment_activity;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initData() {

        listName = new ArrayList<>();
        mList = new ArrayList<>();
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void loadData() {
        listName.add("博客");
        listName.add("资讯");
        listName.add("软件");
        listName.add("问答");
        listName.add("找人");
        //这是设置TabLayout水平滑动
        SearchTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        mList.add(new Search_BlogFragment());//博客
        mList.add(new Search_ZixunFragment());//咨询
        mList.add(new Search_RuanJianFragment());//软件
        mList.add(new Search_WenDaFragment());//问答
        mList.add(new Search_PeopleFragment());//找人
        adapter = new Search_PagerAdapter(getFragmentManager(), listName, mList);
        newsViewPager.setAdapter(adapter);
        SearchTabLayout.setupWithViewPager(newsViewPager);
        Log.e("tablayout",mList.size()+"");
    }

    @Override
    protected void onHiddn() {

    }

    @Override
    protected void show() {

    }

    @Override
    protected void unTitleBar() {

    }

    @Override
    public void setParams(Bundle bundle) {

    }



    }



