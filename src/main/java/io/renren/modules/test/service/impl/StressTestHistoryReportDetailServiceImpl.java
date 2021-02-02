package io.renren.modules.test.service.impl;

import io.renren.modules.test.dao.*;
import io.renren.modules.test.entity.StressTestHistoryReportDetailEntity;
import io.renren.modules.test.service.StressTestHistroyReportDetailService;
import io.renren.modules.test.utils.StressTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("stressTestHistroyReportDetailService")
public class StressTestHistoryReportDetailServiceImpl implements StressTestHistroyReportDetailService {

    @Autowired
    private StressTestHistoryReportDetailDao stressTestHistoryReportDetailDao;

    @Autowired
    private StressTestUtils stressTestUtils;

    @Override
    public StressTestHistoryReportDetailEntity queryObject(Long id) {
        return stressTestHistoryReportDetailDao.queryObject(id);
    }

    @Override
    public List<StressTestHistoryReportDetailEntity> queryList(Map<String, Object> map) {
        return stressTestHistoryReportDetailDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return stressTestHistoryReportDetailDao.queryTotal(map);
    }

    @Override
    public void save(StressTestHistoryReportDetailEntity reportDetailEntity) {
        reportDetailEntity.setAddTime(new Date());
        reportDetailEntity.setUpdateTime(new Date());
        stressTestHistoryReportDetailDao.save(reportDetailEntity);
    }

    @Override
    public void update(StressTestHistoryReportDetailEntity reportDetailEntity) {
        stressTestHistoryReportDetailDao.update(reportDetailEntity);
    }

    @Override
    public void deleteBatch(Long[] ids) {
        stressTestHistoryReportDetailDao.deleteBatch(ids);
    }

    @Override
    public StressTestHistoryReportDetailEntity queryObjectForDuplicate(StressTestHistoryReportDetailEntity reportDetailEntity) {
        return stressTestHistoryReportDetailDao.queryObjectForDuplicate(reportDetailEntity);
    }

}
