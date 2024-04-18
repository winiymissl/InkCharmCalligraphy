package com.example.feature_mine.data.model.result;

import java.io.Serializable;
import java.util.List;

/**
 * @Author winiymissl
 * @Date 2024-04-18 21:21
 * @Version 1.0
 */
public class FansResult implements Serializable {

    private Integer code;
    private String message;
    private DataDTO data;

    public static class DataDTO implements Serializable {
        private Integer currentPage;
        private Integer pageSize;
        private Integer offset;
        private Boolean overflow;
        private Integer totalPage;
        private Integer totalCount;
        private List<PostDataDTO> postData;

        public static class PostDataDTO implements Serializable {
            private Integer userId;
            private String avatarBackground;
            private String nickName;
            private Integer followCount;
            private Integer fansCount;
            private String email;

            public Integer getUserId() {
                return userId;
            }

            public String getAvatarBackground() {
                return avatarBackground;
            }

            public String getNickName() {
                return nickName;
            }

            public Integer getFollowCount() {
                return followCount;
            }

            public Integer getFansCount() {
                return fansCount;
            }

            public String getEmail() {
                return email;
            }
        }

        public Integer getCurrentPage() {
            return currentPage;
        }

        public Integer getPageSize() {
            return pageSize;
        }

        public Integer getOffset() {
            return offset;
        }

        public Boolean getOverflow() {
            return overflow;
        }

        public Integer getTotalPage() {
            return totalPage;
        }

        public Integer getTotalCount() {
            return totalCount;
        }

        public List<PostDataDTO> getPostData() {
            return postData;
        }
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public DataDTO getData() {
        return data;
    }
}