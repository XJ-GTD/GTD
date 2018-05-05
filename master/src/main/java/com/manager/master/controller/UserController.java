package com.manager.master.controller;

import com.manager.master.bean.UserAccountBean;
import com.manager.master.bean.UserInfoBean;
import com.manager.master.dto.BaseOutDto;
import com.manager.master.dto.UserInfoInDto;
import com.manager.master.service.IUserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * create by wzy on 2018/04/24.
 * 用户类
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/user")
public class UserController {

    private Logger logger = LogManager.getLogger(this.getClass());

    @Autowired
    IUserService userService;

    /**
     * 用户用户登录
     * @param
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public BaseOutDto login(@RequestBody UserInfoInDto inDto) {
        BaseOutDto outBean = new BaseOutDto();
        Map<String, UserAccountBean> data = new HashMap<>();

        UserAccountBean userAccountBean = userService.loginUser(inDto.getMobile(),inDto.getPassword());

        if (userAccountBean != null) {
            data.put("userinfo", userAccountBean);
            outBean.setData(data);
            outBean.setCode("0");
            outBean.setMessage("[登录成功]");
            logger.info("[登录成功]"+ data);
        } else {
            data.put("userinfo", userAccountBean);
            outBean.setData(data);
            outBean.setCode("1");
            outBean.setMessage("[登录失败]:用户名或密码输入错误!");
            logger.info("[登录失败]" + data);
        }
        return outBean;
    }
    /**
     * 查询用户信息
     * @param mobile
     * @return
     */
    @RequestMapping(value = "/find/{mobile}", method = RequestMethod.GET)
    @ResponseBody
    public BaseOutDto findUser(@PathVariable String mobile) {
        BaseOutDto outBean = new BaseOutDto();
        Map<String, UserInfoBean> data = new HashMap<>();
        UserInfoBean userInfoBean = userService.findUser(mobile);

        if (userInfoBean != null) {
            data.put("user", userInfoBean);
            outBean.setData(data);
            outBean.setCode("0");
            outBean.setMessage("Find Success!");
            logger.info("[查询成功]"+ data);
        } else {
            data.put("user", userInfoBean);
            outBean.setData(data);
            outBean.setCode("1");
            outBean.setMessage("Find fail!");
            logger.info("[查询失败]" + data);
        }

        return outBean;
    }
}
