package com.arcsoft.docserver.aqapserver.service;

import com.arcsoft.docserver.aqapserver.entity.pojo.Dictionary;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author xqh3622
 */
public interface DictionarySelectService extends IService<Dictionary> {
    Dictionary getLevelByDictionaryId(Integer dictionaryId);
}
