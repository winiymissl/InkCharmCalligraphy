package com.example.feature_mine.data.model;

import androidx.room.Entity;

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

    public void setData(DataDTO data) {
        this.data = data;
    }

    public static class DataDTO implements Serializable {
        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
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

        public String getAvatarBackground() {
            return avatarBackground;
        }

        public void setAvatarBackground(String avatarBackground) {
            this.avatarBackground = avatarBackground;
        }

        public String getBackgroundImage() {
            return backgroundImage;
        }

        public void setBackgroundImage(String backgroundImage) {
            this.backgroundImage = backgroundImage;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public Integer getPostCount() {
            return postCount;
        }

        public void setPostCount(Integer postCount) {
            this.postCount = postCount;
        }

        public Integer getFollowCount() {
            return followCount;
        }

        public void setFollowCount(Integer followCount) {
            this.followCount = followCount;
        }

        public Integer getFansCount() {
            return fansCount;
        }

        public void setFansCount(Integer fansCount) {
            this.fansCount = fansCount;
        }

        public Integer getLikeCount() {
            return likeCount;
        }

        public void setLikeCount(Integer likeCount) {
            this.likeCount = likeCount;
        }

        public Integer getPointCount() {
            return pointCount;
        }

        public void setPointCount(Integer pointCount) {
            this.pointCount = pointCount;
        }

        private Integer id;
        private String nickName;
        private String account;
        private String email;
        private String avatarBackground;
        private String backgroundImage;
        private String phone;
        private Integer postCount;
        private Integer followCount;
        private Integer fansCount;
        private Integer likeCount;
        private Integer pointCount;
    }
}