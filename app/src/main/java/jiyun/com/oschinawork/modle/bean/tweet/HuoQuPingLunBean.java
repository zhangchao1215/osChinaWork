package jiyun.com.oschinawork.modle.bean.tweet;

import java.util.List;

/**
 * Created by Administrator on 2017/4/25.
 */

public class HuoQuPingLunBean {

    private String pagesize;
    private String allCount;
    private List<CommentBean> comments;

    public String getPagesize() {
        return pagesize;
    }

    public void setPagesize(String pagesize) {
        this.pagesize = pagesize;
    }

    public String getAllCount() {
        return allCount;
    }

    public void setAllCount(String allCount) {
        this.allCount = allCount;
    }

    public List<CommentBean> getComments() {
        return comments;
    }

    public void setComments(List<CommentBean> comments) {
        this.comments = comments;
    }

    public static class CommentBean {
        private String id;
        private String portrait;
        private String author;
        private String authorid;
        private String content;
        private String pubDate;
        private String appclient;

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

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getPubDate() {
            return pubDate;
        }

        public void setPubDate(String pubDate) {
            this.pubDate = pubDate;
        }

        public String getAppclient() {
            return appclient;
        }

        public void setAppclient(String appclient) {
            this.appclient = appclient;
        }
    }
}
