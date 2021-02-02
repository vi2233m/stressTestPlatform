package io.renren.modules.test.controller;

import io.renren.common.annotation.SysLog;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.common.utils.R;
import io.renren.common.validator.ValidatorUtils;
import io.renren.modules.test.entity.StressTestHistoryReportDetailEntity;
import io.renren.modules.test.service.StressTestHistroyReportDetailService;
import io.renren.modules.test.service.StressTestTendencyService;
import io.renren.modules.test.utils.StressTestUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;

/**
 * 性能测试
 */
@RestController
@RequestMapping("/test/stressHistoryReport")
public class StressTestHistoryReportDetailController {
    @Autowired
    private StressTestHistroyReportDetailService stressTestHistroyReportDetailService;

    @Autowired
    private StressTestTendencyService stressTestTendencyService;

    @Autowired
    private StressTestUtils stressTestUtils;

    /**
     * 性能明细列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("test:stress:historyReportList")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(StressTestUtils.filterParms(params));
        List<StressTestHistoryReportDetailEntity> stressTestHistoryReportDetailEntityList = stressTestHistroyReportDetailService.queryList(query);
        int total = stressTestHistroyReportDetailService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(stressTestHistoryReportDetailEntityList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 接口性能明细信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("test:stress:historyReportInfo")
    public R info(@PathVariable("id") Long id) {
        StressTestHistoryReportDetailEntity reportDetailEntity = stressTestHistroyReportDetailService.queryObject(id);

        return R.ok().put("reportDetailEntity", reportDetailEntity);
    }

    /**
     * 保存接口性能明细
     */
    @SysLog("新增接口性能明细")
    @RequestMapping("/save")
    @RequiresPermissions("test:stress:historyReportSave")
    public R save(@RequestBody StressTestHistoryReportDetailEntity reportDetailEntity) {
        ValidatorUtils.validateEntity(reportDetailEntity);
        if (null == stressTestTendencyService.queryObject(reportDetailEntity.getFileId())) {
            return R.error(201, "<<历史性能趋势看板>>中无关联文件ID记录，请先添加相关文件记录！");
        }
        if(null != stressTestHistroyReportDetailService.queryObjectForDuplicate(reportDetailEntity)){
            return R.error(202, "已存在相同记录，请检查必填项是否有相同记录");
        }
        stressTestHistroyReportDetailService.save(reportDetailEntity);

        return R.ok();
    }

    /**
     * 修改接口性能明细
     */
    @SysLog("修改接口性能明细")
    @RequestMapping("/update")
    @RequiresPermissions("test:stress:historyReportUpdate")
    public R update(@RequestBody StressTestHistoryReportDetailEntity reportDetailEntity) {
        ValidatorUtils.validateEntity(reportDetailEntity);

        stressTestHistroyReportDetailService.update(reportDetailEntity);

        return R.ok();
    }

    /**
     * 删除接口性能明细
     */
    @SysLog("删除接口性能明细")
    @RequestMapping("/delete")
    @RequiresPermissions("test:stress:historyReportDelete")
    public R delete(@RequestBody Long[] ids) {
        stressTestHistroyReportDetailService.deleteBatch(ids);

        return R.ok();
    }
}
