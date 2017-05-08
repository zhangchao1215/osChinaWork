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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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
import jiyun.com.oschinawork.config.ConfigFragment;
import jiyun.com.oschinawork.fragment.dongtan.MyDongTan;
import jiyun.com.oschinawork.fragment.dongtan.mine.FenSiFragment;
import jiyun.com.oschinawork.fragment.dongtan.mine.GuanZhuFragment;

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
    @BindView(R.id.Mine_Tweet)
    TextView MineTweet;
    @BindView(R.id.Mine_ShouCang)
    TextView MineShouCang;
    @BindView(R.id.Mine_Guanzhu)
    TextView MineGuanzhu;
    @BindView(R.id.Mine_Fensi)
    TextView MineFensi;
    @BindView(R.id.Mine_Shezhi)
    ImageView MineShezhi;
    @BindView(R.id.MineLinearlayout)
    LinearLayout MineLinearlayout;
    Unbinder unbinder2;
    private SharedPreferences mShared;
    private SharedPreferences.Editor mEditor;
    private String uid;

    @Override
    protected int layoutId() {
        return R.layout.mine_fragment;
    }

    @Override
    protected void initView(View view) {
        mShared = getActivity().getSharedPreferences("data", Context.MODE_PRIVATE);
        mEditor = mShared.edit();
        uid = mShared.getString("sendMsg", "");
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
    }

    @Override
    protected void loadData() {
        getHead();

    }

    private void getHead() {
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
    protected void onHiddn() {
        unTitleBar();
    }

    @Override
    protected void show() {
        getHead();
        unTitleBar();

    }

    @Override
    protected void unTitleBar() {
        if (App.activity instanceof MainActivity) {
            ((MainActivity) App.activity).getMainTitleBar().setVisibility(View.GONE);
            ((MainActivity) App.activity).getMainRadioGroup().setVisibility(View.VISIBLE);

        }

    }


    @Override
    public void setParams(Bundle bundle) {

    }


    @OnClick()
    public void onViewClicked() {

    }

    @OnClick({R.id.MinImageView, R.id.Mine_Tweet, R.id.Mine_ShouCang, R.id.Mine_Guanzhu, R.id.Mine_Fensi, R.id.Mine_Shezhi})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.MinImageView:
                //如果你的uid得到了就不登陆了，否则就登陆
                if (mShared.getString("sendMsg", "").isEmpty()) {
                    Intent intent = new Intent(getActivity().getApplication().getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getContext(), "你已经登陆过了", Toast.LENGTH_SHORT).show();
                    getHead();
                }
                break;

            case R.id.Mine_Tweet:
                ConfigFragment.getInstance().init().start(MyDongTan.class).build();
                break;
            case R.id.Mine_ShouCang:
                break;
            case R.id.Mine_Guanzhu:
                ConfigFragment.getInstance().init().start(FenSiFragment.class).build();

                break;
            case R.id.Mine_Fensi:
                ConfigFragment.getInstance().init().start(GuanZhuFragment.class).build();
                break;
            case R.id.Mine_Shezhi:

                if (uid.length() > 0) {
                    mShared = App.activity.getSharedPreferences("data", Context.MODE_PRIVATE);
                    mEditor = mShared.edit();
                    mEditor.remove("sendMsg");
                    MineTextView.setText("用户名");
                    MinImageView.setImageResource(R.mipmap.ic_launcher);
                    mEditor.commit();
                    Toast.makeText(getContext(), "注销成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "你还没有登陆", Toast.LENGTH_SHORT).show();
                }
                break;

        }
    }


}
