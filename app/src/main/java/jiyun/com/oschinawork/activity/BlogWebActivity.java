package jiyun.com.oschinawork.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.thoughtworks.xstream.XStream;

import java.util.stream.Stream;

import butterknife.BindView;
import butterknife.ButterKnife;
import jiyun.com.oschinawork.R;
import jiyun.com.oschinawork.base.BaseActivity;
import jiyun.com.oschinawork.http.NewsModle;
import jiyun.com.oschinawork.http.NewsModleImpl;
import jiyun.com.oschinawork.http.callback.MyCallBack;
import jiyun.com.oschinawork.modle.bean.BlogDetailBean;
import jiyun.com.oschinawork.modle.bean.BlogListBean;

/**
 * Created by Administrator on 2017/4/18.
 */

public class BlogWebActivity extends BaseActivity {
    @BindView(R.id.News_WebView)
    WebView NewsWebView;
    private NewsModle modle;
    private String id;

    @Override
    protected int getLayoutId() {
        return R.layout.webview_activity;
    }

    @Override
    protected void init() {
        modle = new NewsModleImpl();
        Intent intent = getIntent();
        id = intent.getStringExtra("BlogId");
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void loadData() {
        modle.BlogDetail(id, new MyCallBack() {
            @Override
            public void onSuccess(String response) {
                XStream stream = new XStream();
                stream.alias("oschina", BlogDetailBean.class);
                BlogDetailBean bean = (BlogDetailBean) stream.fromXML(response);
                String url = bean.getBlog().getUrl();
                 NewsWebView.loadUrl(url);
                Log.i("解析的网址是", url);
                NewsWebView.setWebViewClient(new WebViewClient());
            }

            @Override
            public void onError(String error) {

            }
        });

    }


}
