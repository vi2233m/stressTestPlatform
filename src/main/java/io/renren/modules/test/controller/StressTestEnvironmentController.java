package io.renren.modules.test.controller;

import com.sun.xml.internal.bind.v2.TODO;
import io.renren.common.annotation.SysLog;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.common.utils.R;
import io.renren.modules.test.dao.StressTestEnvironmentDao;
import io.renren.modules.test.entity.StressTestEnvironmentEntity;
import io.renren.modules.test.service.StressTestEnvironmentService;
import io.renren.modules.test.utils.StressTestUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * 压力测试环境列表
 */
@RestController
@RequestMapping("/test/stressEnv")
public class StressTestEnvironmentController {
    @Autowired
    private StressTestEnvironmentService stressTestEnvironmentService;

    @Autowired
    private StressTestEnvironmentDao stressTestEnvironmentDao;

    @Autowired
    private StressTestUtils stressTestUtils;

    /**
     * 分布式节点列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("test:stress:environmentList")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(StressTestUtils.filterParms(params));
        List<StressTestEnvironmentEntity> environmentList = stressTestEnvironmentService.queryList(query);
        int total = stressTestEnvironmentService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(environmentList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 启动指定IP的easyNmon监控
     */
    @SysLog("启动应用节点资源监控")
    @RequestMapping("/start")
    @RequiresPermissions("test:stress:startUp")
    public R start(@RequestBody String[] appIps) {
        // TODO: 2020/12/24 批量勾选，如果有一台服务器未启动服务异常后，后面的服务器就无法启动监控了  Done
        // TODO: 2020/12/24 超时异常最后能够提示前端哪台服务器未启动

        Set<String> ipList = new HashSet<>(Arrays.asList(appIps));
        appIps = ipList.toArray(new String[ipList.size()]);
        List<StressTestEnvironmentEntity> environmentList = stressTestEnvironmentDao.queryListForStart(appIps);
        // 启动easyNmon服务器资源监控
        String msg = stressTestEnvironmentService.startUp(environmentList);

        return R.ok(msg);
    }

    /**
     * 保存测试报告对应的监控记录
     */
    @SysLog("保存报告资源监控")
    @RequestMapping("/saveMonitor")
    @RequiresPermissions("test:stress:saveMonitor")
    public R saveMonitor(@RequestBody String[] appIps) {

        List<StressTestEnvironmentEntity> environmentList = stressTestEnvironmentDao.queryListForStart(appIps);
        // 启动easyNmon服务器资源监控
        String msg = stressTestEnvironmentService.saveMonitor(environmentList);

        return R.ok(msg);
    }

}
