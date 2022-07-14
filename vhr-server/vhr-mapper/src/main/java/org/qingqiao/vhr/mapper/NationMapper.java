package org.qingqiao.vhr.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.qingqiao.vhr.bean.Nation;
import org.qingqiao.vhr.utils.ResponseBean;

import java.util.List;

@Mapper
public interface NationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Nation record);

    int insertSelective(Nation record);

    Nation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Nation record);

    int updateByPrimaryKey(Nation record);

    List<Nation> getAllNation();
}