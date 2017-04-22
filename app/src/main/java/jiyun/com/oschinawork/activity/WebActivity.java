package jiyun.com.oschinawork.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.thoughtworks.xstream.XStream;

import butterknife.BindView;
import jiyun.com.oschinawork.R;
import jiyun.com.oschinawork.base.BaseActivity;
import jiyun.com.oschinawork.http.NewsModle;
import jiyun.com.oschinawork.http.NewsModleImpl;
import jiyun.com.oschinawork.http.callback.MyCallBack;
import jiyun.com.oschinawork.modle.bean.NewDetailBean;

/**
 * Created by Administrator on 2017/4/13.
 */

public class WebActivity extends BaseActivity {
    private NewsModle modle;
    @BindView(R.id.News_WebView)
    WebView NewsWebView;
    private String id;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    if (id != null) {
                        NewsWebView.loadUrl(url);
                    }
                    break;
            }
        }
    };
    private String url;

    @Override
    protected int getLayoutId() {
        return R.layout.webview_activity;
    }

    @Override
    protected void init() {
        modle = new NewsModleImpl();
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void loadData() {
        modle.NewsDetail(id, new MyCallBack() {
            @Override
            public void onSuccess(String response) {
                XStream stream = new XStream();
                stream.alias("oschina", NewDetailBean.class);
                stream.alias("relative", NewDetailBean.NewsBean.RelativeBean.class);
                NewDetailBean bean = (NewDetailBean) stream.fromXML(response);
                url = bean.getNews().getUrl();
                handler.obtainMessage(1, url).sendToTarget();


                Log.i("网址是", url);
                NewsWebView.setWebViewClient(new WebViewClient());
            }

            @Override
            public void onError(String error) {

            }
        });
    }


}
