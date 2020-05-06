package com.arcsoft.docserver.aqapserver.dao;

import com.arcsoft.docserver.aqapserver.entity.pojo.Team;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author xqh3622
 */
@Mapper
@Repository
public interface TeamDao extends BaseMapper<Team> {
}
