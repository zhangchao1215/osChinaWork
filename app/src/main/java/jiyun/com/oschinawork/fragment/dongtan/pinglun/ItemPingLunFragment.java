package jiyun.com.oschinawork.fragment.dongtan.pinglun;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidkun.PullToRefreshRecyclerView;
import com.androidkun.callback.PullToRefreshListener;
import com.thoughtworks.xstream.XStream;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import jiyun.com.oschinawork.App;
import jiyun.com.oschinawork.R;
import jiyun.com.oschinawork.activity.MyContentLinearLayoutManager;
import jiyun.com.oschinawork.adapter.tweet.DongTanPingLunAdapter;
import jiyun.com.oschinawork.base.BaseFragment;
import jiyun.com.oschinawork.http.NewsModle;
import jiyun.com.oschinawork.http.NewsModleImpl;
import jiyun.com.oschinawork.http.callback.MyCallBack;
import jiyun.com.oschinawork.modle.bean.tweet.HuoQuPingLunBean;

import static android.R.attr.id;
import static android.R.attr.offset;
import static android.R.attr.x;

/**
 * Created by Administrator on 2017/4/25.
 */

public class ItemPingLunFragment extends BaseFragment {
    @BindView(R.id.dongtan_PullRecycler)
    PullToRefreshRecyclerView dongtanPullRecycler;
    private NewsModle modle;
    private SharedPreferences mShared;
    private DongTanPingLunAdapter adapter;
    private List<HuoQuPingLunBean.CommentBean> mList;
    private String id;
    private int Index = 0;

    @Override
    protected int layoutId() {
        return R.layout.dongtan_activity;
    }

    @Override
    protected void initView(View view) {
        dongtanPullRecycler.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        dongtanPullRecycler.setLayoutManager(new MyContentLinearLayoutManager(getContext()));
        dongtanPullRecycler.setPullToRefreshListener(new PullToRefreshListener() {
            @Override
            public void onRefresh() {
                dongtanPullRecycler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dongtanPullRecycler.setRefreshComplete();
                        mList.clear();
                        loadData();
                    }
                }, 2000);


            }

            @Override
            public void onLoadMore() {
                dongtanPullRecycler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dongtanPullRecycler.setLoadMoreComplete();
                        Index++;
                        loadData();

                    }
                }, 2000);
            }
        });
    }

    @Override
    protected void initData() {
        modle = new NewsModleImpl();
        mList = new ArrayList<>();
        mShared = App.activity.getSharedPreferences("data", Context.MODE_PRIVATE);
        id = mShared.getString("tweet_id", "");
        Log.d("ItemPingLunFragment我的id", id);
        adapter = new DongTanPingLunAdapter(getContext(), mList);
        dongtanPullRecycler.setAdapter(adapter);
        Log.d("ItemPingLunFragment", "adapter:" + adapter);

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void loadData() {
        modle.getPinlun("3", id, String.valueOf(Index), "10", new MyCallBack() {
            @Override
            public void onSuccess(String response) {
                XStream stream = new XStream();
                stream.alias("oschina", HuoQuPingLunBean.class);
                stream.alias("comment", HuoQuPingLunBean.CommentBean.class);

                HuoQuPingLunBean bean = (HuoQuPingLunBean) stream.fromXML(response);
                mList.addAll(bean.getComments());
                adapter.notifyDataSetChanged();
                Log.d("ItemPingLunFragment", response);

            }

            @Override
            public void onError(String error) {
                Log.d("ItemPingLunFragment", error);
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
