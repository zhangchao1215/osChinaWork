package jiyun.com.oschinawork.http;

import java.util.Map;

import jiyun.com.oschinawork.http.callback.MyCallBack;

/**
 * Created by Administrator on 2017/4/12.
 */

public interface IHttp {
    /*
    请求数据用的方法
     */
    void Post(String url, Map<String, String> params, MyCallBack callBask);

    void Get(String url, Map<String, String> params, MyCallBack callBask);
}
