package com.example.feature_mine.dao.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * @Author winiymissl
 * @Date 2024-04-09 21:43
 * @Version 1.0
 */

@Entity(tableName = "userInfo")
public class UserInfo {

    public UserInfo(String nickName,
                    String id_user,
                    String account,
                    String email,
                    String avatarBackground,
                    String backgroundImage,
                    String phone,
                    String postCount,
                    String followCount,
                    String fansCount,
                    String likeCount,
                    String pointCount) {
        this.nickName = nickName;
        this.id_user = id_user;
        this.account = account;
        this.email = email;
        this.avatarBackground = avatarBackground;
        this.backgroundImage = backgroundImage;
        this.phone = phone;
        this.postCount = postCount;
        this.followCount = followCount;
        this.fansCount = fansCount;
        this.likeCount = likeCount;
        this.pointCount = pointCount;
    }

    @PrimaryKey(autoGenerate = true)
    public long ID;


    @ColumnInfo(name = "nickName", typeAffinity = ColumnInfo.TEXT)
    public String nickName;

    @ColumnInfo(name = "id_user", typeAffinity = ColumnInfo.TEXT)
    public String id_user;

    @ColumnInfo(name = "account", typeAffinity = ColumnInfo.TEXT)
    public String account;
    @ColumnInfo(name = "email", typeAffinity = ColumnInfo.TEXT)

    public String email;
    @ColumnInfo(name = "avatarBackground", typeAffinity = ColumnInfo.TEXT)
    public String avatarBackground;
    @ColumnInfo(name = "backgroundImage", typeAffinity = ColumnInfo.TEXT)
    public String backgroundImage;

    @ColumnInfo(name = "phone", typeAffinity = ColumnInfo.TEXT)
    public String phone;

    @ColumnInfo(name = "postCount", typeAffinity = ColumnInfo.TEXT)

    public String postCount;
    @ColumnInfo(name = "followCount", typeAffinity = ColumnInfo.TEXT)

    public String followCount;
    @ColumnInfo(name = "fansCount", typeAffinity = ColumnInfo.TEXT)

    public String fansCount;
    @ColumnInfo(name = "likeCount", typeAffinity = ColumnInfo.TEXT)

    public String likeCount;
    @ColumnInfo(name = "pointCount", typeAffinity = ColumnInfo.TEXT)
    public String pointCount;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
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

    public String getPostCount() {
        return postCount;
    }

    public void setPostCount(String postCount) {
        this.postCount = postCount;
    }

    public String getFollowCount() {
        return followCount;
    }

    public void setFollowCount(String followCount) {
        this.followCount = followCount;
    }

    public String getFansCount() {
        return fansCount;
    }

    public void setFansCount(String fansCount) {
        this.fansCount = fansCount;
    }

    public String getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(String likeCount) {
        this.likeCount = likeCount;
    }

    public String getPointCount() {
        return pointCount;
    }

    public void setPointCount(String pointCount) {
        this.pointCount = pointCount;
    }

}
