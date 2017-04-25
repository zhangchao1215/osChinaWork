package jiyun.com.oschinawork.http;

import jiyun.com.oschinawork.http.callback.MyCallBack;

/**
 * Created by Administrator on 2017/4/23.
 */

public interface LoginModle {
    void Login(String username, String pwd, String keep_login, MyCallBack callBack);
}
