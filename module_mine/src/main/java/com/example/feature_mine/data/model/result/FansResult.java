package com.example.feature_mine.data.model.result;

import com.example.common.base.BaseResult;

import java.io.Serializable;
import java.util.List;

/**
 * @Author winiymissl
 * @Date 2024-04-18 21:21
 * @Version 1.0
 */
public class FansResult extends BaseResult implements Serializable {
    private DataDTO data;

    @Override
    public String toString() {
        return "FansResult{" + "code=" + code + ", message='" + message + '\'' + ", data=" + data + '}';
    }

    public static class DataDTO implements Serializable {
        @Override
        public String toString() {
            return "DataDTO{" + "current_page=" + current_page + ", page_size=" + page_size + ", offset=" + offset + ", overflow=" + overflow + ", total_page=" + total_page + ", total_count=" + total_count + ", post_data=" + post_data + '}';
        }

        private Integer current_page;
        private Integer page_size;
        private Integer offset;
        private Boolean overflow;
        private Integer total_page;
        private Integer total_count;
        private List<PostDataDTO> post_data;

        public static class PostDataDTO implements Serializable {
            private Integer user_id;
            private String avatar_background;
            private String nick_name;
            private Integer follow_count;
            private Integer fans_count;
            private String email;

            public Integer getUser_id() {
                return user_id;
            }

            public void setUser_id(Integer user_id) {
                this.user_id = user_id;
            }

            public String getAvatar_background() {
                return avatar_background;
            }

            public void setAvatar_background(String avatar_background) {
                this.avatar_background = avatar_background;
            }

            public String getNick_name() {
                return nick_name;
            }

            public void setNick_name(String nick_name) {
                this.nick_name = nick_name;
            }

            public Integer getFollow_count() {
                return follow_count;
            }

            public void setFollow_count(Integer follow_count) {
                this.follow_count = follow_count;
            }

            public Integer getFans_count() {
                return fans_count;
            }

            public void setFans_count(Integer fans_count) {
                this.fans_count = fans_count;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            @Override
            public String toString() {
                return "PostDataDTO{" + "user_id=" + user_id + ", avatar_background='" + avatar_background + '\'' + ", nick_name='" + nick_name + '\'' + ", follow_count=" + follow_count + ", fans_count=" + fans_count + ", email='" + email + '\'' + '}';
            }
        }

        public Integer getCurrent_page() {
            return current_page;
        }

        public Integer getPage_size() {
            return page_size;
        }

        public Integer getOffset() {
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

        public List<PostDataDTO> getPost_data() {
            return post_data;
        }
    }

    public DataDTO getData() {
        return data;
    }
}