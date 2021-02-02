package io.renren.modules.test.service;

import io.renren.modules.test.entity.StressTestHistoryReportDetailEntity;

import java.util.List;
import java.util.Map;

/**
 * 性能测试用例
 * 
 */
public interface StressTestHistroyReportDetailService {

	/**
	 * 根据ID，查询接口性能明细
	 */
	StressTestHistoryReportDetailEntity queryObject(Long id);
	
	/**
	 * 查询明细报告列表
	 */
	List<StressTestHistoryReportDetailEntity> queryList(Map<String, Object> map);
	
	/**
	 * 查询总数
	 */
	int queryTotal(Map<String, Object> map);
	
	/**
	 * 保存接口性能明细
	 */
	void save(StressTestHistoryReportDetailEntity reportDetailEntity);

	/**
	 * 更新接口性能明细数据
	 */
	void update(StressTestHistoryReportDetailEntity reportDetailEntity);

	/**
	 * 批量删除
	 */
	void deleteBatch(Long[] ids);

	/**
	 * 查询是否有重复数据
	 */
	StressTestHistoryReportDetailEntity queryObjectForDuplicate(StressTestHistoryReportDetailEntity reportDetailEntity);
}
