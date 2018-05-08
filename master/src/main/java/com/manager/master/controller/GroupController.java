package com.manager.master.controller;

import com.manager.master.dto.BaseOutDto;
import com.manager.master.dto.GroupDto;
import com.manager.master.service.IGroupService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 组群  zy 2018/5/7
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/group")
public class GroupController {

    private Logger logger = LogManager.getLogger(this.getClass());

    @Autowired
    IGroupService IGroupService;
    /**
     * 组群查询
     * @param
     * @return
     */
    @RequestMapping(value = "/find/{userId}", method = RequestMethod.GET)
    @ResponseBody
    public BaseOutDto find(@PathVariable int userId) {

        BaseOutDto outBean = new BaseOutDto();
        Map<String, List<GroupDto>> data = new HashMap<>();
        List<GroupDto> GroupData= IGroupService.findGroup(userId);

        data.put("Groupinfo", GroupData);
        outBean.setData(data);
        outBean.setCode("0");
        outBean.setMessage("[查询成功]");
        logger.info("[查询成功]"+ data);

        return outBean;
    }
}
