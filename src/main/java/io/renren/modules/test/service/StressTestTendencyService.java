package io.renren.modules.test.service;

import io.renren.modules.test.entity.*;

import java.util.List;
import java.util.Map;

/**
 * 性能测试用例
 * 
 */
public interface StressTestTendencyService {

	/**
	 * 查询环境信息列表
	 */
	List<StressTestTendencyEntity> queryList(Map<String, Object> map);
	
	/**
	 * 查询总数
	 */
	int queryTotal(Map<String, Object> map);

	/**
	 * 根据ID，查询文件
	 */
	StressTestTendencyEntity queryObject(Long reportId);

	/**
	 * 更新场景信息
	 */
	void update(StressTestTendencyEntity testTendencyEntity);

	/**
	 * 保存接口性能明细
	 */
	void save(StressTestTendencyEntity testTendencyEntity);
	/**
	 * 获取测试报告中list
	 */
	Map<String, Object> getReportList(Long fileId);

	/**
	 * 获取测试资源中list
	 */
	Map<String, Object> getResourceList(Long fileId);


}
