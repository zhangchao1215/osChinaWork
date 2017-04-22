package jiyun.com.oschinawork.fragment.kaiyuansoftware;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import jiyun.com.oschinawork.App;
import jiyun.com.oschinawork.R;
import jiyun.com.oschinawork.base.BaseFragment;

/**
 * Created by Administrator on 2017/4/20.
 */

public class KY_FeiLei_Zong extends BaseFragment {
    private FragmentManager man;
    @BindView(R.id.Faxian_FramLayout)
    FrameLayout FaxianFramLayout;
    Unbinder unbinder;

    @Override
    protected int layoutId() {
        return R.layout.activity_faxian_fromlayout;
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
        man = App.activity.getSupportFragmentManager();
        FragmentTransaction tra= man.beginTransaction();
        tra.replace(R.id.Faxian_FramLayout,new KY_FenLeiFragment());
        tra.commit();
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
