package com.arcsoft.docserver.aqapserver.service;

import com.arcsoft.docserver.aqapserver.entity.pojo.Dictionary_l;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author xqh3622
 */
public interface DictionaryService extends IService<Dictionary_l> {
    Dictionary_l getLevelByDictionaryId(Integer dictionaryId);

    Dictionary_l getScopeByDictionaryId(Integer dictionaryId);

    List<Dictionary_l> getLevelList();
}
