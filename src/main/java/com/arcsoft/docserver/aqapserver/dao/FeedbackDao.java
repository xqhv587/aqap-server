package com.arcsoft.docserver.aqapserver.dao;


import com.arcsoft.docserver.aqapserver.entity.pojo.Feedback;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author xqh3622
 */
@Mapper
@Repository
public interface FeedbackDao extends BaseMapper<Feedback> {


}
