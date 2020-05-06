package com.arcsoft.docserver.aqapserver.service.impl;

import com.arcsoft.docserver.aqapserver.dao.FeedbackDao;
import com.arcsoft.docserver.aqapserver.entity.model.DbConstant;
import com.arcsoft.docserver.aqapserver.entity.pojo.Feedback;
import com.arcsoft.docserver.aqapserver.service.FeedbackService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author xqh3622
 */
@Service
public class FeedbackServiceImpl extends ServiceImpl<FeedbackDao, Feedback> implements FeedbackService {


    @Override
    public Feedback getFeedbackByUserId(String userId) {
        QueryWrapper<Feedback> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(DbConstant.USER_ID,userId);

        return getOne(queryWrapper);
    }
}
