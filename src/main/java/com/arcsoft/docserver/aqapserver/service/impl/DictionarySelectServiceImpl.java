package com.arcsoft.docserver.aqapserver.service.impl;

import com.arcsoft.docserver.aqapserver.entity.pojo.Dictionary;
import com.arcsoft.docserver.aqapserver.mapper.DictionaryMapper;
import com.arcsoft.docserver.aqapserver.service.DictionarySelectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xqh3622
 */
@Service
public class DictionarySelectServiceImpl extends ServiceImpl<DictionaryMapper, Dictionary> implements DictionarySelectService {
    @Autowired
    private DictionaryMapper dictionaryMapper;

    @Override
    public Dictionary getLevelByDictionaryId(Integer dictionaryId) {
        return dictionaryMapper.getLevelByDictionaryId(dictionaryId);
    }
}
