package org.qingqiao.vhr.service;

import org.qingqiao.vhr.bean.Position;

import java.util.List;

public interface PositionService {
    List<Position> getAllPosition();

    int updatePosition(Position position);

    int insertPosition(Position position);

    int deletePosition(Integer id);

    int deletePositionByIDS(Integer[] ids);
}
