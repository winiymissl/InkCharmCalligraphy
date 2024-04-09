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
    @PrimaryKey(autoGenerate = true)
    public long id;
    @ColumnInfo(name = "nickName", typeAffinity = ColumnInfo.TEXT)

    public String nickName;
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

    public Integer postCount;
    @ColumnInfo(name = "followCount", typeAffinity = ColumnInfo.TEXT)

    public Integer followCount;
    @ColumnInfo(name = "fansCount", typeAffinity = ColumnInfo.TEXT)

    public Integer fansCount;
    @ColumnInfo(name = "likeCount", typeAffinity = ColumnInfo.TEXT)

    public Integer likeCount;
    @ColumnInfo(name = "pointCount", typeAffinity = ColumnInfo.TEXT)

    public Integer pointCount;
}
