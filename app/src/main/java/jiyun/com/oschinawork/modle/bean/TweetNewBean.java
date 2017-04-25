package jiyun.com.oschinawork.modle.bean;

import android.content.Intent;
import android.view.WindowManager;

import java.util.List;

import jiyun.com.oschinawork.activity.LoginActivity;

/**
 * Created by Administrator on 2017/4/16.
 */

public class TweetNewBean {


    private String notice;
    private String tweetCount;
    private String pagesize;
    private List<TweetBean> tweets;

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public String getTweetCount() {
        return tweetCount;
    }

    public void setTweetCount(String tweetCount) {
        this.tweetCount = tweetCount;
    }

    public String getPagesize() {
        return pagesize;
    }

    public void setPagesize(String pagesize) {
        this.pagesize = pagesize;
    }

    public List<TweetBean> getTweets() {
        return tweets;
    }

    public void setTweets(List<TweetBean> tweets) {
        this.tweets = tweets;
    }

    public static class TweetBean {
        private String id;
        private String portrait;
        private String author;
        private String authorid;
        private String body;
        private String attach;
        private String appclient;
        private String commentCount;
        private String pubDate;
        private String imgSmall;
        private String imgBig;
        private String likeCount;
        private String isLike;
        private String likeList;

        @Override
        public String toString() {
            return "TweetBean{" +
                    "id='" + id + '\'' +
                    ", portrait='" + portrait + '\'' +
                    ", author='" + author + '\'' +
                    ", authorid='" + authorid + '\'' +
                    ", body='" + body + '\'' +
                    ", attach='" + attach + '\'' +
                    ", appclient='" + appclient + '\'' +
                    ", commentCount='" + commentCount + '\'' +
                    ", pubDate='" + pubDate + '\'' +
                    ", imgSmall='" + imgSmall + '\'' +
                    ", imgBig='" + imgBig + '\'' +
                    ", likeCount='" + likeCount + '\'' +
                    ", isLike='" + isLike + '\'' +
                    ", likeList='" + likeList + '\'' +
                    '}';
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPortrait() {
            return portrait;
        }

        public void setPortrait(String portrait) {
            this.portrait = portrait;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getAuthorid() {
            return authorid;
        }

        public void setAuthorid(String authorid) {
            this.authorid = authorid;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }

        public String getAttach() {
            return attach;
        }

        public void setAttach(String attach) {
            this.attach = attach;
        }

        public String getAppclient() {
            return appclient;
        }

        public void setAppclient(String appclient) {
            this.appclient = appclient;
        }

        public String getCommentCount() {
            return commentCount;
        }

        public void setCommentCount(String commentCount) {
            this.commentCount = commentCount;
        }

        public String getPubDate() {
            return pubDate;
        }

        public void setPubDate(String pubDate) {
            this.pubDate = pubDate;
        }

        public String getImgSmall() {
            return imgSmall;
        }

        public void setImgSmall(String imgSmall) {
            this.imgSmall = imgSmall;
        }

        public String getImgBig() {
            return imgBig;
        }

        public void setImgBig(String imgBig) {
            this.imgBig = imgBig;
        }

        public String getLikeCount() {
            return likeCount;
        }

        public void setLikeCount(String likeCount) {
            this.likeCount = likeCount;
        }

        public String getIsLike() {
            return isLike;
        }

        public void setIsLike(String isLike) {
            this.isLike = isLike;
        }

        public String getLikeList() {
            return likeList;
        }

        public void setLikeList(String likeList) {
            this.likeList = likeList;
        }
    }

}
