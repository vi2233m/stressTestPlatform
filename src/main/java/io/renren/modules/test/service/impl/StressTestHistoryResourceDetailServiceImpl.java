package io.renren.modules.test.service.impl;

import io.renren.modules.test.dao.StressTestHistoryResourceDetailDao;
import io.renren.modules.test.entity.StressTestHistoryResourceDetailEntity;
import io.renren.modules.test.service.StressTestHistroyResourceDetailService;
import io.renren.modules.test.utils.StressTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("stressTestHistroyResourceDetailService")
public class StressTestHistoryResourceDetailServiceImpl implements StressTestHistroyResourceDetailService {

    @Autowired
    private StressTestHistoryResourceDetailDao stressTestHistoryResourceDetailDao;

    @Autowired
    private StressTestUtils stressTestUtils;

    @Override
    public StressTestHistoryResourceDetailEntity queryObject(Long id) {
        return stressTestHistoryResourceDetailDao.queryObject(id);
    }

    @Override
    public List<StressTestHistoryResourceDetailEntity> queryList(Map<String, Object> map) {
        return stressTestHistoryResourceDetailDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return stressTestHistoryResourceDetailDao.queryTotal(map);
    }

    @Override
    public void save(StressTestHistoryResourceDetailEntity resourceDetailEntity) {
        resourceDetailEntity.setAddTime(new Date());
        resourceDetailEntity.setUpdateTime(new Date());
        stressTestHistoryResourceDetailDao.save(resourceDetailEntity);
    }

    @Override
    public void update(StressTestHistoryResourceDetailEntity resourceDetailEntity) {
        stressTestHistoryResourceDetailDao.update(resourceDetailEntity);
    }

    @Override
    public void deleteBatch(Long[] ids) {
        stressTestHistoryResourceDetailDao.deleteBatch(ids);
    }

    @Override
    public StressTestHistoryResourceDetailEntity queryObjectForDuplicate(StressTestHistoryResourceDetailEntity resourceDetailEntity) {
        return stressTestHistoryResourceDetailDao.queryObjectForDuplicate(resourceDetailEntity);
    }

}
