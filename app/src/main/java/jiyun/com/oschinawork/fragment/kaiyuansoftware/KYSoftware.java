package jiyun.com.oschinawork.fragment.kaiyuansoftware;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import jiyun.com.oschinawork.App;
import jiyun.com.oschinawork.R;
import jiyun.com.oschinawork.activity.MainActivity;
import jiyun.com.oschinawork.adapter.tweet.DongTan_PagerAdapter;
import jiyun.com.oschinawork.base.BaseFragment;

/**
 * Created by Administrator on 2017/4/16.
 */

public class KYSoftware extends BaseFragment {
    @BindView(R.id.kaiYanTab)
    TabLayout kaiYanTab;
    @BindView(R.id.KaiYuanPager)
    ViewPager KaiYuanPager;
    private DongTan_PagerAdapter adapter;
    private List<String> mListName;
    private List<BaseFragment> mList;

    @Override
    protected int layoutId() {
        return R.layout.kaiyuan_activity;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void loadData() {
        mListName = new ArrayList<>();
        mList = new ArrayList<>();
        mListName.add("分类");
        mListName.add("推荐");
        mListName.add("最新");
        mListName.add("热门");
        mListName.add("国产");
        mList.add(new KY_FeiLei_Zong());
        mList.add(new KY_TuiJianFragment());
        mList.add(new KY_GuoChanFragment());
        mList.add(new KY_NewFragment());
        mList.add(new KY_RemenFragment());
        adapter = new DongTan_PagerAdapter(getFragmentManager(), mListName, mList);
        KaiYuanPager.setAdapter(adapter);
        kaiYanTab.setupWithViewPager(KaiYuanPager);
    }

    @Override
    protected void onHiddn() {

    }

    @Override
    protected void show() {

    }

    @Override
    protected void unTitleBar() {
        if (App.activity instanceof MainActivity) {
            ((MainActivity) App.activity).getMainTitleBar().setVisibility(View.GONE);
            ((MainActivity) App.activity).getMainRadioGroup().setVisibility(View.GONE);
        }

    }

    @Override
    public void setParams(Bundle bundle) {

    }

}
