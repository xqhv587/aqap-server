package com.arcsoft.docserver.aqapserver.service.impl;

import com.arcsoft.docserver.aqapserver.entity.pojo.Judge;
import com.arcsoft.docserver.aqapserver.mapper.JudgeMapper;
import com.arcsoft.docserver.aqapserver.service.JudgeSelectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xqh3622
 */
@Service
public class JudgeSelectServiceImpl extends ServiceImpl<JudgeMapper, Judge> implements JudgeSelectService {
@Autowired
private JudgeMapper judgeMapper;
    @Override
    public List<Judge> getJudgeList1(String userName, String team, Integer level,Integer permission,String currentloginusername,String sorts,String order) {
        return judgeMapper.getJudgeList1( userName, team,  level, permission,currentloginusername,sorts,order);
    }

    @Override
    public int getPermission(String functionId,String currentloginusername) {
        return judgeMapper.getPermission(functionId,currentloginusername);
    }
}
