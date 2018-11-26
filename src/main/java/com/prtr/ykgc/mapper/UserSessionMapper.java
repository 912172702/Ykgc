package com.prtr.ykgc.mapper;

import com.prtr.ykgc.entity.UserSession;
import com.prtr.ykgc.entity.UserSessionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserSessionMapper {
    long countByExample(UserSessionExample example);

    int deleteByExample(UserSessionExample example);

    int insert(UserSession record);

    int insertSelective(UserSession record);

    List<UserSession> selectByExample(UserSessionExample example);

    int updateByExampleSelective(@Param("record") UserSession record, @Param("example") UserSessionExample example);

    int updateByExample(@Param("record") UserSession record, @Param("example") UserSessionExample example);
}