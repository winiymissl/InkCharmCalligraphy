package com.example.module_community.data.model.result;

import com.example.common.base.BaseResult;

import java.io.Serializable;
import java.util.List;

/**
 * @Author winiymissl
 * @Date 2024-04-17 13:04
 * @Version 1.0
 */
public class PostDetailResult extends BaseResult {
    @Override
    public String toString() {
        return "PostDetailResult{" +
                "data=" + data +
                '}';
    }

    private DataDTO data;

    public DataDTO getData() {
        return data;
    }

    public static class DataDTO implements Serializable {
        private PostDataDTO post_data;
        private StatusDataDTO status_data;

        @Override
        public String toString() {
            return "DataDTO{" +
                    "postData=" + post_data +
                    ", statusData=" + status_data +
                    '}';
        }

        public PostDataDTO getPost_data() {
            return post_data;
        }

        public StatusDataDTO getStatus_data() {
            return status_data;
        }

        public static class PostDataDTO implements Serializable {
            @Override
            public String toString() {
                return "PostDataDTO{" +
                        "userInfo=" + user_info +
                        ", id=" + id +
                        ", userId=" + user_id +
                        ", contentCount=" + content_count +
                        ", likeCount=" + like_count +
                        ", collectCount=" + collect_count +
                        ", content='" + content + '\'' +
                        ", createTime=" + create_time +
                        ", deleteTime=" + delete_time +
                        ", imageUrls=" + image_urls +
                        '}';
            }

            private UserInfoDTO user_info;
            private Integer id;
            private Integer user_id;
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

            public Integer getId() {
                return id;
            }

            public Integer getUser_id() {
                return user_id;
            }

            public Integer getContent_count() {
                return content_count;
            }

            public Integer getLike_count() {
                return like_count;
            }

            public Integer getCollect_count() {
                return collect_count;
            }

            public String getContent() {
                return content;
            }

            public Integer getCreate_time() {
                return create_time;
            }

            public Integer getDelete_time() {
                return delete_time;
            }

            public List<String> getImage_urls() {
                return image_urls;
            }

            public static class UserInfoDTO implements Serializable {
                private Integer user_id;
                private String nick_name;
                private String account;
                private String avatar_image;

                public Integer getUser_id() {
                    return user_id;
                }

                public String getNick_name() {
                    return nick_name;
                }

                public String getAccount() {
                    return account;
                }

                public String getAvatar_image() {
                    return avatar_image;
                }

                @Override
                public String toString() {
                    return "UserInfoDTO{" +
                            "user_id=" + user_id +
                            ", nick_name='" + nick_name + '\'' +
                            ", account='" + account + '\'' +
                            ", avatar_image='" + avatar_image + '\'' +
                            '}';
                }
            }

        }

        public static class StatusDataDTO implements Serializable {
            private Boolean whether_like;
            private Boolean whether_collect;
            private Boolean whether_follow;

            public Boolean getWhether_follow() {
                return whether_follow;
            }

            public Boolean getWhether_like() {
                return whether_like;
            }

            public Boolean getWhether_collect() {
                return whether_collect;
            }
        }
    }
}
