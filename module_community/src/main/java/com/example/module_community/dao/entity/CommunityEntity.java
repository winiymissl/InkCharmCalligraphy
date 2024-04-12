package com.example.module_community.dao.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.common.base.BaseResult;
import com.example.module_community.dao.converter.ConverterImageURI;

import java.io.Serializable;
import java.util.List;

/**
 * @Author winiymissl
 * @Date 2024-04-12 0:41
 * @Version 1.0
 */
@Entity(tableName = "community_info")
public class CommunityEntity  implements Serializable {

    public CommunityEntity(int id,
                           Integer id_post,
                           Integer userId,
                           Integer contentCount,
                           Integer likeCount,
                           Integer collectCount,
                           String content,
                           Integer createTime,
                           Integer deleteTime,
                           List<String> imageUrls) {
        this.id = id;
        this.id_post = id_post;
        this.userId = userId;
        this.contentCount = contentCount;
        this.likeCount = likeCount;
        this.collectCount = collectCount;
        this.content = content;
        this.createTime = createTime;
        this.deleteTime = deleteTime;
        this.imageUrls = imageUrls;
    }

    @PrimaryKey(autoGenerate = true)
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ColumnInfo(name = "id_post", typeAffinity = ColumnInfo.INTEGER)
    private Integer id_post;
    @ColumnInfo(name = "userId", typeAffinity = ColumnInfo.INTEGER)

    private Integer userId;
    @ColumnInfo(name = "contentCount", typeAffinity = ColumnInfo.INTEGER)

    private Integer contentCount;
    @ColumnInfo(name = "likeCount", typeAffinity = ColumnInfo.INTEGER)

    private Integer likeCount;
    @ColumnInfo(name = "collectCount", typeAffinity = ColumnInfo.INTEGER)
    private Integer collectCount;
    @ColumnInfo(name = "content", typeAffinity = ColumnInfo.TEXT)
    private String content;
    @ColumnInfo(name = "createTime", typeAffinity = ColumnInfo.INTEGER)

    private Integer createTime;
    @ColumnInfo(name = "deleteTime", typeAffinity = ColumnInfo.INTEGER)
    private Integer deleteTime;

    @TypeConverters(ConverterImageURI.class)
    private List<String> imageUrls;



    public Integer getId_post() {
        return id_post;
    }

    public void setId_post(Integer id_post) {
        this.id_post = id_post;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getContentCount() {
        return contentCount;
    }

    public void setContentCount(Integer contentCount) {
        this.contentCount = contentCount;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    public Integer getCollectCount() {
        return collectCount;
    }

    public void setCollectCount(Integer collectCount) {
        this.collectCount = collectCount;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
    }

    public Integer getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(Integer deleteTime) {
        this.deleteTime = deleteTime;
    }

    public List<String> getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }
}
