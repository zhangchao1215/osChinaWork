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

    @Override
    protected void loadData() {
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
                    Log.d("LoginActivity", bean.getUser().getUid());
                    Toast.makeText(LoginActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
                    mShared = getSharedPreferences("data", MODE_PRIVATE);
                    mEditor = mShared.edit();
                    mEditor.putString("sendMsg", bean.getUser().getUid());
                    mEditor.commit();
                    onBackPressed();
                }

                @Override
                public void onError(String error) {

                }
            });
        }

    }

//   private void Post(String url, Map<String, String> params) {
//        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
//        FormBody.Builder builder = null;
//        if (params != null & params.size() > 0) {
//            builder = new FormBody.Builder();
//            Set<String> keySet = params.keySet();
//            for (String key : keySet) {
//                builder.add(key, params.get(key));
//            }
//
//        }
//        Request request = new Request.Builder()
//                .url(url)
//                .post(builder.build())
//                .build();
//        okHttpClient.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//
//                String string = response.body().string();
//                Log.d("LoginActivity", string);
//                XStream stream = new XStream();
//                stream.alias("oschina", LoginBean.class);
//                bean = (LoginBean) stream.fromXML(string);
//                Log.d("LoginActivity", bean.getUser().getUid());
//                saveCookie(response);
//
//            }
//        });
//    }


    /**
     * 点击登陆做的操作，解析数据
     */
    public void getListener() {
        name = LoginEditName.getText().toString().trim();
        pwd = LoginEditPwd.getText().toString().trim();
        if (name.isEmpty() && pwd.isEmpty()) {
            Toast.makeText(this, "请输入内容", Toast.LENGTH_SHORT).show();
        } else {
//            String url = "http://www.oschina.net/action/api/login_validate";
//            Map<String, String> params = new HashMap<>();
//            params.put("username", name);
//            params.put("pwd", pwd);
//            params.put("keep_login", "1");
//            Post(url, params);
//            onBackPressed();
        }
    }

    //返回回退栈方法
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


//    /**
//     * 在响应头中得到cookie
//     * 得到cookie,遍历里面的信息
//     *
//     * @param response
//     */
//    public void saveCookie(Response response) {
//        cookie = "";
//        Headers headers = response.headers();
//        Set<String> names = headers.names();
//        for (String key : names) {
//            String value = headers.get(key);
//            if (key.contains("Set-Cookie")) {
//                cookie += value + ";";
//            }
//            if (cookie.length() > 0) {
//                cookie = cookie.substring(0, cookie.length() - 1);
//
//            }
//        }
//         mShared =getSharedPreferences("data",MODE_PRIVATE);
//        mEditor = mShared.edit();
//        mEditor.putString("cookie", cookie);
//        mEditor.commit();
//
//    }
//
//
//    /**
//     * 保存数据
//     *
//     * @return
//     */
//    public String getCoodie() {
//        String cookie="";
//        mShared = getSharedPreferences("data",MODE_PRIVATE);
//        cookie = mShared.getString("cookie", "");
//        if(!cookie.isEmpty()){
//            runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    Toast.makeText(LoginActivity.this, "cookie得到了", Toast.LENGTH_SHORT).show();
//                }
//            });
//
//        }
//
//
//        return cookie;
//    }

    /**
     * 点击事件
     *
     * @param view
     */
    @OnClick({R.id.Login_But, R.id.Register_Login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.Login_But:
                loadData();
//                getListener();
                break;
            case R.id.Register_Login:
//                myUid();
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
