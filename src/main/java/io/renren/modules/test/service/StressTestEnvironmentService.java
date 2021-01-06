package io.renren.modules.test.service;

import io.renren.modules.test.entity.StressTestEnvironmentEntity;
import java.util.List;
import java.util.Map;

/**
 * 性能测试用例
 * 
 */
public interface StressTestEnvironmentService {

	/**
	 * 查询环境信息列表
	 */
	List<StressTestEnvironmentEntity> queryList(Map<String, Object> map);
	
	/**
	 * 查询总数
	 */
	int queryTotal(Map<String, Object> map);

	/**
	 * 启动选中服务器监控
	 */
	String startUp(List<StressTestEnvironmentEntity> environmentList);

	/**
	 * 监控服务文件名称路径落库
	 */
	void updateReportPath(StressTestEnvironmentEntity environmentEntity);

	/**
	 * 保存报告对应的监控记录
	 */
	String saveMonitor(List<StressTestEnvironmentEntity> environmentList);

}
