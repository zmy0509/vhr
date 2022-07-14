package org.qingqiao.vhr.service.Impl;

import org.qingqiao.vhr.bean.JobLevel;
import org.qingqiao.vhr.mapper.JobLevelMapper;
import org.qingqiao.vhr.service.JobLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobLevelServiceImpl implements JobLevelService {
    @Autowired
    private JobLevelMapper jobLevelMapper;

    @Override
    public List<JobLevel> getAllJoblevel() {
        return jobLevelMapper.getAllJobLevel();
    }

    @Override
    public int addJobLevel(JobLevel jobLevel) {
        return jobLevelMapper.insertSelective(jobLevel);
    }

    @Override
    public int updateJobLevel(JobLevel jobLevel) {
        return jobLevelMapper.updateByPrimaryKeySelective(jobLevel);
    }

    @Override
    public int deleteJobLevel(Integer id) {
        return jobLevelMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int deleteManyJobLevel(Integer[] ids) {
        return jobLevelMapper.deleteManyJobLevel(ids);
    }
}
