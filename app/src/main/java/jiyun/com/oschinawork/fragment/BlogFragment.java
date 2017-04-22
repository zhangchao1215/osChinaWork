package jiyun.com.oschinawork.fragment;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.androidkun.PullToRefreshRecyclerView;
import com.androidkun.callback.PullToRefreshListener;
import com.thoughtworks.xstream.XStream;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import jiyun.com.oschinawork.R;
import jiyun.com.oschinawork.adapter.BlogAdapter;
import jiyun.com.oschinawork.base.BaseFragment;
import jiyun.com.oschinawork.http.NewsModle;
import jiyun.com.oschinawork.http.NewsModleImpl;
import jiyun.com.oschinawork.http.callback.MyCallBack;
import jiyun.com.oschinawork.modle.bean.BlogListBean;

/**
 * Created by Administrator on 2017/4/13.
 */

public class BlogFragment extends BaseFragment {
    private BlogAdapter adapter;
    private NewsModle modle;
    private List<BlogListBean.BlogBean> mList;
    private int Index;
    @BindView(R.id.news_PullRecycler)
    PullToRefreshRecyclerView newsPullRecycler;

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
                },2000);

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
                },2000);
            }
        });
    }

    @Override
    protected void initData() {
        modle = new NewsModleImpl();
        mList = new ArrayList<>();
        adapter = new BlogAdapter(getActivity(), mList);
        newsPullRecycler.setAdapter(adapter);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void loadData() {
        modle.Blog("latest", String.valueOf(Index), "10", new MyCallBack() {
            @Override
            public void onSuccess(String response) {
//                BlogListBean bean = (BlogListBean) TextStream.getInstance().alies("oschina", BlogListBean.class)
//                        .alies("blog", BlogListBean.BlogBean.class)
//                        .build(response, BlogListBean.class);
                XStream stream = new XStream();
                stream.alias("oschina",BlogListBean.class);
                stream.alias("blog",BlogListBean.BlogBean.class);
                BlogListBean bean = (BlogListBean) stream.fromXML(response);

                mList.addAll(bean.getBlogs());
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
