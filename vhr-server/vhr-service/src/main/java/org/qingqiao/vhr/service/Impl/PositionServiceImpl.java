package org.qingqiao.vhr.service.Impl;

import org.qingqiao.vhr.bean.Position;
import org.qingqiao.vhr.mapper.PositionMapper;
import org.qingqiao.vhr.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PositionServiceImpl implements PositionService {
    @Autowired
    private PositionMapper positionMapper;
    @Override
    public List<Position> getAllPosition() {
        return positionMapper.getAllPosition();
    }

    @Override
    public int updatePosition(Position position) {
        return positionMapper.updateByPrimaryKeySelective(position);
    }

    @Override
    public int insertPosition(Position position) {
        return positionMapper.insertSelective(position);
    }

    @Override
    public int deletePosition(Integer id) {
        return positionMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int deletePositionByIDS(Integer[] ids) {
        return positionMapper.deletePositionByIDS(ids);
    }
}
