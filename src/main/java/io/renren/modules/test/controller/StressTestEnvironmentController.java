package io.renren.modules.test.controller;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.common.utils.R;
import io.renren.modules.test.entity.StressTestEnvironmentEntity;
import io.renren.modules.test.service.StressTestEnvironmentService;
import io.renren.modules.test.utils.StressTestUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 压力测试环境列表
 */
@RestController
@RequestMapping("/test/stressEnv")
public class StressTestEnvironmentController {
    @Autowired
    private StressTestEnvironmentService stressTestEnvironmentService;

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

}
