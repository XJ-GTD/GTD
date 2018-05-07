package com.manager.master.service.serviceImpl;

import com.manager.master.dao.GroupDao;
import com.manager.master.dto.GroupDto;
import com.manager.master.service.GroupService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
@Service
@Transactional
public class GroupServicelmpl implements GroupService {

    @Resource
    private GroupDao groupDao;

    @Override
    public List<GroupDto> findGroup(GroupDto inDto) {
        int userId=inDto.getUserId();
        return groupDao.findGroup(userId);
    }

    @Override
    public void createGroup(String groupId,String groupName,int userId,int roleId) {
        groupDao.createGroup(groupId,groupName,userId,roleId);
    }
}
