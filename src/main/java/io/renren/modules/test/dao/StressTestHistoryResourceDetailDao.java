package io.renren.modules.test.dao;

import io.renren.modules.sys.dao.BaseDao;
import io.renren.modules.test.entity.StressTestHistoryResourceDetailEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StressTestHistoryResourceDetailDao extends BaseDao<StressTestHistoryResourceDetailEntity> {

    List<StressTestHistoryResourceDetailEntity> queryIpList(Long fileId);

    List<String> queryVersionList(Long fileId);

    List<StressTestHistoryResourceDetailEntity> queryListForIp(@Param("fileId") Long fileId);

    StressTestHistoryResourceDetailEntity queryObjectForDuplicate(StressTestHistoryResourceDetailEntity resourceDetailEntity);

}
