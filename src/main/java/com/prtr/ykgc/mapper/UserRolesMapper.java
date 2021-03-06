package com.prtr.ykgc.mapper;

import com.prtr.ykgc.entity.UserRoles;
import com.prtr.ykgc.entity.UserRolesExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserRolesMapper {
    long countByExample(UserRolesExample example);

    int deleteByExample(UserRolesExample example);

    int insert(UserRoles record);

    int insertSelective(UserRoles record);

    List<UserRoles> selectByExample(UserRolesExample example);

    int updateByExampleSelective(@Param("record") UserRoles record, @Param("example") UserRolesExample example);

    int updateByExample(@Param("record") UserRoles record, @Param("example") UserRolesExample example);
}