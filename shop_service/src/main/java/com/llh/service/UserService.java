package com.llh.service;

import com.llh.pojo.Users;
import com.llh.pojo.bo.UserBo;

public interface UserService {

    /**
     * 判断用户名是否存在
     * @param username
     */
    public boolean queryUsernameIsExist(String username);


    /**
     * 用户注册
     * @param userBo
     * @return
     */
    public Users registerUser(UserBo userBo);


    /**
     * 用户登陆
     * @param userBo
     * @return
     */
    public Users loginUser(UserBo userBo);



}
