package com.arcsoft.docserver.aqapserver.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author xqh3622
 */
@Data
@ApiModel(value = "judge查询对象")
public class JudgeQo {

    @ApiModelProperty(name = "team", value = "团队",example = "HZ_SA_IC")
    private String team;

    @ApiModelProperty(name = "userId", value = "用户编号",example = "1")
    private Integer userId;

    @ApiModelProperty(name = "level", value = "星级",example = "2")
    private Integer level;
}
