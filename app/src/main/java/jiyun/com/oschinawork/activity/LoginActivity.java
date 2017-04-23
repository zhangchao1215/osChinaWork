package jiyun.com.oschinawork.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.thoughtworks.xstream.XStream;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jiyun.com.oschinawork.App;
import jiyun.com.oschinawork.R;
import jiyun.com.oschinawork.base.BaseActivity;
import jiyun.com.oschinawork.http.NewsModle;
import jiyun.com.oschinawork.http.NewsModleImpl;
import jiyun.com.oschinawork.http.callback.MyCallBack;
import jiyun.com.oschinawork.modle.bean.LoginBean;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.R.id.edit;
import static android.R.string.ok;
import static android.os.Process.myUid;

/**
 * Created by Administrator on 2017/4/16.
 */

public class LoginActivity extends BaseActivity {
    @BindView(R.id.Register_Login)
    Button RegisterLogin;
    private NewsModle modle;
    @BindView(R.id.LoginEditName)
    EditText LoginEditName;
    @BindView(R.id.LoginEditPwd)
    EditText LoginEditPwd;
    @BindView(R.id.Login_But)
    Button LoginBut;
    private SharedPreferences mShared;
    private SharedPreferences.Editor mEditor;
    private String name, pwd;
    private LoginBean bean;
    private String cookie;

    @Override
    protected int getLayoutId() {
        return R.layout.login_activity;
    }

    @Override
    protected void init() {
        modle = new NewsModleImpl();
        mShared = getSharedPreferences("data", MODE_PRIVATE);
        mEditor = mShared.edit();

    }

    @Override
    protected void initListener() {

    }

    //登陆
    private void getLogin() {
        String name = LoginEditName.getText().toString().trim();
        String pwd = LoginEditPwd.getText().toString().trim();
        if (name.isEmpty() && pwd.isEmpty()) {
            Toast.makeText(this, "请输入正确的信息", Toast.LENGTH_SHORT).show();
        } else {
            modle.getLogin(name, pwd, "keep_login", new MyCallBack() {
                @Override
                public void onSuccess(String response) {
                    XStream stream = new XStream();
                    stream.alias("oschina", LoginBean.class);
                    bean = (LoginBean) stream.fromXML(response);
                    mShared = getSharedPreferences("data", MODE_PRIVATE);
                    mEditor = mShared.edit();
                    //这是传uid的值
                    if(bean.getResult().getErrorCode().equals("1")) {
                        mEditor.putString("sendMsg", bean.getUser().getUid());
                        mEditor.putString("userName", bean.getUser().getName());
                        mEditor.putString("port", bean.getUser().getPortrait());
                        mEditor.commit();
                        Log.e("成功了呢", response);
                        Toast.makeText(LoginActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
                        onBackPressed();
                    }else if(bean.getResult().getErrorCode().equals("0")){
                        Toast.makeText(LoginActivity.this, "登陆失败", Toast.LENGTH_SHORT).show();

                    }
                }

                @Override
                public void onError(String error) {
                    Log.e("失败的是", error);
                }
            });
        }
    }


    @Override
    protected void loadData() {


    }


    //返回回退栈方法
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


//

    /**
     * 点击事件
     *
     * @param view
     */
    @OnClick({R.id.Login_But, R.id.Register_Login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.Login_But:
                getLogin();
                break;
            case R.id.Register_Login:
                getUser();
                break;
        }
    }

    private void getUser() {
        modle.getUserName(bean.getUser().getUid(), new MyCallBack() {
            @Override
            public void onSuccess(String response) {
                Log.d("LoginActivity+我的用户信息是", response);
            }

            @Override
            public void onError(String error) {

            }
        });


    }


    /**
     * 得到用户信息
     *
     * 自己呀要有思想   老师的技术咱们学不来
     */
//    private void myUid() {
//        String url = "http://www.oschina.net/action/api/my_information?uid="+bean.getUser().getUid();
//        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
//        Request request = new Request.Builder().addHeader("Cookie", getCoodie()).url(url).build();
//        okHttpClient.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                Log.d("LoginActivity:获取用户信息", response.body().string());
//            }
//        });
//    }


}
