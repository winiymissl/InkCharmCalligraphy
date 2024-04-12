package com.example.module_community.data.model.result;

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

    public void setData(DataDTO data) {
        this.data = data;
    }

    private DataDTO data;

    public static class DataDTO implements Serializable {
        public Integer getCurrentPage() {
            return currentPage;
        }

        public void setCurrentPage(Integer currentPage) {
            this.currentPage = currentPage;
        }

        public Integer getPageSize() {
            return pageSize;
        }

        public void setPageSize(Integer pageSize) {
            this.pageSize = pageSize;
        }

        public Long getOffset() {
            return offset;
        }

        public void setOffset(Long offset) {
            this.offset = offset;
        }

        public Boolean getOverflow() {
            return overflow;
        }

        public void setOverflow(Boolean overflow) {
            this.overflow = overflow;
        }

        public Integer getTotalPage() {
            return totalPage;
        }

        public void setTotalPage(Integer totalPage) {
            this.totalPage = totalPage;
        }

        public Integer getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(Integer totalCount) {
            this.totalCount = totalCount;
        }

        public List<PostDataDTO> getPostData() {
            return postData;
        }

        public void setPostData(List<PostDataDTO> postData) {
            this.postData = postData;
        }

        public DataDTO(Integer currentPage, Integer pageSize, Long offset, Boolean overflow, Integer totalPage, Integer totalCount, List<PostDataDTO> postData) {
            this.currentPage = currentPage;
            this.pageSize = pageSize;
            this.offset = offset;
            this.overflow = overflow;
            this.totalPage = totalPage;
            this.totalCount = totalCount;
            this.postData = postData;
        }

        private Integer currentPage;
        private Integer pageSize;
        private Long offset;
        private Boolean overflow;
        private Integer totalPage;
        private Integer totalCount;
        private List<PostDataDTO> postData;

        public static class PostDataDTO implements Serializable {
            public PostDataDTO(UserInfoDTO userInfo, Integer id, Integer userId, Integer contentCount, Integer likeCount, Integer collectCount, String content, Integer createTime, Integer deleteTime, List<String> imageUrls) {
                this.userInfo = userInfo;
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

            private UserInfoDTO userInfo;
            private Integer id;
            private Integer userId;
            private Integer contentCount;
            private Integer likeCount;
            private Integer collectCount;
            private String content;
            private Integer createTime;
            private Integer deleteTime;
            private List<String> imageUrls;

            public UserInfoDTO getUserInfo() {
                return userInfo;
            }

            public void setUserInfo(UserInfoDTO userInfo) {
                this.userInfo = userInfo;
            }

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

            public static class UserInfoDTO implements Serializable {
                private Integer userId;
                private String nickName;
                private String account;
                private String avatarImage;

                public UserInfoDTO(Integer userId, String nickName, String account, String avatarImage) {
                    this.userId = userId;
                    this.nickName = nickName;
                    this.account = account;
                    this.avatarImage = avatarImage;
                }

                public Integer getUserId() {
                    return userId;
                }

                public void setUserId(Integer userId) {
                    this.userId = userId;
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

                public String getAvatarImage() {
                    return avatarImage;
                }

                public void setAvatarImage(String avatarImage) {
                    this.avatarImage = avatarImage;
                }
            }
        }
    }
}
