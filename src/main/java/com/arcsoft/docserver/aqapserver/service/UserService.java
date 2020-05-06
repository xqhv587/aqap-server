package com.arcsoft.docserver.aqapserver.service;

import com.arcsoft.docserver.aqapserver.entity.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author xqh3622
 */
public interface UserService extends IService<User> {
    User getUserByUserId(String id);


    List<User> getUserList();

}
