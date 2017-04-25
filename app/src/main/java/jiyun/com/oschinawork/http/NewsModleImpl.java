package jiyun.com.oschinawork.http;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;

import java.io.IOException;
import java.util.HashMap;
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
 * Created by Administrator on 2017/4/12.
 */

public class NewsModleImpl implements NewsModle {
    private SharedPreferences mShared = App.activity.getSharedPreferences("data", Context.MODE_PRIVATE);
    private SharedPreferences.Editor mEditor = mShared.edit();
    private String cookie;

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

    /**
     * 动弹
     *
     * @param uid
     * @param pageIndex
     * @param pageSize
     * @param callBask
     */
    @Override
    public void getNewTweet(String uid, String pageIndex, String pageSize, MyCallBack callBask) {
        Map<String, String> map = new HashMap<>();
        map.put("uid", uid);
        map.put("pageIndex", pageIndex);
        map.put("pageSize", pageSize);
        HttpFactroy.create().Post(UrlUtils.getNewDT, map, callBask);


    }

    /**
     * 发表动弹
     *
     * @param uid
     * @param msg
     * @param img
     * @param amr
     * @param callBack
     */
    @Override
    public void sendMsg(String uid, String msg, String img, String amr, MyCallBack callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("uid", uid);
        map.put("msg", msg);
        map.put("img", img);
        map.put("amr", amr);
        HttpFactroy.create().Post(UrlUtils.sendTweet, map, callBack);
    }

    /**
     * /**
     * 点赞
     *
     * @param tweetid      动弹的id
     * @param uid          用户的id
     * @param ownerOfTweet 动弹的作者的id
     * @param callBack
     */
    @Override
    public void DianZan(String tweetid, String uid, String ownerOfTweet, MyCallBack callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("tweetid", tweetid);
        map.put("uid", uid);
        map.put("ownerOfTweet", ownerOfTweet);
        HttpFactroy.create().Post(UrlUtils.DianZan, map, callBack);

    }

    /**
     * 动弹评论
     * @param catalog
     * @param id
     * @param uid
     * @param content
     * @param isPostToMyZone
     * @param callBack
     */
    @Override
    public void pinglun(String catalog, String id, String uid, String content, String isPostToMyZone, MyCallBack callBack) {
       Map<String,String> params = new HashMap<>();
        params.put("catalog","3");
        params.put("id",id);
        params.put("uid",uid);
        params.put("content",content);
        params.put("isPostToMyZone","0");
        HttpFactroy.create().Post(UrlUtils.Pinglun,params,callBack);

    }

    /*
    登陆
     */
    @Override
    public void getLogin(String username, String pwd, String login, final MyCallBack callbask) {
        if (TextUtils.isEmpty(username) && TextUtils.isEmpty(pwd)) {
            return;
        }
        Map<String, String> params = new HashMap<>();
        params.put("username", username);
        params.put("pwd", pwd);
        params.put("keep_login", login);
        HttpFactroy.create().Post(UrlUtils.Login, params, callbask);
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
     * 开源软件的详情
     *
     * @param ident
     * @param callBack
     */

    @Override
    public void KYDetail(String ident, MyCallBack callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("ident", ident);
        HttpFactroy.create().Get(UrlUtils.KY_Detail, map, callBack);
    }

    /**
     * //     * @param name搜索中的找人
     * //     * @param callBack接口的回调
     */
    @Override
    public void SerachPeople(String name, MyCallBack callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("name", name);
        HttpFactroy.create().Get(UrlUtils.SerachPeople, map, callBack);
    }

    /**
     * 活动
     *
     * @param uid
     * @param callBack
     */
    @Override
    public void HuoDong(String uid, String pageIndex, MyCallBack callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("pageIndex", pageIndex);
        map.put("uid", uid);
        map.put("pageSize", "11");
        HttpFactroy.create().Post(UrlUtils.HuoDong, map, callBack);

    }

    /**
     * 线下活动详情
     *
     * @param id
     * @param callBack
     */
    @Override
    public void HuoDongDetail(String id, MyCallBack callBack) {
        Map<String, String> params = new HashMap<>();
        params.put("id", id);
        HttpFactroy.create().Get(UrlUtils.HuoDongDetail, params, callBack);
    }


}
