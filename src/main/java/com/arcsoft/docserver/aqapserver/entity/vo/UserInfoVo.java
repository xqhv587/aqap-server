package com.arcsoft.docserver.aqapserver.entity.vo;

import com.arcsoft.docserver.aqapserver.entity.pojo.Feedback;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.util.Map;

/**
 * @author xqh3622
 */
@Data
@Builder
@ApiModel(value = "UserInfoVo字段编辑对象")
public class UserInfoVo {
  @ApiModelProperty(name = "map", value = "用户能力值")
  private Map<String, Integer> map;

  @ApiModelProperty(name = "feedback", value = "反馈意见对象")
  private Feedback feedback;

  @ApiModelProperty(name = "meanValue", value = "能力平均值")
  private double meanValue;
}
