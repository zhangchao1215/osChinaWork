package jiyun.com.oschinawork.modle.bean.tweet;

import java.util.List;

/**
 * Created by Administrator on 2017/4/26.
 */

public class FenSiBean {

    private List<FriendBean> friends;

    public List<FriendBean> getFriends() {
        return friends;
    }

    public void setFriends(List<FriendBean> friends) {
        this.friends = friends;
    }

    public static class FriendBean {
        private String name;
        private String userid;
        private String portrait;
        private String from;
        private String expertise;
        private String gender;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getPortrait() {
            return portrait;
        }

        public void setPortrait(String portrait) {
            this.portrait = portrait;
        }

        public String getFrom() {
            return from;
        }

        public void setFrom(String from) {
            this.from = from;
        }

        public String getExpertise() {
            return expertise;
        }

        public void setExpertise(String expertise) {
            this.expertise = expertise;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }
    }
}
