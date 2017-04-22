package jiyun.com.oschinawork.fragment.dongtan;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;

import com.androidkun.PullToRefreshRecyclerView;
import com.androidkun.callback.PullToRefreshListener;
import com.thoughtworks.xstream.XStream;

import java.util.ArrayList;

import butterknife.BindView;
import jiyun.com.oschinawork.R;
import jiyun.com.oschinawork.adapter.tweet.TweetAdapter;
import jiyun.com.oschinawork.base.BaseFragment;
import jiyun.com.oschinawork.http.NewsModle;
import jiyun.com.oschinawork.http.NewsModleImpl;
import jiyun.com.oschinawork.http.callback.MyCallBack;
import jiyun.com.oschinawork.modle.bean.TweetNewBean;

/**
 * Created by Administrator on 2017/4/16.
 */

public class NewTongtan extends BaseFragment {
    @BindView(R.id.dongtan_PullRecycler)
    PullToRefreshRecyclerView dongtanPullRecycler;
    private NewsModle modle;
    private TweetAdapter adapter;
    private ArrayList<TweetNewBean.TweetBean> mList;
    private int Index =0;

    @Override
    protected int layoutId() {
        return R.layout.dongtan_activity;
    }

    @Override
    protected void initView(View view) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        dongtanPullRecycler.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
        dongtanPullRecycler.setLayoutManager(linearLayoutManager);
        dongtanPullRecycler.setPullToRefreshListener(new PullToRefreshListener() {
            @Override
            public void onRefresh() {
                dongtanPullRecycler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dongtanPullRecycler.setRefreshComplete();
                        mList.clear();
                        initData();
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
        adapter = new TweetAdapter(getActivity().getApplication(), mList);
        dongtanPullRecycler.setAdapter(adapter);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void loadData() {
        modle.getNewTweet("0" ,String.valueOf(Index), "10", new MyCallBack() {
            @Override
            public void onSuccess(String response) {
                XStream stream = new XStream();
                stream.alias("oschina", TweetNewBean.class);
                stream.alias("tweet", TweetNewBean.TweetBean.class);
                TweetNewBean bean = (TweetNewBean) stream.fromXML(response);
                mList.addAll(bean.getTweets());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onError(String error) {
                Log.i("错误信息是", error);
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
