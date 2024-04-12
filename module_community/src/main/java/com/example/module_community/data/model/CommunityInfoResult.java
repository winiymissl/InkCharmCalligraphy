package com.example.module_community.data.model;

import com.example.common.base.BaseResult;

import java.io.Serializable;
import java.util.List;

/**
 * @Author winiymissl
 * @Date 2024-04-12 1:06
 * @Version 1.0
 */
public class CommunityInfoResult extends BaseResult implements Serializable {
    public DataDTO getData() {
        return data;
    }

    public CommunityInfoResult(DataDTO data) {
        this.data = data;
    }

    public void setData(DataDTO data) {
        this.data = data;
    }

    private DataDTO data;

    public static class DataDTO implements Serializable {
        private List<PostDataDTO> postData;

        public DataDTO(List<PostDataDTO> postData) {
            this.postData = postData;
        }

        public static class PostDataDTO implements Serializable {
            public PostDataDTO(Integer id,
                               Integer userId,
                               Integer contentCount,
                               Integer likeCount,
                               Integer collectCount,
                               String content,
                               Integer createTime,
                               Integer deleteTime,
                               List<String> imageUrls) {
                this.id = id;
                this.userId = userId;
                this.contentCount = contentCount;
                this.likeCount = likeCount;
                this.collectCount = collectCount;
                this.content = content;
                this.createTime = createTime;
                this.deleteTime = deleteTime;
                this.imageUrls = imageUrls;
            }

            private Integer id;
            private Integer userId;
            private Integer contentCount;
            private Integer likeCount;
            private Integer collectCount;
            private String content;
            private Integer createTime;
            private Integer deleteTime;
            private List<String> imageUrls;

            public Integer getId() {
                return id;
            }

            public void setId(Integer id) {
                this.id = id;
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

        public List<PostDataDTO> getPostData() {
            return postData;
        }

        public void setPostData(List<PostDataDTO> postData) {
            this.postData = postData;
        }
    }
}
