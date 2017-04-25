package jiyun.com.oschinawork.activity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

import com.thoughtworks.xstream.XStream;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jiyun.com.oschinawork.R;
import jiyun.com.oschinawork.base.BaseActivity;
import jiyun.com.oschinawork.http.NewsModle;
import jiyun.com.oschinawork.http.NewsModleImpl;
import jiyun.com.oschinawork.http.callback.MyCallBack;
import jiyun.com.oschinawork.modle.bean.KY_TuiJianBean;
import jiyun.com.oschinawork.modle.bean.serach.HuoDongDetailBean;

/**
 * Created by Administrator on 2017/4/23.
 */

public class HuoDongWebView extends BaseActivity {
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
        id = intent.getStringExtra("hd_detail");
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void loadData() {
    modle.HuoDongDetail(id, new MyCallBack() {
        @Override
        public void onSuccess(String response) {
            XStream stream = new XStream();
            stream.alias("oschina", HuoDongDetailBean.class);
            stream.alias("post",HuoDongDetailBean.PostBean.class);
            HuoDongDetailBean bean = (HuoDongDetailBean) stream.fromXML(response);
            String url = bean.getPost().getUrl();
            NewsWebView.loadUrl(url);
        }

        @Override
        public void onError(String error) {

        }
    });
    }


}
