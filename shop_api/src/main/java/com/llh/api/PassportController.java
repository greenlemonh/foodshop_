package com.llh.api;



import com.llh.pojo.Users;
import com.llh.pojo.bo.UserBo;
import com.llh.service.UserService;
import com.llh.util.CookieUtils;
import com.llh.util.JsonResult;
import com.llh.util.JsonUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Api(value = "注册登陆", tags = {"用于注册登陆的相关接口"})
@RestController
@RequestMapping("passport")
public class PassportController {

    @Autowired
    public UserService userService;



    @ApiOperation(value = "用户名是否存在", notes = "用户名是否存在", httpMethod = "GET")
    @GetMapping("/usernameIsExist")
    // RequestParam 请求参数
    public JsonResult usernameIsExist(@RequestParam  String username) {
        //1.判断用户名不能为空
        if (StringUtils.isBlank(username)) {
            return JsonResult.errMsg("用户名不能为空");

        }

        //2.查找注册的用户名是否存在

        boolean exists = userService.queryUsernameIsExist(username);
        if(exists) {
            return JsonResult.errMsg("用户名已存在");
        }

        //请求成功，用户名没有重复
        return  JsonResult.ok();

    }

    @ApiOperation(value = "用户注册", notes = "用户注册", httpMethod = "POST")
    @PostMapping("/regist")
    public JsonResult  regist(@RequestBody UserBo userBo, HttpServletRequest request, HttpServletResponse response) {


        String userName = userBo.getUserName();
        String password = userBo.getPassword();
        String congfirmPassword = userBo.getConfirmPassword();

        //1.校验用户名和密码不为空
        if (StringUtils.isBlank(userName)
                || StringUtils.isBlank(password)
                || StringUtils.isBlank(congfirmPassword)){
            return JsonResult.errMsg("用户名或者密码不能为空");
        }

        // 2.查询用户是否已存在
        if (userService.queryUsernameIsExist(userName)) {
            return JsonResult.errMsg("用户名已存在");
        }
        //校验密码长度是否小于6位
        if (password.length() < 6) {
            return JsonResult.errMsg("用户密码不可小于6位");
        }

        //判断两次密码是否一致

        if (!password.equals(congfirmPassword)) {
            return JsonResult.errMsg("两次输入密码不一致");
        }
        //实现注册

        Users userResult = userService.registerUser(userBo);


        CookieUtils.setCookie(request, response ,"user", JsonUtils.objectToJson(userResult),true);


        return JsonResult.ok();
    }

    @ApiOperation(value = "用户登录", notes = "用户登录", httpMethod = "POST")
    @PostMapping("/login")
    public JsonResult  login(@RequestBody  UserBo userBo, HttpServletRequest request, HttpServletResponse response) {
        String userName = userBo.getUserName();
        String password = userBo.getPassword();

        //1.校验用户名和密码不为空
        if (StringUtils.isBlank(userName)
                || StringUtils.isBlank(password)){
            return JsonResult.errMsg("用户名或者密码不能为空");
        }

        Users userResult = userService.loginUser(userBo);
        if(userResult == null) {
            return JsonResult.errMsg("用户名或者密码不正确");
        }
        //用户信息保存在cookie 中

        setNullProperty(userResult);

        CookieUtils.setCookie(request, response ,"user", JsonUtils.objectToJson(userResult),true);

        return JsonResult.ok(userResult);
    }

    private Users setNullProperty(Users userResult) {
        //不该给的字段别给
        userResult.setPassword(null);
        userResult.setMobile(null);
        userResult.setEmail(null);
        return userResult;

    }

    @ApiOperation(value = "用户退出", notes = "用户退出登陆", httpMethod = "POST")
    @PostMapping("/logout")
    public JsonResult  logout(@RequestParam String userId, HttpServletRequest request, HttpServletResponse response) {

        //清楚用户相关信息的cookie
       CookieUtils.deleteCookie(request, response,"user");

       return JsonResult.ok();
    }
}
