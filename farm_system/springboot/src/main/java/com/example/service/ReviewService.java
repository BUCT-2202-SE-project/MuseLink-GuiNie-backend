package com.example.service;
import com.example.common.Result;
import com.example.entity.review;
import com.example.mapper.reviewMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private reviewMapper reviewMapper;


    public PageInfo<review> list(int pageNum, int pageSize, String reviewerId) {
        PageHelper.startPage(pageNum, pageSize);
        List<review> list = reviewMapper.selectAll(reviewerId);
        return PageInfo.of(list);
    }


    public review getById(Integer id) {
        return reviewMapper.selectById(id);
    }


    public boolean add(review review) {
        return reviewMapper.insert(review) > 0;
    }


    public boolean update(review review) {
        return reviewMapper.update(review) > 0;
    }


    public Result delete(Integer id) {
         reviewMapper.deleteById(id) ;
         return Result.success();
    }
}
