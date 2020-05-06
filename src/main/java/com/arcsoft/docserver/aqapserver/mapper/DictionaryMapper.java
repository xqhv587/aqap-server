package com.arcsoft.docserver.aqapserver.mapper;

import com.arcsoft.docserver.aqapserver.entity.pojo.Dictionary;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author xqh3622
 */
@Mapper
@Repository
public interface DictionaryMapper extends BaseMapper<Dictionary> {
    Dictionary getLevelByDictionaryId(Integer dictionaryId);
}
