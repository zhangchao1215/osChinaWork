package jiyun.com.oschinawork.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.TabLayout;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jiyun.com.oschinawork.App;
import jiyun.com.oschinawork.R;
import jiyun.com.oschinawork.adapter.tweet.DongTan_PagerAdapter;
import jiyun.com.oschinawork.base.BaseActivity;
import jiyun.com.oschinawork.base.BaseFragment;
import jiyun.com.oschinawork.fragment.dongtan.pinglun.ItemPingLunFragment;
import jiyun.com.oschinawork.fragment.dongtan.pinglun.ZanFragment;
import jiyun.com.oschinawork.http.NewsModle;
import jiyun.com.oschinawork.http.NewsModleImpl;
import jiyun.com.oschinawork.http.callback.MyCallBack;
import jiyun.com.oschinawork.modle.bean.TweetNewBean;

/**
 * Created by Administrator on 2017/4/23.
 */

public class TweetDeatil extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.action_bar)
    TextView actionBar;
    @BindView(R.id.item_dongtanxiangqing_author_head)
    ImageView itemDongtanxiangqingAuthorHead;
    @BindView(R.id.item_dongtanxiangqing_author_name)
    TextView itemDongtanxiangqingAuthorName;
    @BindView(R.id.item_dongtanxiangqing_author_body)
    TextView itemDongtanxiangqingAuthorBody;
    @BindView(R.id.head_)
    RelativeLayout head;
    @BindView(R.id.item_dongtanxiangqing_author_date)
    TextView itemDongtanxiangqingAuthorDate;
    @BindView(R.id.item_dongtanxiangqing_author_phone)
    TextView itemDongtanxiangqingAuthorPhone;
    @BindView(R.id.item_dongtanxiangqing_author_zhuanfa)
    ImageView itemDongtanxiangqingAuthorZhuanfa;
    @BindView(R.id.item_dongtanxiangqing_author_pinlun)
    ImageView itemDongtanxiangqingAuthorPinlun;
    @BindView(R.id.item_dongtanxiangqing_author_zan)
    ImageView itemDongtanxiangqingAuthorZan;
    @BindView(R.id.item_dongtanxiangqing_bottom)
    RelativeLayout itemDongtanxiangqingBottom;
    @BindView(R.id.item_dongtanxiangqing_tab)
    TabLayout itemDongtanxiangqingTab;
    @BindView(R.id.item_dongtanxiangqing_viewpager)
    ViewPager itemDongtanxiangqingViewpager;
    @BindView(R.id.Tweet_PinLun_edit)
    EditText TweetPinLunEdit;
    @BindView(R.id.dongtan_lin)
    RelativeLayout dongtanLin;
    @BindView(R.id.TweetDetail_Back)
    ImageView TweetDetailBack;
    private NewsModle modle;
    private SharedPreferences mShared;
    private  Intent  intent;
    private String id;
    private Dialog dialog;
    private EditText editText;
    private DongTan_PagerAdapter adapter;
    private List<String> mListName;
    private List<BaseFragment> mList;
    private ZanFragment zan;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_tweet_pinlun;
    }

    @Override
    protected void init() {
        getDailog();
        modle = new NewsModleImpl();
        mShared = getSharedPreferences("data", MODE_PRIVATE);
        TweetPinLunEdit.setFocusable(false);
       intent = getIntent();
        String name = intent.getStringExtra("tweet_name");
        itemDongtanxiangqingAuthorName.setText(name);
        String body = intent.getStringExtra("tweet_body");
        itemDongtanxiangqingAuthorBody.setText(body);
        String image = intent.getStringExtra("tweet_image");
        id = intent.getStringExtra("tweet_id");

        Glide.with(this).load(image).asBitmap().centerCrop().into(new BitmapImageViewTarget(itemDongtanxiangqingAuthorHead) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable ciDrawable = RoundedBitmapDrawableFactory.create(App.activity.getResources(), resource);
                ciDrawable.setCircular(true);
                view.setImageDrawable(ciDrawable);
            }
        });
        mListName = new ArrayList<>();
        mList = new ArrayList<>();


    }

    @Override
    protected void initListener() {
        TweetPinLunEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });
    }

    @Override
    protected void loadData() {
        zan = new ZanFragment();
        mListName.add("赞(0)");
        mListName.add("评论(0)");
        ArrayList<TweetNewBean.TweetBean.UserBean> lists = intent.getParcelableArrayListExtra("lists");
        zan.setList(lists);
        mList.add(zan);
        mList.add(new ItemPingLunFragment());
        adapter = new DongTan_PagerAdapter(getSupportFragmentManager(), mListName, mList);
        itemDongtanxiangqingViewpager.setAdapter(adapter);
        itemDongtanxiangqingTab.setupWithViewPager(itemDongtanxiangqingViewpager);
    }

    private void sendMsg() {
        modle.pinglun("3", id, mShared.getString("sendMsg", ""), editText.getText().toString(), "0", new MyCallBack() {
            @Override
            public void onSuccess(String response) {
//                Toast.makeText(TweetDeatil.this, response, Toast.LENGTH_SHORT).show();
                Log.d("TweetDeatil", response);
                dialog.dismiss();
            }

            @Override
            public void onError(String error) {

            }
        });


    }

    /**
     * 弹出dialog
     */
    private void getDailog() {
        View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.activity_dialog_item, null);
        editText = (EditText) view.findViewById(R.id.Tweet_Dialog_edit);
        Button but = (Button) view.findViewById(R.id.Tweet_Dialog_but);
        but.setOnClickListener(this);
        dialog = new AlertDialog.Builder(App.activity)
                .setTitle("发表消息")
                .setView(view)
                .create();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.Tweet_Dialog_but:
                if (editText.getText().toString().isEmpty()) {
                    Toast.makeText(this, "不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    sendMsg();
                }
                break;
        }
    }


    @OnClick(R.id.TweetDetail_Back)
    public void onViewClicked() {
        onBackPressed();
    }

    /**
     * 返回
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
