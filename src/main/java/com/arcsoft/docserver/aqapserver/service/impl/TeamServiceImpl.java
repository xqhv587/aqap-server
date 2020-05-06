package com.arcsoft.docserver.aqapserver.service.impl;

import com.arcsoft.docserver.aqapserver.dao.TeamDao;
import com.arcsoft.docserver.aqapserver.entity.model.DbConstant;
import com.arcsoft.docserver.aqapserver.entity.pojo.Team;
import com.arcsoft.docserver.aqapserver.service.TeamService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xqh3622
 */
@Service
public class TeamServiceImpl extends ServiceImpl<TeamDao, Team> implements TeamService {

    @Override
    public Team getTeamByDeptId(String deptId) {
        QueryWrapper<Team> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(DbConstant.GROUP_ID,deptId);
        return getOne(queryWrapper);
    }

    @Override
    public List<Team> getTeams() {
        QueryWrapper<Team> queryWrapper = new QueryWrapper<>();
        return list(queryWrapper);
    }
}
