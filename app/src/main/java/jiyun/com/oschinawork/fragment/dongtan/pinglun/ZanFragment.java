package jiyun.com.oschinawork.fragment.dongtan.pinglun;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidkun.PullToRefreshRecyclerView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import jiyun.com.oschinawork.R;
import jiyun.com.oschinawork.adapter.tweet.TweetAdapter;
import jiyun.com.oschinawork.adapter.tweet.ZanAdapter;
import jiyun.com.oschinawork.base.BaseFragment;
import jiyun.com.oschinawork.modle.bean.TweetNewBean;

/**
 * Created by Administrator on 2017/4/25.
 */

public class ZanFragment extends BaseFragment {
    @BindView(R.id.dongtan_PullRecycler)
    PullToRefreshRecyclerView dongtanPullRecycler;
    private ArrayList<TweetNewBean.TweetBean.UserBean> list;
    private ZanAdapter adapter;

    public ArrayList<TweetNewBean.TweetBean.UserBean> getList() {
        adapter.notifyDataSetChanged();
        return list;
    }

    public void setList(ArrayList<TweetNewBean.TweetBean.UserBean> list) {
        this.list = list;
//        adapter.notifyDataSetChanged();

    }

    @Override
    protected int layoutId() {
        return R.layout.dongtan_activity;
    }

    @Override
    protected void initView(View view) {
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        dongtanPullRecycler.setLayoutManager(manager);

    }

    @Override
    protected void initData() {
        adapter = new ZanAdapter(getContext(), list);
        dongtanPullRecycler.setAdapter(adapter);
        getList();
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void loadData() {

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
//        ArrayList<Parcelable> like = bundle.getParcelableArrayList("like");

    }


}
