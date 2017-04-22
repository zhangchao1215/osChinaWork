package jiyun.com.oschinawork.fragment.kaiyuansoftware;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.androidkun.PullToRefreshRecyclerView;
import com.thoughtworks.xstream.XStream;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import jiyun.com.oschinawork.R;
import jiyun.com.oschinawork.adapter.kaiyuan.KaiYuanAdapter;
import jiyun.com.oschinawork.base.BaseFragment;
import jiyun.com.oschinawork.http.NewsModle;
import jiyun.com.oschinawork.http.NewsModleImpl;
import jiyun.com.oschinawork.http.callback.MyCallBack;
import jiyun.com.oschinawork.modle.bean.RuanJianBean;

/**
 * Created by Administrator on 2017/4/16.
 */

public class KY_FenLeiFragment extends BaseFragment {
    private KaiYuanAdapter adapter;
    private List<RuanJianBean.SoftwareTypeBean> mList;
    private NewsModle modle;
    @BindView(R.id.RuanJianRecycler)
    PullToRefreshRecyclerView RuanJianRecycler;

    @Override
    protected int layoutId() {
        return R.layout.ruanjian_activity;
    }

    @Override
    protected void initView(View view) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        RuanJianRecycler.setLayoutManager(linearLayoutManager);
        RuanJianRecycler.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
    }

    @Override
    protected void initData() {
        modle = new NewsModleImpl();
        mList = new ArrayList<>();
        adapter = new KaiYuanAdapter(getActivity().getApplication(), mList);
        RuanJianRecycler.setAdapter(adapter);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void loadData() {
        modle.KaiYuan("0", new MyCallBack() {
            @Override
            public void onSuccess(String response) {
                XStream xStream = new XStream();
                xStream.alias("oschina", RuanJianBean.class);
                xStream.alias("softwareType", RuanJianBean.SoftwareTypeBean.class);
                RuanJianBean bean = (RuanJianBean) xStream.fromXML(response);
                mList.addAll(bean.getSoftwareTypes());
                adapter.notifyDataSetChanged();

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
