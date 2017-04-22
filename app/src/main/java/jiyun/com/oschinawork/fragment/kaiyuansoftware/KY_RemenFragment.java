package jiyun.com.oschinawork.fragment.kaiyuansoftware;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;

import com.androidkun.PullToRefreshRecyclerView;
import com.thoughtworks.xstream.XStream;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;
import jiyun.com.oschinawork.R;
import jiyun.com.oschinawork.adapter.kaiyuan.KYTuiJianAdapter;
import jiyun.com.oschinawork.base.BaseFragment;
import jiyun.com.oschinawork.http.NewsModle;
import jiyun.com.oschinawork.http.NewsModleImpl;
import jiyun.com.oschinawork.http.callback.MyCallBack;
import jiyun.com.oschinawork.modle.bean.KY_TuiJianBean;

/**
 * Created by Administrator on 2017/4/16.
 */

public class KY_RemenFragment extends BaseFragment {
    @BindView(R.id.RuanJianRecycler)
    PullToRefreshRecyclerView RuanJianRecycler;
    Unbinder unbinder;
    private List<KY_TuiJianBean.SoftwareBean> mList;
    private NewsModle modle;
    private KYTuiJianAdapter adapter;
    @Override
    protected int layoutId() {
        return R.layout.ruanjian_activity;
    }

    @Override
    protected void initView(View view) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        RuanJianRecycler.setLayoutManager(linearLayoutManager);
        RuanJianRecycler.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
    }

    @Override
    protected void initData() {
        modle = new NewsModleImpl();
        mList = new ArrayList<>();
        adapter = new KYTuiJianAdapter(getActivity(), mList);
        RuanJianRecycler.setAdapter(adapter);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void loadData() {
        modle.KaiYuanTJ("view", "2", "10", new MyCallBack() {
            @Override
            public void onSuccess(String response) {
                XStream xStream = new XStream();
                xStream.alias("oschina", KY_TuiJianBean.class);
                xStream.alias("software", KY_TuiJianBean.SoftwareBean.class);
                KY_TuiJianBean bean = (KY_TuiJianBean) xStream.fromXML(response);
                mList.addAll(bean.getSoftwares());
                Log.e("数据是：：", response);
            }

            @Override
            public void onError(String error) {

            }
        });
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
