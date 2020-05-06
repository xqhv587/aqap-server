package com.arcsoft.docserver.aqapserver.service;

import com.arcsoft.docserver.aqapserver.entity.pojo.Judge;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author xqh3622
 */
public interface JudgeSelectService extends IService<Judge> {
    List<Judge> getJudgeList1(String userName, String team, Integer level,Integer permission,String currentloginusername,String sorts,String order);

    int getPermission(String functionId,String currentloginusername);
}
