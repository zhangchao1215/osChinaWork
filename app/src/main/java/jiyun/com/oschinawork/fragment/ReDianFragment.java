package jiyun.com.oschinawork.fragment;

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
import butterknife.ButterKnife;
import butterknife.Unbinder;
import jiyun.com.oschinawork.R;
import jiyun.com.oschinawork.adapter.NewsConterAdapter;
import jiyun.com.oschinawork.base.BaseFragment;
import jiyun.com.oschinawork.http.NewsModle;
import jiyun.com.oschinawork.http.NewsModleImpl;
import jiyun.com.oschinawork.http.callback.MyCallBack;
import jiyun.com.oschinawork.modle.bean.NewsListBean;

/**
 * Created by Administrator on 2017/4/13.
 */

public class ReDianFragment extends BaseFragment {
    @BindView(R.id.news_PullRecycler)
    PullToRefreshRecyclerView newsPullRecycler;
    Unbinder unbinder;
    private List<NewsListBean.NewsBean> mList;
    private NewsModle modle;
    private NewsConterAdapter adapter;
    private int Index = 0;

    @Override
    protected int layoutId() {
        return R.layout.news_contentfragment;
    }

    @Override
    protected void initView(View view) {
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        newsPullRecycler.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));

        newsPullRecycler.setLayoutManager(manager);
        newsPullRecycler.setPullRefreshEnabled(true);//刷新
        newsPullRecycler.setLoadingMoreEnabled(true);//加载
        newsPullRecycler.setPullToRefreshListener(new PullToRefreshListener() {
            @Override
            public void onRefresh() {
                newsPullRecycler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        newsPullRecycler.setRefreshComplete();
                        mList.clear();
                        initData();
                    }
                }, 2000);

            }

            @Override
            public void onLoadMore() {
                newsPullRecycler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        newsPullRecycler.setLoadMoreComplete();
                        Index++;
                        loadData();
                    }
                }, 2000);
            }
        });
    }

    @Override
    protected void initData() {
        mList = new ArrayList<>();
        modle = new NewsModleImpl();
        adapter = new NewsConterAdapter(getActivity(), mList);
        newsPullRecycler.setAdapter(adapter);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void loadData() {
        modle.reDian("5", String.valueOf(Index), "5", "", new MyCallBack() {
            @Override
            public void onSuccess(String response) {
                XStream stream = new XStream();
                stream.alias("oschina", NewsListBean.class);
                stream.alias("news", NewsListBean.NewsBean.class);
                NewsListBean build = (NewsListBean) stream.fromXML(response);
                mList.addAll(build.getNewslist());
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
