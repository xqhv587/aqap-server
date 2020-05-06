package com.arcsoft.docserver.aqapserver.entity.vo;


import lombok.Builder;
import lombok.Data;

/**
 * @author xqh3622
 */
@Data
@Builder
public class DictionaryVo {
    private Integer id;

    private String itemName;

    private Integer itemType;

    private Integer preLevel;

    private Integer endLevel;
}
