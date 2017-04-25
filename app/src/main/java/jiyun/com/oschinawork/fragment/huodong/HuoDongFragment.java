package jiyun.com.oschinawork.fragment.huodong;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
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
import jiyun.com.oschinawork.adapter.huodong.HuoDongAdapter;
import jiyun.com.oschinawork.base.BaseFragment;
import jiyun.com.oschinawork.http.NewsModle;
import jiyun.com.oschinawork.http.NewsModleImpl;
import jiyun.com.oschinawork.http.callback.MyCallBack;
import jiyun.com.oschinawork.modle.bean.XianXiaHuoDongBean;

/**
 * Created by Administrator on 2017/4/22.
 */

public class HuoDongFragment extends BaseFragment {
    @BindView(R.id.HD_Recycler)
    PullToRefreshRecyclerView HDRecycler;
    Unbinder unbinder;
    private NewsModle modle;
    private List<XianXiaHuoDongBean.EventBean> mList;
    private HuoDongAdapter adapter;
   private int Index = 0;
    @Override
    protected int layoutId() {
        return R.layout.activity_huodong_recycler;
    }

    @Override
    protected void initView(View view) {
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        HDRecycler.setLayoutManager(manager);
        HDRecycler.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
        HDRecycler.setPullToRefreshListener(new PullToRefreshListener() {
            @Override
            public void onRefresh() {
                HDRecycler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        HDRecycler.setRefreshComplete();
                        mList.clear();
                        loadData();
                    }
                }, 2000);
            }

            @Override
            public void onLoadMore() {
                HDRecycler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        HDRecycler.setLoadMoreComplete();
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
        adapter = new HuoDongAdapter(getContext(), mList);
        HDRecycler.setAdapter(adapter);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void loadData() {
        modle.HuoDong(String.valueOf(Index),"0", new MyCallBack() {
            @Override
            public void onSuccess(String response) {
                XStream stream = new XStream();
                stream.alias("oschina", XianXiaHuoDongBean.class);
                stream.alias("event", XianXiaHuoDongBean.EventBean.class);
                XianXiaHuoDongBean bean = (XianXiaHuoDongBean) stream.fromXML(response);
                mList.addAll(bean.getEvents());
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
