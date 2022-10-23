package com.llh.service.impl;

import com.llh.enums.Sex;
import com.llh.mapper.UsersMapper;

import com.llh.pojo.Users;
import com.llh.pojo.bo.UserBo;
import com.llh.service.UserService;
import com.llh.util.DateUtil;
import com.llh.util.MD5Utils;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    public UsersMapper usersMapper;

    @Autowired
    public Sid sid;

    private static final String USER_FACE = "http://122.152.205.72:88/group1/M00/00/05/CpoxxFw_8_qAIlFXAAAcIhVPdSg994.png";



    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public boolean queryUsernameIsExist(String username) {

        Example userExample = new Example(Users.class);
        Example.Criteria userCriteria =  userExample.createCriteria();
        userCriteria.andEqualTo("username", username);

        Users result =  usersMapper.selectOneByExample(userExample);

        return result == null ? false : true;

    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Users registerUser(UserBo userBO) {

        String userId = sid.nextShort();

        String userName = userBO.getUserName();
        String password = userBO.getPassword();

        Users user = new Users();
        user.setUsername(userName);
        user.setId(userId);
        try {
            user.setPassword(MD5Utils.getMD5Str(password));
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 默认用户昵称同用户名
        user.setNickname(userBO.getUserName());
        // 默认头像
        user.setFace(USER_FACE);
        // 默认生日
        user.setBirthday(DateUtil.stringToDate("1900-01-01"));
        // 默认性别为 保密
        user.setSex(Sex.secret.type);

        user.setCreatedTime(new Date());
        user.setUpdatedTime(new Date());


        usersMapper.insert(user);
        return user;

    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    @Override
    public Users loginUser(UserBo userBo) {
        String password = "";

        try {
         password = MD5Utils.getMD5Str(userBo.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Example userExample = new Example(Users.class);
        Example.Criteria userCriteria =  userExample.createCriteria();
        userCriteria.andEqualTo("username", userBo.getUserName());
        userCriteria.andEqualTo("password", password);

        Users result =  usersMapper.selectOneByExample(userExample);
        return result;
    }


}
