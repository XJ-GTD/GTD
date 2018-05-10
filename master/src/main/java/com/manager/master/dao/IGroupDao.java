package com.manager.master.dao;

import com.manager.master.dto.GroupDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface IGroupDao {
    /**
     * 查询个人群组信息
     * @return
     */
    @Select("select gg.GROUP_ID,gg.USER_ID,gg.ROLE_ID,gg.GROUP_NAME,gr.ROLE_NAME from GTD_GROUP  gg " +
            "inner join gtd_role gr on gr.ROLE_ID=gg.ROLE_ID " +
            "where gg.USER_ID = #{userId}")
    List<GroupDto> findGroup(@Param("userId") int userId);
    /**
     * 查询个人群组信息
     * @return
     */
    @Insert("INSERT INTO gtd.gtd_group (`GROUP_ID`, `GROUP_NAME`, `USER_ID`, `ROLE_ID`) " +
            "VALUES ( #{groupId}, #{groupName},#{userId}, #{roleId})")
    void createGroup(@Param("groupId") String groupId,
                     @Param("groupName") String groupName,
                     @Param("userId") int userId,
                     @Param("roleId") int roleId);

}
