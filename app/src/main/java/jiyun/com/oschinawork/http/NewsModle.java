package jiyun.com.oschinawork.http;

import jiyun.com.oschinawork.http.callback.MyCallBack;

/**
 * Created by Administrator on 2017/4/12.
 */

public interface NewsModle {
    //新闻列表的调用
    void Post(String catalog, String pageIndex, String pageSize, MyCallBack callBask);
   //热点的调用
    void reDian(String catalog, String pageIndex, String pageSize,String show,MyCallBack callBask);
   //博客的调用
    void Blog(String type,String pageIndex,String pageSize,MyCallBack callBask);
   //博客详情

    void BlogDetail(String id,MyCallBack callBack);

    //推荐的调用
    void Blog_List(String type,String pageIndex,String pageSize,MyCallBack callBask);

    //咨询详情
    void NewsDetail(String id,MyCallBack callBask);

   //搜索调用
    void getSearch(String catalog,String content,String pageIndex,String pageSize,MyCallBack callBask);

    //最新动弹

    void getNewTweet(String uid,String pageIndex,String pageSize, MyCallBack callBask);

    /**
     * 发表动弹

     */

    void sendMsg(String uid,String msg,String img,String amr,MyCallBack callBack);

    //登陆

    void getLogin(String username,String pwd,String login,MyCallBack callbask);
/*
 用户信息
 */
    void getUserName(String uid,MyCallBack callBack);


    //开源软件

    void KaiYuan(String type,MyCallBack callBack);
    //开源二级跳转
    void KaiYuanTag(String tag,MyCallBack callBack);

    void KY_Second(String searchTag,String pageIndex,String pageSize,MyCallBack callBack);

    void KaiYuanTJ(String searchTag,String pageIndex,String pageSize,MyCallBack callBack);
   /*
   搜素中的找人

    */
    void SerachPeople(String name,MyCallBack callBack);
}
