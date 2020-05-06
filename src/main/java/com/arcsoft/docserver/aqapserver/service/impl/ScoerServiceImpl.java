package com.arcsoft.docserver.aqapserver.service.impl;

import com.arcsoft.docserver.aqapserver.dao.ScoreDao;
import com.arcsoft.docserver.aqapserver.entity.model.DbConstant;
import com.arcsoft.docserver.aqapserver.entity.pojo.Score;
import com.arcsoft.docserver.aqapserver.service.ScoreService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xqh3622
 */
@Service
public class ScoerServiceImpl extends ServiceImpl<ScoreDao, Score> implements ScoreService {


    @Override
    public List<Score> getScoreByUserId(String id) {
        QueryWrapper<Score> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(DbConstant.USER_ID,id);
        queryWrapper.eq(DbConstant.SCOPE_STATUS,1);
        return list(queryWrapper);
    }
}
