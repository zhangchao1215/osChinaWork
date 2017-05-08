package jiyun.com.oschinawork.fragment.dongtan.mine;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidkun.PullToRefreshRecyclerView;
import com.thoughtworks.xstream.XStream;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import jiyun.com.oschinawork.App;
import jiyun.com.oschinawork.R;
import jiyun.com.oschinawork.activity.MainActivity;
import jiyun.com.oschinawork.adapter.tweet.FenSiAdapter;
import jiyun.com.oschinawork.base.BaseFragment;
import jiyun.com.oschinawork.http.NewsModle;
import jiyun.com.oschinawork.http.NewsModleImpl;
import jiyun.com.oschinawork.http.callback.MyCallBack;
import jiyun.com.oschinawork.modle.bean.tweet.FenSiBean;

/**
 * Created by Administrator on 2017/4/26.
 */

public class GuanZhuFragment extends BaseFragment {
    @BindView(R.id.dongtan_PullRecycler)
    PullToRefreshRecyclerView dongtanPullRecycler;
    private SharedPreferences mShared;
    private NewsModle modle;
    private List<FenSiBean.FriendBean> mList;
    private FenSiAdapter adapter;

    @Override
    protected int layoutId() {
        return R.layout.dongtan_activity;
    }

    @Override
    protected void initView(View view) {
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        dongtanPullRecycler.setLayoutManager(manager);
        dongtanPullRecycler.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
    }

    @Override
    protected void initData() {
        mList = new ArrayList<>();
        modle = new NewsModleImpl();
        adapter = new FenSiAdapter(getContext(), mList);
        dongtanPullRecycler.setAdapter(adapter);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void loadData() {
        modle.getGuanZhu("3415682", new MyCallBack() {
            @Override

            public void onSuccess(String response) {
                XStream stream = new XStream();
                stream.alias("oschina", FenSiBean.class);
                stream.alias("friend", FenSiBean.FriendBean.class);
                FenSiBean bean = (FenSiBean) stream.fromXML(response);
                mList.addAll(bean.getFriends());
                Log.d("FenSiFragment:我的关注", response);
            }

            @Override
            public void onError(String error) {

            }
        });
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
            ((MainActivity) App.activity).getMainRadioGroup().setVisibility(View.GONE);
        }
    }

    @Override
    public void setParams(Bundle bundle) {

    }

}
