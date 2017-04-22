package jiyun.com.oschinawork.fragment.dongtan;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;
import jiyun.com.oschinawork.App;
import jiyun.com.oschinawork.R;
import jiyun.com.oschinawork.activity.MainActivity;
import jiyun.com.oschinawork.adapter.tweet.DongTan_PagerAdapter;
import jiyun.com.oschinawork.base.BaseFragment;

/**
 * Created by Administrator on 2017/4/13.
 */

public class TweetFragment extends BaseFragment {
    @BindView(R.id.Dongtan_Tablayout)
    TabLayout DongtanTablayout;
    @BindView(R.id.Dongtan_ViewPager)
    ViewPager DongtanViewPager;
    Unbinder unbinder;
    private DongTan_PagerAdapter adapter;
    private List<String> mListName;
    private List<BaseFragment> mList;


    @Override
    protected int layoutId() {
        return R.layout.tweet_fragment;
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
        mListName.add("最新动弹");
        mListName.add("热门动弹");
        mListName.add("我的动弹");
        mList.add(new NewTongtan());
        mList.add(new RemenDongtan());
        mList.add(new MyDongTan());
        Log.e("same", mList.size() + "");

        adapter = new DongTan_PagerAdapter(getFragmentManager(), mListName, mList);
        DongtanViewPager.setAdapter(adapter);
        DongtanTablayout.setupWithViewPager(DongtanViewPager);
    }

    @Override
    protected void onHiddn() {

    }

    @Override
    protected void show() {
        unTitleBar();
    }

    //隐藏布局
    @Override
    protected void unTitleBar() {
      if(App.activity instanceof MainActivity){
       if(((MainActivity)App.activity).getMainRadioGroup().getVisibility()==View.GONE){
           ((MainActivity)App.activity).getMainRadioGroup().setVisibility(View.VISIBLE);
       }else if(((MainActivity)App.activity).getMainTitleBar().getVisibility()==View.GONE){
           ((MainActivity)App.activity).getMainTitleBar().setVisibility(View.VISIBLE);
       }

      }
        if (App.activity instanceof MainActivity) {
            ((MainActivity) App.activity).getTitleText().setText("动弹");
        }
    }

    @Override
    public void setParams(Bundle bundle) {

    }


}
