package jiyun.com.oschinawork.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import jiyun.com.oschinawork.App;
import jiyun.com.oschinawork.R;
import jiyun.com.oschinawork.activity.MainActivity;
import jiyun.com.oschinawork.base.BaseFragment;
import jiyun.com.oschinawork.config.ConfigFragment;
import jiyun.com.oschinawork.fragment.huodong.HuoDongFragment;
import jiyun.com.oschinawork.fragment.kaiyuansoftware.KYSoftware;

/**
 * Created by Administrator on 2017/4/13.
 */

public class FaxianFragment extends BaseFragment {
    @BindView(R.id.Git)
    ImageView Git;
    @BindView(R.id.Soft)
    ImageView Soft;
    @BindView(R.id.KaiYuan_Text)
    TextView KaiYuanText;
    @BindView(R.id.scan)
    ImageView scan;
    @BindView(R.id.shake)
    ImageView shake;
    @BindView(R.id.nearby)
    ImageView nearby;
    @BindView(R.id.event)
    ImageView event;
    Unbinder unbinder;
    @BindView(R.id.KY_Intent)
    RelativeLayout KYIntent;
    Unbinder unbinder1;
    @BindView(R.id.HuoDong_XianXia)
    RelativeLayout HuoDongXianXia;
    Unbinder unbinder2;
    Unbinder unbinder3;

    @Override
    protected int layoutId() {
        return R.layout.disfragment;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initData() {

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
    }

    @Override
    protected void unTitleBar() {
        if (App.activity instanceof MainActivity) {
            //显示
            ((MainActivity) App.activity).getMainTitleBar().setVisibility(View.VISIBLE);
            ((MainActivity) App.activity).getMainRadioGroup().setVisibility(View.VISIBLE);

        }
        if (App.activity instanceof MainActivity) {
            ((MainActivity) App.activity).getTitleText().setText("发现");
        }
    }

    @Override
    public void setParams(Bundle bundle) {
    }


    @OnClick({R.id.KY_Intent, R.id.HuoDong_XianXia})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.KY_Intent:
                ConfigFragment.getInstance().init().start(KYSoftware.class).build();

                break;
            case R.id.HuoDong_XianXia:
                ConfigFragment.getInstance().init().start(HuoDongFragment.class).build();
                break;
        }
    }


}
