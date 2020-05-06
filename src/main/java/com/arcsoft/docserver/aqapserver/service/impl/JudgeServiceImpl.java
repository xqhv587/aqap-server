package com.arcsoft.docserver.aqapserver.service.impl;

import com.arcsoft.docserver.aqapserver.dao.JudgeDao;
import com.arcsoft.docserver.aqapserver.entity.pojo.Judge;
import com.arcsoft.docserver.aqapserver.service.JudgeService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xqh3622
 */
@Service
public class JudgeServiceImpl extends ServiceImpl<JudgeDao, Judge> implements JudgeService {

    @Override
    public List<Judge> getJudgeList() {
        QueryWrapper<Judge> queryWrapper = new QueryWrapper<>();

        return list(queryWrapper);
    }


}
