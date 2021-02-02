package io.renren.modules.test.controller;

import io.renren.common.annotation.SysLog;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.common.utils.R;
import io.renren.common.validator.ValidatorUtils;
import io.renren.modules.test.entity.StressTestTendencyEntity;
import io.renren.modules.test.service.StressTestTendencyService;
import io.renren.modules.test.utils.StressTestUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 压力测试用例文件
 *
 */
@RestController
@RequestMapping("/test/stressTendency")
public class StressTestTendencyController {
    @Autowired
    private StressTestTendencyService stressTestTendencyService;

    /**
     * 趋势场景列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("test:stress:tendencyList")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(StressTestUtils.filterParms(params));
        List<StressTestTendencyEntity> tendencyList = stressTestTendencyService.queryList(query);
        int total = stressTestTendencyService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(tendencyList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 查询具体场景（脚本文件）信息
     */
    @RequestMapping("/info/{fileId}")
    @RequiresPermissions("test:stress:tendencyInfo")
    public R info(@PathVariable("fileId") Long fileId) {
        StressTestTendencyEntity testTendencyEntity = stressTestTendencyService.queryObject(fileId);
        return R.ok().put("stressTestTendency", testTendencyEntity);
    }

    /**
     * 修改测试场景描述
     */
    @SysLog("修改趋势文件描述信息")
    @RequestMapping("/update")
    @RequiresPermissions("test:stress:tendencyUpdate")
    public R update(@RequestBody StressTestTendencyEntity testTendencyEntity) {
        ValidatorUtils.validateEntity(testTendencyEntity);
        stressTestTendencyService.update(testTendencyEntity);
        return R.ok();
    }

    /**
     * 保存测试场景趋势记录
     */
    @SysLog("新增测试场景趋势记录")
    @RequestMapping("/save")
    @RequiresPermissions("test:stress:tendencySave")
    public R save(@RequestBody StressTestTendencyEntity testTendencyEntity) {
        ValidatorUtils.validateEntity(testTendencyEntity);
        if (null != stressTestTendencyService.queryObject(testTendencyEntity.getFileId())) {
            return R.error(201, "文件ID已存在！");
        }
        stressTestTendencyService.save(testTendencyEntity);

        return R.ok();
    }

    /**
     * 趋势图展现数据信息
     */
    @RequestMapping("/statInfo/{fileId}")
    public R statInfo(@PathVariable("fileId") Long fileId){

        Map<String, Object> reportMap = stressTestTendencyService.getReportList(fileId);
        Map<String, Object> resourceMap = stressTestTendencyService.getResourceList(fileId);
        Map<String, Object> map = new HashMap<>();
        map.putAll(resourceMap);
        map.putAll(reportMap);

        return R.ok().put("statInfo", map);
    }

}