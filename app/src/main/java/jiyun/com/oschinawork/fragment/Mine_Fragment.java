package jiyun.com.oschinawork.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import jiyun.com.oschinawork.App;
import jiyun.com.oschinawork.R;
import jiyun.com.oschinawork.activity.LoginActivity;
import jiyun.com.oschinawork.activity.MainActivity;
import jiyun.com.oschinawork.base.BaseFragment;

/**
 * Created by Administrator on 2017/4/13.
 */

public class Mine_Fragment extends BaseFragment {
    @BindView(R.id.MinImageView)
    ImageView MinImageView;
    @BindView(R.id.message)
    ImageView message;
    @BindView(R.id.blog)
    ImageView blog;
    @BindView(R.id.event)
    ImageView event;
    @BindView(R.id.question)
    ImageView question;
    @BindView(R.id.team)
    ImageView team;
    Unbinder unbinder;
    @BindView(R.id.Mine_TextView)
    TextView MineTextView;
    Unbinder unbinder1;
    private SharedPreferences mShared;
    private SharedPreferences.Editor mEditor;

    @Override
    protected int layoutId() {
        return R.layout.mine_fragment;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initData() {
        mShared = getActivity().getSharedPreferences("data", Context.MODE_PRIVATE);
        mEditor = mShared.edit();
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void onHiddn() {
        unTitleBar();
    }

    @Override
    protected void show() {
        unTitleBar();
        String name = mShared.getString("userName", "");
        String port = mShared.getString("port", "");
        MineTextView.setText(name);
        Glide.with(getActivity()).load(port).asBitmap().centerCrop().into(new BitmapImageViewTarget(MinImageView) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable ciDrawable = RoundedBitmapDrawableFactory.create(getActivity().getResources(), resource);
                ciDrawable.setCircular(true);
                MinImageView.setImageDrawable(ciDrawable);
            }
        });
    }

    @Override
    protected void unTitleBar() {
        if (App.activity instanceof MainActivity) {
            ((MainActivity) App.activity).getMainTitleBar().setVisibility(View.GONE);
        }
    }

    @Override
    public void setParams(Bundle bundle) {

    }


    @OnClick(R.id.MinImageView)
    public void onViewClicked() {
        Intent intent = new Intent(getActivity().getApplication().getApplicationContext(), LoginActivity.class);
        startActivity(intent);
    }

}
