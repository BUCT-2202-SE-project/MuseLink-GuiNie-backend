package com.example.entity;

import java.time.LocalDateTime;

/**
 * 审核记录实体类，对应 review 表
 */
public class review {
    private Integer reviewId;       // 审核ID（主键）
    private String contentType;     // 审核内容类型（评论/动态/媒体）
    private Integer commentId;      // 关联评论ID（仅当类型为评论时有效）
    private Integer postId;         // 关联动态ID（仅当类型为动态时有效）
    private String result;          // 审核结果（通过/未通过）
    private LocalDateTime reviewTime;  // 审核时间
    private Integer reviewerId;     // 审核员ID

    // 省略 getters 和 setters，可用 IDE 自动生成或使用 Lombok 注解
    public Integer getReviewId() {
        return reviewId;
    }
    public void setReviewId(Integer reviewId) {
        this.reviewId = reviewId;
    }
    public String getContentType() {
        return contentType;
    }
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
    public Integer getCommentId() {
        return commentId;
    }
    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }
    public Integer getPostId() {
        return postId;
    }
    public void setPostId(Integer postId) {
        this.postId = postId;
    }
    public String getResult() {
        return result;
    }
    public void setResult(String result) {
        this.result = result;
    }
    public LocalDateTime getReviewTime() {
        return reviewTime;
    }
    public void setReviewTime(LocalDateTime reviewTime) {
        this.reviewTime = reviewTime;
    }
    public Integer getReviewerId() {
        return reviewerId;
    }
    public void setReviewerId(Integer reviewerId) {
        this.reviewerId = reviewerId;
    }
}
