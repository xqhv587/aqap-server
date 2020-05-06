package com.arcsoft.docserver.aqapserver.entity.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * @author xqh3622
 */
@Data
@AllArgsConstructor
@Builder
@TableName("dbo.access_group")
public class Team {
    @TableField("group_name")
    private String groupName;

    @TableField("group_id")
    private String groupId;
}
