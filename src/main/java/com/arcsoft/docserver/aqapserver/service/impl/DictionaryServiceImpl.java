package com.arcsoft.docserver.aqapserver.service.impl;

import com.arcsoft.docserver.aqapserver.dao.DictionaryDao;
import com.arcsoft.docserver.aqapserver.entity.model.DbConstant;
import com.arcsoft.docserver.aqapserver.entity.pojo.Dictionary_l;
import com.arcsoft.docserver.aqapserver.service.DictionaryService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DictionaryServiceImpl extends ServiceImpl<DictionaryDao, Dictionary_l> implements DictionaryService {

    @Override
    public Dictionary_l getLevelByDictionaryId(Integer dictionaryId) {

        QueryWrapper<Dictionary_l> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(DbConstant.ITEM_ID,dictionaryId);
        queryWrapper.eq(DbConstant.ITEM_TYPE,1);
        return getOne(queryWrapper);
    }

    @Override
    public Dictionary_l getScopeByDictionaryId(Integer dictionaryId) {

        QueryWrapper<Dictionary_l> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(DbConstant.ITEM_ID,dictionaryId);
        queryWrapper.eq(DbConstant.ITEM_TYPE,2);
        return getOne(queryWrapper);
    }

    @Override
    public List<Dictionary_l> getLevelList() {
        QueryWrapper<Dictionary_l> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(DbConstant.ITEM_TYPE,1);
        List<Dictionary_l> list = list(queryWrapper);
        for (Dictionary_l dic:list) {
            dic.setID(dic.getItemId());
        }
        return list;
    }
}
