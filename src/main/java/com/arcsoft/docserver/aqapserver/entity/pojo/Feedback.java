package com.arcsoft.docserver.aqapserver.entity.pojo;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@TableName("dbo.AQAP_Feedback")
@ApiModel(value = "JudgeVo字段编辑对象")
public class Feedback {
    @ApiModelProperty(name = "feedback", value = "反馈意见",example = "加油")
    @TableField("feedback")
    private String feedback;

    @ApiModelProperty(name = "projectScale", value = "项目规模",example = "小型项目")
    @TableField("project_scale")
    private String projectScale;

    @ApiModelProperty(name = "userId", value = "用户id",example = "8023")
    @TableField("user_id")
    private String userId;

    @ApiModelProperty(name = "updateTime", value = "评定日期",example = "2020-03-19")
    @TableField("update_time")
    private Date updateTime;

}
