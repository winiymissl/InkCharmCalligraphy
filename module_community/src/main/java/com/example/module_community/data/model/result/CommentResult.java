package com.example.module_community.data.model.result;

import com.example.common.base.BaseResult;

import java.io.Serializable;
import java.util.List;

/**
 * @Author winiymissl
 * @Date 2024-04-17 17:42
 * @Version 1.0
 */
public class CommentResult extends BaseResult implements Serializable {

    private DataDTO data;

    public DataDTO getData() {
        return data;
    }

    @Override
    public String toString() {
        return "CommentResult{" + "data=" + data + '}';
    }

    public static class DataDTO implements Serializable {
        public Integer getCurrent_page() {
            return current_page;
        }

        @Override
        public String toString() {
            return "DataDTO{" + "current_page=" + current_page + ", page_size=" + page_size + ", offset=" + offset + ", overflow=" + overflow + ", total_page=" + total_page + ", total_count=" + total_count + ", comment_data=" + comment_data + '}';
        }

        public Integer getPage_size() {
            return page_size;
        }

        public Long getOffset() {
            return offset;
        }

        public Boolean getOverflow() {
            return overflow;
        }

        public Integer getTotal_page() {
            return total_page;
        }

        public Integer getTotal_count() {
            return total_count;
        }

        public List<CommentDataDTO> getComment_data() {
            return comment_data;
        }

        private Integer current_page;
        private Integer page_size;
        private Long offset;
        private Boolean overflow;
        private Integer total_page;
        private Integer total_count;
        private List<CommentDataDTO> comment_data;

        public static class CommentDataDTO implements Serializable {
            @Override
            public String toString() {
                return "CommentDataDTO{" + "id=" + id + ", create_time=" + create_time + ", post_id=" + post_id + ", comment='" + comment + '\'' + ", user_info=" + user_info + '}';
            }

            public Integer getId() {
                return id;
            }

            public Integer getCreate_time() {
                return create_time;
            }

            public Integer getPost_id() {
                return post_id;
            }

            public String getComment() {
                return comment;
            }

            public UserInfoDTO getUser_info() {
                return user_info;
            }

            private Integer id;
            private Integer create_time;
            private Integer post_id;
            private String comment;
            private UserInfoDTO user_info;

            public static class UserInfoDTO implements Serializable {
                @Override
                public String toString() {
                    return "UserInfoDTO{" + "user_id=" + user_id + ", nick_name='" + nick_name + '\'' + ", account='" + account + '\'' + ", avatar_image='" + avatar_image + '\'' + '}';
                }

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

                private Integer user_id;
                private String nick_name;
                private String account;
                private String avatar_image;
            }
        }
    }
}
