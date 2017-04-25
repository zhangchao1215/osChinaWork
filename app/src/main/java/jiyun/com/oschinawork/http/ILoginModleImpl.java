package jiyun.com.oschinawork.http;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jiyun.com.oschinawork.App;
import jiyun.com.oschinawork.http.callback.MyCallBack;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Administrator on 2017/4/23.
 */

public class ILoginModleImpl implements LoginModle {
    private String cookie;
    private SharedPreferences mShared;
    private SharedPreferences.Editor mEditor;
    @Override
    public void Login(String username, String pwd, String keep_login,final MyCallBack callBack) {
        Map<String,String> params=new HashMap<>();
        params.put("username",username);
        params.put("pwd",pwd);
        params.put("keep_login",keep_login);
        OkHttpClient okHttpClient=new OkHttpClient();
        FormBody.Builder body=new FormBody.Builder();
        if (params!=null){
            Set<String> keySet = params.keySet();
            for (String key : keySet) {
                String value = params.get(key);
                body.add(key,value);
            }



        }
        Request  request=new Request.Builder().url(UrlUtils.Login).post(body.build()).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(final Call call, IOException e) {
                App.activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        callBack.onError("失败");
                    }
                });
            }

            @Override
            public void onResponse(final Call call, final Response response) throws IOException {
                saveCookie(response);
                final String s = response.body().string();
                App.activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        callBack.onSuccess(s);
                    }
                });
            }
        });

    }



    //保持用户登录的cookie
    /**
     * 提交请求头信息
     * @param response
     */
    public void saveCookie(Response response) {
        cookie = "";
        Headers headers = response.headers();
        Set<String> names = headers.names();
        for (String name : names) {
            String value = headers.get(name);
            if (name.contains("Set-Cookie")) {
                cookie += value + ";";
            }
            if (cookie.length() > 0) {
                cookie = cookie.substring(0, cookie.length() - 1);
            }
        }
        mShared =App.activity.getSharedPreferences("data",MODE_PRIVATE);
        mEditor = mShared.edit();
        mEditor.putString("cookie", cookie);
        mEditor.commit();

    }



}

