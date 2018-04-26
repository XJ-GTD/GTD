package com.manager.master.controller;

import com.manager.master.bean.BaseOutBean;
import com.manager.master.bean.UserAccountBean;
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
     * 查询用户账户信息
     * @param userName
     * @return
     */
    @RequestMapping(value = "/find/{userName}", method = RequestMethod.GET)
    public BaseOutBean findUser(@PathVariable String userName) {
        BaseOutBean outBean = new BaseOutBean();
        Map<String, UserAccountBean> data = new HashMap<>();
        UserAccountBean userAccountBean = new UserAccountBean();

        userAccountBean = userService.findUser();

        data.put("user", userAccountBean);
        outBean.setData(data);
        outBean.setCode("0");
        outBean.setMessage("Find Success!");
        return outBean;
    }
}
