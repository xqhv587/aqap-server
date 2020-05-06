package com.arcsoft.docserver.aqapserver.entity.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("dbo.AQAP_Judge")
public class Judge {

@TableField("ID")
private Integer id;

@TableField("user_id")
private String userId;

@TableField("level_id")
private Integer levelId;

@TableField("update_time")
private Date updateTime;

@TableField(exist = false)
private String team;

@TableField(exist = false)
private String comPoseName;

}
