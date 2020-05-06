package com.arcsoft.docserver.aqapserver.mapper;

import com.arcsoft.docserver.aqapserver.entity.pojo.Judge;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xqh3622
 */
@Mapper
@Repository
public interface JudgeMapper extends BaseMapper<Judge> {
    /**
     * @return List<Judge>
     */
    List<Judge> getJudgeList1(String userName,String team, Integer level,Integer permission,String currentloginusername,String sorts,String order);

    int getPermission(String functionId,String currentloginusername);
}
