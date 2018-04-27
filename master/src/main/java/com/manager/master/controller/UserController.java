package com.manager.master.controller;

import com.manager.master.bean.UserInfoBean;
import com.manager.master.dto.BaseOutDto;
import com.manager.master.service.IUserService;
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

    @Autowired
    IUserService userService;

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
        UserInfoBean userInfoBean = new UserInfoBean();

        userInfoBean = userService.findUser(mobile);

        data.put("user", userInfoBean);
        outBean.setData(data);
        outBean.setCode("0");
        outBean.setMessage("Find Success!");
        return outBean;
    }
}
