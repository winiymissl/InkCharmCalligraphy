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

    @Override
    public String toString() {
        return "CommunityInfoResult{" +
                "data=" + data +
                '}';
    }

    public static class DataDTO implements Serializable {
        @Override
        public String toString() {
            return "DataDTO{" +
                    "currentPage=" + current_page +
                    ", pageSize=" + page_size +
                    ", offset=" + offset +
                    ", overflow=" + overflow +
                    ", totalPage=" + total_page +
                    ", totalCount=" + total_count +
                    ", postData=" + post_data +
                    '}';
        }

        public Integer getCurrent_page() {
            return current_page;
        }

        public void setCurrent_page(Integer current_page) {
            this.current_page = current_page;
        }

        public Integer getPage_size() {
            return page_size;
        }

        public void setPage_size(Integer page_size) {
            this.page_size = page_size;
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

        public Integer getTotal_page() {
            return total_page;
        }

        public void setTotal_page(Integer total_page) {
            this.total_page = total_page;
        }

        public Integer getTotal_count() {
            return total_count;
        }

        public void setTotal_count(Integer total_count) {
            this.total_count = total_count;
        }

        public List<PostDataDTO> getPost_data() {
            return post_data;
        }

        public void setPost_data(List<PostDataDTO> post_data) {
            this.post_data = post_data;
        }

        public DataDTO(Integer currentPage, Integer pageSize, Long offset, Boolean overflow, Integer totalPage, Integer totalCount, List<PostDataDTO> postData) {
            this.current_page = currentPage;
            this.page_size = pageSize;
            this.offset = offset;
            this.overflow = overflow;
            this.total_page = totalPage;
            this.total_count = totalCount;
            this.post_data = postData;
        }

        private Integer current_page;
        private Integer page_size;
        private Long offset;
        private Boolean overflow;
        private Integer total_page;
        private Integer total_count;
        private List<PostDataDTO> post_data;

        public static class PostDataDTO implements Serializable {
            @Override
            public String toString() {
                return "PostDataDTO{" +
                        "userInfo=" + user_info +
                        ", id=" + id +
                        ", userId=" + userId +
                        ", contentCount=" + content_count +
                        ", likeCount=" + like_count +
                        ", collectCount=" + collect_count +
                        ", content='" + content + '\'' +
                        ", createTime=" + create_time +
                        ", deleteTime=" + delete_time +
                        ", imageUrls=" + image_urls +
                        '}';
            }

            public PostDataDTO(UserInfoDTO userInfo, Integer id, Integer userId, Integer contentCount, Integer likeCount, Integer collectCount, String content, Integer createTime, Integer deleteTime, List<String> imageUrls) {
                this.user_info = userInfo;
                this.id = id;
                this.userId = userId;
                this.content_count = contentCount;
                this.like_count = likeCount;
                this.collect_count = collectCount;
                this.content = content;
                this.create_time = createTime;
                this.delete_time = deleteTime;
                this.image_urls = imageUrls;
            }

            private UserInfoDTO user_info;
            private Integer id;
            private Integer userId;
            private Integer content_count;
            private Integer like_count;
            private Integer collect_count;
            private String content;
            private Integer create_time;
            private Integer delete_time;
            private List<String> image_urls;

            public UserInfoDTO getUser_info() {
                return user_info;
            }

            public void setUser_info(UserInfoDTO user_info) {
                this.user_info = user_info;
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

            public Integer getContent_count() {
                return content_count;
            }

            public void setContent_count(Integer content_count) {
                this.content_count = content_count;
            }

            public Integer getLike_count() {
                return like_count;
            }

            public void setLike_count(Integer like_count) {
                this.like_count = like_count;
            }

            public Integer getCollect_count() {
                return collect_count;
            }

            public void setCollect_count(Integer collect_count) {
                this.collect_count = collect_count;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public Integer getCreate_time() {
                return create_time;
            }

            public void setCreate_time(Integer create_time) {
                this.create_time = create_time;
            }

            public Integer getDelete_time() {
                return delete_time;
            }

            public void setDelete_time(Integer delete_time) {
                this.delete_time = delete_time;
            }

            public List<String> getImage_urls() {
                return image_urls;
            }

            public void setImage_urls(List<String> image_urls) {
                this.image_urls = image_urls;
            }

            public static class UserInfoDTO implements Serializable {
                @Override
                public String toString() {
                    return "UserInfoDTO{" +
                            "userId=" + user_id +
                            ", nickName='" + nick_name + '\'' +
                            ", account='" + account + '\'' +
                            ", avatarImage='" + avatar_image + '\'' +
                            '}';
                }

                private Integer user_id;
                private String nick_name;
                private String account;
                private String avatar_image;

                public UserInfoDTO(Integer userId, String nickName, String account, String avatarImage) {
                    this.user_id = userId;
                    this.nick_name = nickName;
                    this.account = account;
                    this.avatar_image = avatarImage;
                }

                public Integer getUser_id() {
                    return user_id;
                }

                public void setUser_id(Integer user_id) {
                    this.user_id = user_id;
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

                public String getAvatar_image() {
                    return avatar_image;
                }

                public void setAvatar_image(String avatar_image) {
                    this.avatar_image = avatar_image;
                }
            }
        }
    }
}
