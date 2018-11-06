package com.prtr.ykgc.mapper;

import com.prtr.ykgc.entity.Police;
import com.prtr.ykgc.entity.PoliceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PoliceMapper {
    long countByExample(PoliceExample example);

    int deleteByExample(PoliceExample example);

    int deleteByPrimaryKey(String id);

    int insert(Police record);

    int insertSelective(Police record);

    List<Police> selectByExample(PoliceExample example);

    Police selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Police record, @Param("example") PoliceExample example);

    int updateByExample(@Param("record") Police record, @Param("example") PoliceExample example);

    int updateByPrimaryKeySelective(Police record);

    int updateByPrimaryKey(Police record);
}