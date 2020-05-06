package com.arcsoft.docserver.aqapserver.service;

import com.arcsoft.docserver.aqapserver.entity.pojo.Feedback;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author xqh3622
 */
public interface FeedbackService extends IService<Feedback> {
    Feedback getFeedbackByUserId(String userId);
}
