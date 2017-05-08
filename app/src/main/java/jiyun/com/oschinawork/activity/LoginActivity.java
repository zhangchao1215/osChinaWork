package jiyun.com.oschinawork.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.thoughtworks.xstream.XStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jiyun.com.oschinawork.R;
import jiyun.com.oschinawork.base.BaseActivity;
import jiyun.com.oschinawork.http.ILoginModleImpl;
import jiyun.com.oschinawork.http.LoginModle;
import jiyun.com.oschinawork.http.callback.MyCallBack;
import jiyun.com.oschinawork.modle.bean.LoginBean;
import jiyun.com.oschinawork.modle.bean.db.MyManger;

import static jiyun.com.oschinawork.R.id.LoginEditName;
import static jiyun.com.oschinawork.R.id.LoginEditPwd;

/**
 * Created by Administrator on 2017/4/16.
 */

public class LoginActivity extends BaseActivity {

    Button LoginBut;
    @BindView(R.id.login_zhanghao)
    EditText loginZhanghao;
    @BindView(R.id.login_mima)
    EditText loginMima;
    @BindView(R.id.login_denglu)
    Button loginDenglu;
    @BindView(R.id.login_zhuce)
    Button loginZhuce;
    private SharedPreferences mShared;
    private SharedPreferences.Editor mEditor;
    private String name, pwd;
    private LoginBean bean;
    private String cookie;
    private MyManger manger;
    private String name1;
    private String pwd1;
    private LoginModle modle;

    @Override
    protected int getLayoutId() {
        return R.layout.mine_login;
    }

    @Override
    protected void init() {
        modle = new ILoginModleImpl();
        manger = new MyManger(this);
        mShared = getSharedPreferences("data", MODE_PRIVATE);
        mEditor = mShared.edit();

    }

    @Override
    protected void initListener() {

    }


    @Override
    protected void loadData() {


    }

    private void Login() {
        modle.Login(loginZhanghao.getText().toString().trim(), loginMima.getText().toString().trim(), "1", new MyCallBack() {
            @Override
            public void onSuccess(String response) {
                XStream stream = new XStream();
                stream.alias("oschina", LoginBean.class);
                bean = (LoginBean) stream.fromXML(response);
                //这是传uid的值
                if (bean.getResult().getErrorCode().equals("1")) {
                    mEditor.putString("sendMsg", bean.getUser().getUid());
                    mEditor.putString("userName", bean.getUser().getName());
                    mEditor.putString("port", bean.getUser().getPortrait());
//                    manger.insert(bean.getUser().getUid(), name1, pwd1);
//                    Log.d("LoginActivity", "manger.insert(bean.getUser().getUid(), name, pwd):" + manger.insert(bean.getUser().getUid(), name1, pwd1));
                    mEditor.commit();
//                        Log.e("成功了呢", response);
                    Toast.makeText(LoginActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
                    onBackPressed();
                } else if (bean.getResult().getErrorCode().equals("0")) {
                    Toast.makeText(LoginActivity.this, "登陆失败", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onError(String error) {
                Log.e("失败的是", error);
            }
        });
    }


    //返回回退栈方法
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    /**
     * 点击事件
     *
     * @param view
     */
    @OnClick({R.id.login_denglu, R.id.login_zhuce})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_denglu:
                name1 = loginZhanghao.getText().toString().trim();
                pwd1 = loginMima.getText().toString().trim();
                if (name1.isEmpty() && pwd1.isEmpty()) {
                    Toast.makeText(this, "请输入正确的信息", Toast.LENGTH_SHORT).show();
                } else {
                    Login();
                }
                break;
            case R.id.Register_Login:
//                getUser();
                break;
        }
    }


//    private void getUser() {
//        modle.getUserName(bean.getUser().getUid(), new MyCallBack() {
//            @Override
//            public void onSuccess(String response) {
//                Log.d("LoginActivity+我的用户信息是", response);
//            }
//
//            @Override
//            public void onError(String error) {
//
//            }
//        });


}


/**
 * 得到用户信息
 * <p>
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



