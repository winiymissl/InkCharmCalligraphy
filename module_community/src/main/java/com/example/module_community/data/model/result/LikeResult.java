package com.example.module_community.data.model.result;

import com.example.common.base.BaseResult;

import java.io.Serializable;

/**
 * @Author winiymissl
 * @Date 2024-04-17 21:37
 * @Version 1.0
 */
public class LikeResult extends BaseResult implements Serializable {

    private DataDTO data;

    public DataDTO getData() {
        return data;
    }

    public static class DataDTO implements Serializable {
        private Integer like_id;

        public Integer getLike_id() {
            return like_id;
        }
    }
}
