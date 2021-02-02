package io.renren.modules.test.dao;

import io.renren.modules.sys.dao.BaseDao;
import io.renren.modules.test.entity.StressTestEnvironmentEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface StressTestEnvironmentDao extends BaseDao<StressTestEnvironmentEntity> {

    List<StressTestEnvironmentEntity> queryListForStart(String[] appIps);

    void updateReportPath(Map<String, Object> map);

}
