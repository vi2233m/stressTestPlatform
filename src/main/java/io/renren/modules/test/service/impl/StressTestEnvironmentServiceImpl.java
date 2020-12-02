package io.renren.modules.test.service.impl;

import io.renren.modules.test.dao.StressTestEnvironmentDao;
import io.renren.modules.test.entity.StressTestEnvironmentEntity;
import io.renren.modules.test.service.StressTestEnvironmentService;
import io.renren.modules.test.utils.StressTestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service("stressTestEnvironmentService")
public class StressTestEnvironmentServiceImpl implements StressTestEnvironmentService {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private StressTestEnvironmentDao stressTestEnvironmentDao;

    @Autowired
    private StressTestUtils stressTestUtils;

    @Override
    public List<StressTestEnvironmentEntity> queryList(Map<String, Object> map) {
        return stressTestEnvironmentDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return stressTestEnvironmentDao.queryTotal(map);
    }

}
