package com.midas.cafe.service;

import com.midas.cafe.model.User;
import com.midas.cafe.repository.user.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * User: kimkm
 * Date: 2018-05-26
 * Time: 오후 4:01
 */
@Service
public class UserService {

    @Autowired
    UserDao userDao;

    public int joinUser(User user){
        return userDao.insertUser(user);
    }


}
