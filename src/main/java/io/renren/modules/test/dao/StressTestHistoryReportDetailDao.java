package io.renren.modules.test.dao;

import io.renren.modules.sys.dao.BaseDao;
import io.renren.modules.test.entity.StressTestHistoryReportDetailEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StressTestHistoryReportDetailDao extends BaseDao<StressTestHistoryReportDetailEntity> {

    List<String> queryApiList(Long fileId);

    List<String> queryVersionList(Long fileId);

    List<StressTestHistoryReportDetailEntity> queryListForApi(@Param("fileId") Long fileId);

    StressTestHistoryReportDetailEntity queryObjectForDuplicate(StressTestHistoryReportDetailEntity reportDetailEntity);

}
