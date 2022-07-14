package org.qingqiao.vhr.service;

import org.apache.ibatis.annotations.Param;
import org.qingqiao.vhr.bean.JobLevel;

import java.util.List;

public interface JobLevelService {

    List<JobLevel> getAllJoblevel();

    int addJobLevel(JobLevel jobLevel);

    int updateJobLevel(JobLevel jobLevel);

    int deleteJobLevel(Integer id);

    int deleteManyJobLevel(@Param("ids") Integer[] ids);
}
