package io.renren.modules.test.controller;

import io.renren.common.annotation.SysLog;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.common.utils.R;
import io.renren.common.validator.ValidatorUtils;
import io.renren.modules.test.entity.StressTestHistoryResourceDetailEntity;
import io.renren.modules.test.service.StressTestHistroyResourceDetailService;
import io.renren.modules.test.service.StressTestTendencyService;
import io.renren.modules.test.utils.StressTestUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 性能测试
 */
@RestController
@RequestMapping("/test/stressHistoryResource")
public class StressTestHistoryResourceDetailController {
    @Autowired
    private StressTestHistroyResourceDetailService stressTestHistroyResourceDetailService;

    @Autowired
    private StressTestTendencyService stressTestTendencyService;

    @Autowired
    private StressTestUtils stressTestUtils;

    /**
     * 性能明细列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("test:stress:historyResourceList")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(StressTestUtils.filterParms(params));
        List<StressTestHistoryResourceDetailEntity> resourceDetailEntityList = stressTestHistroyResourceDetailService.queryList(query);
        int total = stressTestHistroyResourceDetailService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(resourceDetailEntityList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 接口性能明细信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("test:stress:historyResourceInfo")
    public R info(@PathVariable("id") Long id) {
        StressTestHistoryResourceDetailEntity resourceDetailEntity = stressTestHistroyResourceDetailService.queryObject(id);

        return R.ok().put("resourceDetailEntity", resourceDetailEntity);
    }

    /**
     * 保存接口性能明细
     */
    @SysLog("新增接口性能明细")
    @RequestMapping("/save")
    @RequiresPermissions("test:stress:historyResourceSave")
    public R save(@RequestBody StressTestHistoryResourceDetailEntity resourceDetailEntity) {
        ValidatorUtils.validateEntity(resourceDetailEntity);
        if (null == stressTestTendencyService.queryObject(resourceDetailEntity.getFileId())) {
            return R.error(201, "<<历史性能趋势看板>>中无关联文件ID记录，请先添加相关文件记录！");
        }
        if(null != stressTestHistroyResourceDetailService.queryObjectForDuplicate(resourceDetailEntity)){
            return R.error(202, "已存在相同的记录，请检查必填项是否有相同记录");
        }
        stressTestHistroyResourceDetailService.save(resourceDetailEntity);

        return R.ok();
    }

    /**
     * 修改接口性能明细
     */
    @SysLog("修改接口性能明细")
    @RequestMapping("/update")
    @RequiresPermissions("test:stress:historyResourceUpdate")
    public R update(@RequestBody StressTestHistoryResourceDetailEntity resourceDetailEntity) {
        ValidatorUtils.validateEntity(resourceDetailEntity);

        stressTestHistroyResourceDetailService.update(resourceDetailEntity);

        return R.ok();
    }

    /**
     * 删除接口性能明细
     */
    @SysLog("删除接口性能明细")
    @RequestMapping("/delete")
    @RequiresPermissions("test:stress:historyResourceDelete")
    public R delete(@RequestBody Long[] ids) {
        stressTestHistroyResourceDetailService.deleteBatch(ids);

        return R.ok();
    }
}
