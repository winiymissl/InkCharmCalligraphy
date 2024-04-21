package com.example.module_character.data.result;

import com.example.common.base.BaseResult;

import java.io.Serializable;

/**
 * @Author winiymissl
 * @Date 2024-04-21 14:48
 * @Version 1.0
 */
public class SentenceResult extends BaseResult implements Serializable {

    private Integer id;
    private String uuid;
    private String hitokoto;
    private String type;
    private String from;
    private String creator;
    private Integer creator_uid;
    private Integer reviewer;
    private String commit_from;
    private String created_at;
    private Integer length;

    @Override
    public String toString() {
        return "SentenceResult{" +
                "id=" + id +
                ", uuid='" + uuid + '\'' +
                ", hitokoto='" + hitokoto + '\'' +
                ", type='" + type + '\'' +
                ", from='" + from + '\'' +
                ", creator='" + creator + '\'' +
                ", creator_uid=" + creator_uid +
                ", reviewer=" + reviewer +
                ", commit_from='" + commit_from + '\'' +
                ", created_at='" + created_at + '\'' +
                ", length=" + length +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getHitokoto() {
        return hitokoto;
    }

    public void setHitokoto(String hitokoto) {
        this.hitokoto = hitokoto;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Integer getCreator_uid() {
        return creator_uid;
    }

    public void setCreator_uid(Integer creator_uid) {
        this.creator_uid = creator_uid;
    }

    public Integer getReviewer() {
        return reviewer;
    }

    public void setReviewer(Integer reviewer) {
        this.reviewer = reviewer;
    }

    public String getCommit_from() {
        return commit_from;
    }

    public void setCommit_from(String commit_from) {
        this.commit_from = commit_from;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }
}
