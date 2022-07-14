package org.qingqiao.vhr.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.qingqiao.vhr.bean.Hr;
import org.springframework.security.core.userdetails.UserDetails;
@Mapper
public interface HrMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Hr record);

    int insertSelective(Hr record);

    Hr selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Hr record);

    int updateByPrimaryKey(Hr record);

    UserDetails loadUserByUsername(String username);
}