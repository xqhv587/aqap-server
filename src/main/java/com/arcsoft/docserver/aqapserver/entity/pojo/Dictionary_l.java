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
@TableName("dbo.AQAP_Dictionary")
public class Dictionary_l {
    @TableField("ID")
    private Integer ID;

    @TableField("ItemName")
    private String itemName;

    @TableField("ItemType")
    private Integer itemType;

    @TableField("ItemId")
    private Integer itemId;


}
