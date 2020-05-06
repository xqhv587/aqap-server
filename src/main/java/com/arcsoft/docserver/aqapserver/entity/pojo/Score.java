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
@TableName("dbo.AQAP_Score")
public class Score {
    @TableField("user_id")
    private String userId;

    @TableField("dictionary_id")
    private Integer dictionaryId;

    @TableField("scope")
    private Integer scope;

    @TableField("status")
    private Integer status;

}
