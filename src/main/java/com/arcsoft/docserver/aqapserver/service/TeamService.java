package com.arcsoft.docserver.aqapserver.service;


import com.arcsoft.docserver.aqapserver.entity.pojo.Team;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author xqh3622
 */
public interface TeamService extends IService<Team> {
    Team getTeamByDeptId(String deptId);

    List<Team> getTeams();
}
