package jiyun.com.oschinawork.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ComponentInfo;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jiyun.com.oschinawork.App;
import jiyun.com.oschinawork.R;
import jiyun.com.oschinawork.base.BaseActivity;
import jiyun.com.oschinawork.http.NewsModle;
import jiyun.com.oschinawork.http.NewsModleImpl;
import jiyun.com.oschinawork.http.callback.MyCallBack;

/**
 * Created by Administrator on 2017/4/23.
 */

public class TweetDeatil extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.item_newsdongtan_author_head)
    ImageView itemNewsdongtanAuthorHead;
    @BindView(R.id.item_newsdongtan_author_name)
    TextView itemNewsdongtanAuthorName;
    @BindView(R.id.item_newsdongtan_author_body)
    TextView itemNewsdongtanAuthorBody;
    @BindView(R.id.item_newsdongtan_author_ImageView)
    ImageView itemNewsdongtanAuthorImageView;
    @BindView(R.id.item_newsdongtan_author_date)
    TextView itemNewsdongtanAuthorDate;
    @BindView(R.id.item_newsdongtan_author_phone)
    TextView itemNewsdongtanAuthorPhone;
    @BindView(R.id.item_newsdongtan_author_zhuanfa)
    TextView itemNewsdongtanAuthorZhuanfa;
    @BindView(R.id.item_newsdongtan_author_pinlun)
    TextView itemNewsdongtanAuthorPinlun;
    @BindView(R.id.item_newsdongtan_author_zan)
    TextView itemNewsdongtanAuthorZan;
    @BindView(R.id.dongtan_lin)
    RelativeLayout dongtanLin;
    @BindView(R.id.item_newsdongtan_author_zanImage)
    ImageView itemNewsdongtanAuthorZanImage;
    @BindView(R.id.Tweet_PinLun_edit)
    EditText TweetPinLunEdit;
    private Dialog dialog;
    private EditText editText;
    private NewsModle modle;
    private String id;
    private SharedPreferences mShared;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_tweet_pinlun;
    }

    @Override
    protected void init() {
        getDailog();
        modle = new NewsModleImpl();
        mShared = getSharedPreferences("data",MODE_PRIVATE);
        TweetPinLunEdit.setFocusable(false);
        Intent intent = getIntent();
        String name = intent.getStringExtra("tweet_name");
        itemNewsdongtanAuthorName.setText(name);
        String body = intent.getStringExtra("tweet_body");
        itemNewsdongtanAuthorBody.setText(body);
        String image = intent.getStringExtra("tweet_image");
        id = intent.getStringExtra("tweet_id");
        Glide.with(this).load(image).asBitmap().centerCrop().into(new BitmapImageViewTarget(itemNewsdongtanAuthorHead) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable ciDrawable = RoundedBitmapDrawableFactory.create(App.activity.getResources(), resource);
                ciDrawable.setCircular(true);
                view.setImageDrawable(ciDrawable);
            }
        });

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


}
