package io.renren.modules.test.dao;

import io.renren.modules.sys.dao.BaseDao;
import io.renren.modules.test.entity.StressTestEnvironmentEntity;
import io.renren.modules.test.entity.StressTestMonitorEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface StressTestMonitorDao extends BaseDao<StressTestMonitorEntity> {

    int queryTotal(StressTestMonitorEntity stressTestMonitorEntity);

    List<StressTestMonitorEntity> queryList(Long reportId);

}
