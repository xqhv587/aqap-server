package com.arcsoft.docserver.aqapserver.entity.vo;

import com.arcsoft.docserver.aqapserver.entity.pojo.Dictionary_l;
import com.arcsoft.docserver.aqapserver.entity.pojo.Team;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Set;

/**
 * @author xqh3622
 */
@Data
@Builder
@ApiModel(value = "JudgeListVo字段字段显示对象")
public class JudgeListVo {
    @ApiModelProperty(name = "total", value = "总条数")
    private Integer total;

    @ApiModelProperty(name = "groupList", value = "组名集合")
    private  Set<Team> groupMap;

    @ApiModelProperty(name = "userNameList", value = "用户名集合")
    private Set<String> userNameList;

    @ApiModelProperty(name = "levelMap", value = "星级id和星级name")
    private List<Dictionary_l> levelMap;

    @ApiModelProperty(name = "judgeVo", value = "judgeVo返回对象链表")
    private List<JudgeVo> judgeVos;

}
