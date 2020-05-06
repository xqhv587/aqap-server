package com.arcsoft.docserver.aqapserver.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * @author xqh3622
 */
@Data
@Builder
@ApiModel(value = "JudgeVo字段编辑对象")
public class JudgeVo {
    @ApiModelProperty(name = "team", value = "团队",example = "HZ_SA_IC")
    private String team;

    @ApiModelProperty(name = "userName", value = "用户姓名",example = "杨晓")
    private String userName;

    @ApiModelProperty(name = "level", value = "星级",example = "二星")
    private String level;

    @ApiModelProperty(name = "updateTime", value = "最近评定时间")
    private Date updateTime;

    @ApiModelProperty(name = "userId", value = "用户id",example = "1")
    private String userId;

    @ApiModelProperty(name = "levelNum", value = "星级数量",example = "2.5")
    private double levelNum;

    @ApiModelProperty(name = "levelRemark", value = "星级说明",example = "")
    private String levelRemark;
}

