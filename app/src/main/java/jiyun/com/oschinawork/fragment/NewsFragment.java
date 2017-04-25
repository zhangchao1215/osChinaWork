package jiyun.com.oschinawork.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import jiyun.com.oschinawork.App;
import jiyun.com.oschinawork.R;
import jiyun.com.oschinawork.activity.MainActivity;
import jiyun.com.oschinawork.adapter.FragAdapter;
import jiyun.com.oschinawork.base.BaseFragment;

/**
 * Created by Administrator on 2017/4/11.
 */

public class NewsFragment extends BaseFragment {
    Unbinder unbinder;
    private List<String> listName;
    private List<BaseFragment> mList;
    private FragAdapter adapter;
    @BindView(R.id.newsTabLayout)
    TabLayout newsTabLayout;
    @BindView(R.id.newsViewPager)
    ViewPager newsViewPager;


    //找ID布局文件
    @Override
    protected int layoutId() {
        return R.layout.news_fragment;
    }

    //初始化组件
    @Override
    protected void initView(View view) {

    }

    //加载数据
    @Override
    protected void initData() {
        listName = new ArrayList<>();
        mList = new ArrayList<>();
        //加载Fragment类
        mList.add(new NewsconterFragment());
        mList.add(new ReDianFragment());
        mList.add(new BlogFragment());
        mList.add(new TuiJianFragment());
        listName.add("资讯");
        listName.add("博客");
        listName.add("热点");
        listName.add("推荐");
        adapter = new FragAdapter(getFragmentManager(), listName, mList);
        newsViewPager.setAdapter(adapter);
        newsTabLayout.setupWithViewPager(newsViewPager);

    }

    //监听事件
    @Override
    protected void initListener() {

    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void onHiddn() {
        unTitleBar();
    }

    @Override
    protected void show() {
        unTitleBar();
    }

    @Override
    protected void unTitleBar() {
        if (App.activity instanceof MainActivity) {
            //显示
            ((MainActivity) App.activity).getMainTitleBar().setVisibility(View.VISIBLE);
            ((MainActivity) App.activity).getMainRadioGroup().setVisibility(View.VISIBLE);

        }
        if (App.activity instanceof MainActivity) {
            ((MainActivity) App.activity).getTitleText().setText("综合");
        }
    }

    @Override
    public void setParams(Bundle bundle) {

    }

}
