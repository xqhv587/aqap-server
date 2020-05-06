package com.arcsoft.docserver.aqapserver.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * @author xqh3622
 */
@Data
@AllArgsConstructor
@Builder
public class JudgelistTwoVo {

    private Integer id;

    private String userId;

    private Integer levelId;

    private Date updateTime;

    private String team;
}
