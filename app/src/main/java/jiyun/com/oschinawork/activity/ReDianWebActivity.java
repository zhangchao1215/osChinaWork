package jiyun.com.oschinawork.activity;

import android.os.Bundle;
import android.webkit.WebView;

import butterknife.BindView;
import butterknife.ButterKnife;
import jiyun.com.oschinawork.R;
import jiyun.com.oschinawork.base.BaseActivity;

/**
 * Created by Administrator on 2017/4/18.
 */

public class ReDianWebActivity extends BaseActivity {
    @BindView(R.id.News_WebView)
    WebView NewsWebView;

    @Override
    protected int getLayoutId() {
        return R.layout.webview_activity;
    }

    @Override
    protected void init() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
