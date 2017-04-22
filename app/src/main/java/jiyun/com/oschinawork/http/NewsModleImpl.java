package jiyun.com.oschinawork.http;

import android.text.TextUtils;

import java.util.HashMap;
import java.util.Map;

import jiyun.com.oschinawork.http.callback.MyCallBack;

/**
 * Created by Administrator on 2017/4/12.
 */

public class NewsModleImpl implements NewsModle {
    /*
    新闻资讯
     */
    @Override
    public void Post(String catalog, String pageIndex, String pageSize, MyCallBack callBask) {
        Map<String, String> params = new HashMap<>();

        params.put("catalog", catalog);
        params.put("pageIndex", pageIndex);
        params.put("pageSize", pageSize);
        HttpFactroy.create().Get(UrlUtils.getNews, params, callBask);
    }

    /*
    热点
     */
    @Override
    public void reDian(String catalog, String pageIndex, String pageSize, String show, MyCallBack callBask) {
        Map<String, String> params = new HashMap<>();
        params.put("catalog", catalog);
        params.put("pageIndex", pageIndex);
        params.put("pageSize", pageSize);
        params.put("show", show);
        HttpFactroy.create().Get(UrlUtils.getRedian, params, callBask);
    }
    /*
    博客
     */

    @Override
    public void Blog(String type, String pageIndex, String pageSize, MyCallBack callBask) {
        Map<String, String> params = new HashMap<>();
        params.put("type", type);
        params.put("pageIndex", pageIndex);
        params.put("pageSize", pageSize);
        HttpFactroy.create().Get(UrlUtils.getBlog, params, callBask);
    }

    /*
    博客详情
     */
    @Override
    public void BlogDetail(String id, MyCallBack callBack) {
        Map<String, String> params = new HashMap<>();
        params.put("id", id);
        HttpFactroy.create().Get(UrlUtils.getBlogDetail, params, callBack);
    }

    /*
    推荐
     */
    @Override
    public void Blog_List(String type, String pageIndex, String pageSize, MyCallBack callBask) {
        Map<String, String> params = new HashMap<>();
        params.put("type", type);
        params.put("pageIndex", pageIndex);
        params.put("pageSize", pageSize);
        HttpFactroy.create().Get(UrlUtils.getBlog, params, callBask);
    }

    /*
    新闻详情
     */
    @Override
    public void NewsDetail(String id, MyCallBack callBask) {
        Map<String, String> map = new HashMap<>();
        map.put("id", id);
        HttpFactroy.create().Get(UrlUtils.getDetail, map, callBask);

    }

    /*
    搜索
     */
    @Override
    public void getSearch(String catalog, String content, String pageIndex, String pageSize, MyCallBack callBask) {
        Map<String, String> map = new HashMap<>();
        map.put("catalog", catalog);
        map.put("content", content);
        map.put("pageIndex", pageIndex);
        map.put("pageSize", pageSize);
        HttpFactroy.create().Get(UrlUtils.getSearch, map, callBask);

    }

    //动弹
    @Override
    public void getNewTweet(String uid, String pageIndex, String pageSize, MyCallBack callBask) {
        Map<String, String> map = new HashMap<>();
        map.put("uid", uid);
        map.put("pageIndex", pageIndex);
        map.put("pageSize", pageSize);
        HttpFactroy.create().Get(UrlUtils.getNewDT, map, callBask);


    }

    /**
     * 发表动弹
     * @param uid
     * @param msg
     * @param img
     * @param amr
     * @param callBack
     */
    @Override
    public void sendMsg(String uid, String msg, String img, String amr, MyCallBack callBack) {
        Map<String,String> map = new HashMap<>();
        map.put("uid",uid);
        map.put("msg",msg);
        map.put("img",img);
        map.put("amr",amr);
        HttpFactroy.create().Post(UrlUtils.sendTweet,map,callBack);
    }

    /*
    登陆
     */
    @Override
    public void getLogin(String username, String pwd, String login, MyCallBack callbask) {
        if (TextUtils.isEmpty(username) && TextUtils.isEmpty(pwd)) {
            return;
        }
        Map<String, String> map = new HashMap<>();
        map.put("username", username);
        map.put("pwd", pwd);
        map.put("keep_login", "1");
        HttpFactroy.create().Post(UrlUtils.Login, map, callbask);

    }

    /**
     * 获取用户信息
     *
     * @param uid
     * @param callBack
     */
    @Override
    public void getUserName(String uid, MyCallBack callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("uid", uid);
        HttpFactroy.create().Post(UrlUtils.getUserName, map, callBack);
    }
 /*
    开源软件
     */

    @Override
    public void KaiYuan(String type, MyCallBack callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("type", type);
        HttpFactroy.create().Get(UrlUtils.kaiYuan, map, callBack);
    }

    @Override
    public void KaiYuanTag(String tag, MyCallBack callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("tag", tag);
        HttpFactroy.create().Get(UrlUtils.kaiYuan, map, callBack);
    }

    @Override
    public void KY_Second(String searchTag, String pageIndex, String pageSize, MyCallBack callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("searchTag", searchTag);
        map.put("pageIndex", pageIndex);
        map.put("pageSize", pageSize);
        HttpFactroy.create().Get(UrlUtils.KY_Second, map, callBack);
    }

    @Override
    public void KaiYuanTJ(String searchTag, String pageIndex, String pageSize, MyCallBack callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("searchTag", searchTag);
        map.put("pageIndex", pageIndex);
        map.put("pageSize", pageSize);
        HttpFactroy.create().Get(UrlUtils.KY_Tuijian, map, callBack);
    }

    /**
//     * @param name搜索中的找人
//     * @param callBack接口的回调
     */
    @Override
    public void SerachPeople(String name, MyCallBack callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("name", name);
        HttpFactroy.create().Get(UrlUtils.SerachPeople, map, callBack);
    }


}
