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
@TableName("dbo.access_user")
public class User {
    @TableField("real_name")
    private String realName;

    @TableField("dept_id")
    private String deptId;

    @TableField("user_name")
    private String userName;



}
