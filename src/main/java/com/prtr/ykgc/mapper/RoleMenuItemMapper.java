package com.prtr.ykgc.mapper;

import com.prtr.ykgc.entity.RoleMenuItem;
import com.prtr.ykgc.entity.RoleMenuItemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RoleMenuItemMapper {
    long countByExample(RoleMenuItemExample example);

    int deleteByExample(RoleMenuItemExample example);

    int insert(RoleMenuItem record);

    int insertSelective(RoleMenuItem record);

    List<RoleMenuItem> selectByExample(RoleMenuItemExample example);

    int updateByExampleSelective(@Param("record") RoleMenuItem record, @Param("example") RoleMenuItemExample example);

    int updateByExample(@Param("record") RoleMenuItem record, @Param("example") RoleMenuItemExample example);
}