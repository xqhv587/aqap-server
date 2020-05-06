package com.arcsoft.docserver.aqapserver.service.impl;

import com.arcsoft.docserver.aqapserver.dao.UserDao;
import com.arcsoft.docserver.aqapserver.entity.model.DbConstant;
import com.arcsoft.docserver.aqapserver.entity.pojo.User;
import com.arcsoft.docserver.aqapserver.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xqh3622
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {

    @Override
    public User getUserByUserId(String id) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(DbConstant.USER_ID,id);
        return getOne(queryWrapper);
    }

    @Override
    public List<User> getUserList() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        return list(queryWrapper);
    }

}

