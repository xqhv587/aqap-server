package com.arcsoft.docserver.aqapserver.service;


import com.arcsoft.docserver.aqapserver.entity.pojo.Score;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author xqh3622
 */
public interface ScoreService extends IService<Score> {
    List<Score> getScoreByUserId(String id);

}
