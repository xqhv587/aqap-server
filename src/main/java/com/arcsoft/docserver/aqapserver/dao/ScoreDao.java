package com.arcsoft.docserver.aqapserver.dao;

import com.arcsoft.docserver.aqapserver.entity.pojo.Score;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ScoreDao extends BaseMapper<Score> {

}
