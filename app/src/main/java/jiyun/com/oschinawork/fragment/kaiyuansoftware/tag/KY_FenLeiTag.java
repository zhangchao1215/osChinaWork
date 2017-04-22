package jiyun.com.oschinawork.fragment.kaiyuansoftware.tag;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;

import com.androidkun.PullToRefreshRecyclerView;
import com.thoughtworks.xstream.XStream;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import jiyun.com.oschinawork.App;
import jiyun.com.oschinawork.R;
import jiyun.com.oschinawork.adapter.kaiyuan.KYFenleiTag_Adapter;
import jiyun.com.oschinawork.base.BaseFragment;
import jiyun.com.oschinawork.http.NewsModle;
import jiyun.com.oschinawork.http.NewsModleImpl;
import jiyun.com.oschinawork.http.callback.MyCallBack;
import jiyun.com.oschinawork.modle.bean.TagBean;

import static android.R.attr.id;

/**
 * Created by Administrator on 2017/4/16.
 */

public class KY_FenLeiTag extends BaseFragment {
    @BindView(R.id.RuanJianRecycler)
    PullToRefreshRecyclerView RuanJianRecycler;
    private KYFenleiTag_Adapter adapter;
    private List<TagBean.SoftwareTypeBean> mList;
    private NewsModle modle;
    private String tag;
    private SharedPreferences mShared;
    @Override
    protected int layoutId() {
        return R.layout.ruanjian_activity;
    }

    @Override
    protected void initView(View view) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        RuanJianRecycler.setLayoutManager(linearLayoutManager);

    }

    @Override
    protected void initData() {
        mShared = App.activity.getSharedPreferences("data", Context.MODE_PRIVATE);
        tag = mShared.getString("tag1", "");
        modle = new NewsModleImpl();
        mList = new ArrayList<>();
        adapter = new KYFenleiTag_Adapter(getActivity().getApplication(), mList);
        RuanJianRecycler.setAdapter(adapter);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void loadData() {
        modle.KaiYuanTag(tag, new MyCallBack() {
            @Override
            public void onSuccess(String response) {
                XStream xStream = new XStream();
                xStream.alias("oschina", TagBean.class);
                xStream.alias("softwareType", TagBean.SoftwareTypeBean.class);
                TagBean bean = (TagBean) xStream.fromXML(response);
                mList.addAll(bean.getSoftwareTypes());
                adapter.notifyDataSetChanged();
//                Log.e("打印的数据是",response);
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
//        tag = bundle.getString("tag");
    }


}
