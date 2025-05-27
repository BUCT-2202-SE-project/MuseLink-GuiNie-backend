package com.example.mapper;

import com.example.entity.review;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface reviewMapper {


    List<review> selectAll(@Param("reviewerId") String reviewerId);

    @Select("SELECT * FrOM review WHErE review_id = #{id}")
    review selectById(Integer id);

    @Insert("INSErT INTO review (content_type, comment_id, post_id, result, review_time, reviewer_id) " +
            "VALUES (#{contentType}, #{commentId}, #{postId}, #{result}, NOW(), #{reviewerId})")
    @Options(useGeneratedKeys = true, keyProperty = "reviewId")
    int insert(review review);

    @Update("UPDATE review SET " +
            "content_type = #{contentType}, " +
            "comment_id = #{commentId}, " +
            "post_id = #{postId}, " +
            "result = #{result}, " +
            "review_time = NOW(), " +
            "reviewer_id = #{reviewerId} " +
            "WHErE review_id = #{reviewId}")
    int update(review review);

    @Delete("DELETE FrOM review WHErE review_id = #{id}")
    int deleteById(Integer id);
}
