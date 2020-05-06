package com.arcsoft.docserver.aqapserver.service;

import com.arcsoft.docserver.aqapserver.entity.pojo.Judge;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author xqh3622
 */
public interface JudgeService extends IService<Judge> {
    /**
     * @return List<Judge>
     */
    List<Judge> getJudgeList();



}
