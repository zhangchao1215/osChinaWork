package jiyun.com.oschinawork.http;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jiyun.com.oschinawork.App;
import jiyun.com.oschinawork.activity.LoginActivity;
import jiyun.com.oschinawork.http.callback.MyCallBack;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Administrator on 2017/4/10.
 */

public class OkHttpUtils implements IHttp {
    private SharedPreferences mShared;
    private SharedPreferences.Editor mEditor;
    private String cookie;

    private OkHttpUtils() {
    }

    public static OkHttpUtils okHttpUtils = new OkHttpUtils();

    public static OkHttpUtils getInstance() {

        return okHttpUtils;
    }

    /**
     * 这是请求带cookie的网络请求方式
     *
     * @param url
     * @param params
     * @param callBask
     */
    @Override
    public void Post(String url, Map<String, String> params, final MyCallBack callBask) {
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        FormBody.Builder builder = new FormBody.Builder();
        if (params != null) {
            Set<String> keySet = params.keySet();
            for (String key : keySet) {
                String value = params.get(key);
                builder.add(key, value);
            }
            Request request = new Request.Builder()
                    .url(url)
                    .post(builder.build())
                    .addHeader("Cookie", getCoodie())
                    .build();
            okHttpClient.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(final Call call, Response response) throws IOException {
                    saveCookie(response);
                    final String string = response.body().string();

                    App.activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            callBask.onSuccess(string);
                        }
                    });
                }
            });


        }
    }
        /**
         * 保存数据
         *
         * @return
         */
    public String getCoodie() {
        String cookie="";
        mShared = App.activity.getSharedPreferences("data",MODE_PRIVATE);
        cookie = mShared.getString("cookie", "");
        return cookie;

    }

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

    /**
     * 请求网络数据解析
     *
     * @param url
     * @param params
     * @param callBask
     */
    @Override
    public void Get(String url, Map<String, String> params, final MyCallBack callBask) {
        OkHttpClient client = new OkHttpClient.Builder().build();
        FormBody.Builder builder = new FormBody.Builder();
        if (params != null) {
            Set<String> keySet = params.keySet();
            for (String key : keySet) {
                String value = params.get(key);
                builder.add(key, value);

            }
            Request request = new Request.Builder()
                    .url(url)
                    .post(builder.build())
                    .build();
            Call call = client.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, final Response response) throws IOException {
                    final String str = response.body().string();
                    App.activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                callBask.onSuccess(str);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            });
        }
    }


}