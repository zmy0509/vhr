package org.qingqiao.vhr.controller.system.basic;

import org.qingqiao.vhr.bean.Position;
import org.qingqiao.vhr.service.PositionService;
import org.qingqiao.vhr.utils.ResponseBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/system/basic")
public class PositionController {

    @Autowired
    private PositionService postionService;

    @GetMapping("/pos")
    public List<Position> getAllPositions(){
        return postionService.getAllPosition();
    }
    @PutMapping("/pos")
    public ResponseBean updatePosition (@RequestBody Position position){
        if (postionService.updatePosition(position)==1){
            return ResponseBean.ok("修改成功");
        }
        return ResponseBean.ok("修改失败");
    }
    @PostMapping("/pos")
    public ResponseBean insertintoPosition (@RequestBody Position position){
        if (postionService.insertPosition(position)==1){
            return ResponseBean.ok("添加成功");
        }
        return ResponseBean.ok("添加失败");
    }
    @DeleteMapping("/pos/{id}")
    public ResponseBean deletePosition (@PathVariable Integer id){
        if(postionService.deletePosition(id)==1){
            return ResponseBean.ok("删除成功");
        }
        return ResponseBean.ok("删除成功");
    }
    @DeleteMapping("/pos/many/{ids}")
    public ResponseBean deletePositionMany (@PathVariable Integer[] ids){
        if(postionService.deletePositionByIDS(ids)==ids.length){
            return ResponseBean.ok("删除成功");
        }
        return ResponseBean.ok("删除成功");
    }
}
