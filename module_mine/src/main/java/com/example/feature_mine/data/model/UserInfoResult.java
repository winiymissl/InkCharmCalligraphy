package com.example.feature_mine.data.model;

import com.example.common.base.BaseResult;

import java.io.Serializable;

/**
 * @Author winiymissl
 * @Date 2024-04-05 15:40
 * @Version 1.0
 */

public class UserInfoResult extends BaseResult implements Serializable {

    private DataDTO data;

    public DataDTO getData() {
        return data;
    }

    public UserInfoResult(DataDTO dataDTO) {
        super();
    }


    public void setData(DataDTO data) {
        this.data = data;
    }

    public static class DataDTO implements Serializable {
        public DataDTO(Integer id, String nickName, String account, String email, String avatarBackground, String backgroundImage, String phone, Integer postCount, Integer followCount, Integer fansCount, Integer likeCount, Integer pointCount) {
            this.id = id;
            this.nick_name = nickName;
            this.account = account;
            this.email = email;
            this.avatar_background = avatarBackground;
            this.background_image = backgroundImage;
            this.phone = phone;
            this.post_count = postCount;
            this.follow_count = followCount;
            this.fans_count = fansCount;
            this.like_count = likeCount;
            this.point_count = pointCount;
        }

        private int id;
        private String nick_name;
        private String account;
        private String email;
        private String avatar_background;
        private String background_image;
        private String phone;
        private int post_count;
        private int follow_count;
        private int fans_count;
        private int like_count;
        private int point_count;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getNick_name() {
            return nick_name;
        }

        public void setNick_name(String nick_name) {
            this.nick_name = nick_name;
        }

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getAvatar_background() {
            return avatar_background;
        }

        public void setAvatar_background(String avatar_background) {
            this.avatar_background = avatar_background;
        }

        public String getBackground_image() {
            return background_image;
        }

        public void setBackground_image(String background_image) {
            this.background_image = background_image;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public int getPost_count() {
            return post_count;
        }

        public void setPost_count(int post_count) {
            this.post_count = post_count;
        }

        public int getFollow_count() {
            return follow_count;
        }

        public void setFollow_count(int follow_count) {
            this.follow_count = follow_count;
        }

        public int getFans_count() {
            return fans_count;
        }

        public void setFans_count(int fans_count) {
            this.fans_count = fans_count;
        }

        public int getLike_count() {
            return like_count;
        }

        public void setLike_count(int like_count) {
            this.like_count = like_count;
        }

        public int getPoint_count() {
            return point_count;
        }

        public void setPoint_count(int point_count) {
            this.point_count = point_count;
        }
    }
}