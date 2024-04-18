package com.example.feature_mine.data.model.result;

import java.io.Serializable;
import java.util.List;

/**
 * @Author winiymissl
 * @Date 2024-04-18 21:21
 * @Version 1.0
 */
public class FollowResult implements Serializable {

    private Integer code;
    private String message;
    private DataDTO data;

    public static class DataDTO implements Serializable {
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

            public String getAvatar_background() {
                return avatar_background;
            }

            public String getNick_name() {
                return nick_name;
            }

            public Integer getFollow_count() {
                return follow_count;
            }

            public Integer getFans_count() {
                return fans_count;
            }

            public String getEmail() {
                return email;
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

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataDTO getData() {
        return data;
    }

    public void setData(DataDTO data) {
        this.data = data;
    }
}

