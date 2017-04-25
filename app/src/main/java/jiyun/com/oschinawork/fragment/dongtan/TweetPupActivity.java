package jiyun.com.oschinawork.fragment.dongtan;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jiyun.com.oschinawork.R;
import jiyun.com.oschinawork.base.BaseActivity;
import jiyun.com.oschinawork.http.NewsModle;
import jiyun.com.oschinawork.http.NewsModleImpl;
import jiyun.com.oschinawork.http.callback.MyCallBack;
import jiyun.com.oschinawork.modle.bean.LoginBean;
import jiyun.com.oschinawork.modle.bean.db.MyManger;

/**
 * Created by Administrator on 2017/4/21.
 */

public class TweetPupActivity extends BaseActivity {
    @BindView(R.id.Tweet_SendMsg)
    TextView TweetSendMsg;
    @BindView(R.id.Tweet_EditText)
    EditText TweetEditText;
    private SharedPreferences mShared;
    private NewsModle modle;
    private String sendMsg;
    private MyManger manger;
    private String id;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_tweet_pub;
    }

    @Override
    protected void init() {
        modle = new NewsModleImpl();
        mShared = getSharedPreferences("data", MODE_PRIVATE);
        sendMsg = mShared.getString("sendMsg", "");
        manger = new MyManger(getApplicationContext());
//        id = manger.QueryUid();
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void loadData() {

    }

    private void sendMsg() {
        modle.sendMsg(sendMsg, TweetEditText.getText().toString(), "", "", new MyCallBack() {
            @Override
            public void onSuccess(String response) {
                Log.d("TweetPupActivity+我的是", response);
//                Toast.makeText(TweetPupActivity.this, response, Toast.LENGTH_SHORT).show();
                onBackPressed();
            }

            @Override
            public void onError(String error) {

            }
        });
    }


    @OnClick(R.id.Tweet_SendMsg)
    public void onViewClicked() {
        if(TweetEditText.getText().toString().isEmpty()){
            Toast.makeText(this, "请输入内容", Toast.LENGTH_SHORT).show();
        }else{
            sendMsg();
        }

    }

    //退出键
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
