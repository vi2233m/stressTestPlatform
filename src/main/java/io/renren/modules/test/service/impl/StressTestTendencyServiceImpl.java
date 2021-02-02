package io.renren.modules.test.service.impl;

import io.renren.modules.test.dao.StressTestTendencyDao;
import io.renren.modules.test.dao.StressTestHistoryReportDetailDao;
import io.renren.modules.test.dao.StressTestHistoryResourceDetailDao;
import io.renren.modules.test.entity.StressTestHistoryReportDetailEntity;
import io.renren.modules.test.entity.StressTestHistoryResourceDetailEntity;
import io.renren.modules.test.entity.StressTestTendencyEntity;
import io.renren.modules.test.service.StressTestTendencyService;
import io.renren.modules.test.utils.StressTestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("stressTestTendencyService")
public class StressTestTendencyServiceImpl implements StressTestTendencyService {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private StressTestTendencyDao stressTestTendencyDao;

    @Autowired
    private StressTestHistoryReportDetailDao stressTestHistoryReportDetailDao;

    @Autowired
    private StressTestHistoryResourceDetailDao stressTestHistoryResourceDetailDao;

    @Autowired
    private StressTestUtils stressTestUtils;

    @Override
    public List<StressTestTendencyEntity> queryList(Map<String, Object> map) {
        return stressTestTendencyDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return stressTestTendencyDao.queryTotal(map);
    }

    @Override
    public StressTestTendencyEntity queryObject(Long fileId) {
        return stressTestTendencyDao.queryObject(fileId);
    }

    @Override
    public void update(StressTestTendencyEntity testTendencyEntity) {
        testTendencyEntity.setUpdateTime(new Date());
        stressTestTendencyDao.update(testTendencyEntity);
    }

    @Override
    public void save(StressTestTendencyEntity testTendencyEntity) {
        testTendencyEntity.setAddTime(new Date());
        testTendencyEntity.setUpdateTime(new Date());
        stressTestTendencyDao.save(testTendencyEntity);
    }

    @Override
    public Map<String, Object> getReportList(Long fileId){
        List<String> apiNames = stressTestHistoryReportDetailDao.queryApiList(fileId);
        List<String> versions = stressTestHistoryReportDetailDao.queryVersionList(fileId);
        List<StressTestHistoryReportDetailEntity> reportDetailEntities1 = stressTestHistoryReportDetailDao.queryListForApi(fileId);
        List<StressTestHistoryReportDetailEntity> reportDetailEntities = new ArrayList<>();

        Map<String, Object> map = new HashMap<>();
        Map<String, Object> average = new HashMap<>();
        Map<String, Object> ninetieth = new HashMap<>();
        Map<String, Object> tps = new HashMap<>();
        // 所有版本号和接口名称 组合，当库中没有没有数据时，设置为0
        // 主要是为了避免有些场景新增或者减少测试接口，导致数据异常，紊乱
        for (String version : versions){
            for (String apiName : apiNames){
                StressTestHistoryReportDetailEntity reportDetailEntity = new StressTestHistoryReportDetailEntity();
                reportDetailEntity.setVersion(version);
                reportDetailEntity.setApiName(apiName);
                reportDetailEntity.setAverage(0);
                reportDetailEntity.setNinetiethPct(0);
                reportDetailEntity.setTps(0);

                for (StressTestHistoryReportDetailEntity reportDetailEntity1 : reportDetailEntities1){
                    if (reportDetailEntity1.getVersion().equals(version) && reportDetailEntity1.getApiName().equals(apiName)){
                        reportDetailEntity.setAverage(reportDetailEntity1.getAverage());
                        reportDetailEntity.setNinetiethPct(reportDetailEntity1.getNinetiethPct());
                        reportDetailEntity.setTps(reportDetailEntity1.getTps());
                    }
                }
                reportDetailEntities.add(reportDetailEntity);
            }
        }
        // 根据接口名称，把各版本的数据放到列表中前端展示
        for (String apiName : apiNames){
            List<Float> averageList = new ArrayList<>();
            List<Float> ninetiethList = new ArrayList<>();
            List<Float> tpsList = new ArrayList<>();
            for (StressTestHistoryReportDetailEntity reportDetailEntity2 : reportDetailEntities){
                if (apiName.equals(reportDetailEntity2.getApiName())){
                    averageList.add(reportDetailEntity2.getAverage());
                    ninetiethList.add(reportDetailEntity2.getNinetiethPct());
                    tpsList.add(reportDetailEntity2.getTps());
                }
            }
            average.put(apiName, averageList);
            ninetieth.put(apiName, ninetiethList);
            tps.put(apiName, tpsList);
        }

        map.put("responseTimesMap", average);
        map.put("ninetiethPctTimesMap", ninetieth);
        map.put("throughputMap", tps);
        map.put("versionList", versions);

        return map;
    }

    @Override
    public Map<String, Object> getResourceList(Long fileId) {
        List<StressTestHistoryResourceDetailEntity> resourceDetailEntities = stressTestHistoryResourceDetailDao.queryIpList(fileId);
        List<String> versions = stressTestHistoryResourceDetailDao.queryVersionList(fileId);
        List<StressTestHistoryResourceDetailEntity> resourceDetailEntities2 = stressTestHistoryResourceDetailDao.queryListForIp(fileId);
        List<StressTestHistoryResourceDetailEntity> resourceDetailEntities1 = new ArrayList<>();

        Map<String, Object> map = new HashMap<>();
        Map<String, Object> cpuPct = new HashMap<>();
        Map<String, Object> memFree = new HashMap<>();
        Map<String, Object> netRead = new HashMap<>();
        Map<String, Object> netWrite = new HashMap<>();
        Map<String, Object> diskRead = new HashMap<>();
        Map<String, Object> diskWrite = new HashMap<>();
        // 待优化，
        for (String version : versions){
            for (StressTestHistoryResourceDetailEntity resourceDetailEntity : resourceDetailEntities){
                StressTestHistoryResourceDetailEntity resourceDetailEntity1 = new StressTestHistoryResourceDetailEntity();
                resourceDetailEntity1.setVersion(version);
                resourceDetailEntity1.setAppName(resourceDetailEntity.getAppName());
                resourceDetailEntity1.setAppIp(resourceDetailEntity.getAppIp());

                for (StressTestHistoryResourceDetailEntity resourceDetailEntity2 : resourceDetailEntities2){
                    if (resourceDetailEntity2.getVersion().equals(version) && resourceDetailEntity2.getAppIp().equals(resourceDetailEntity.getAppIp())){
                        resourceDetailEntity1.setCpuSys(resourceDetailEntity2.getCpuSys());
                        resourceDetailEntity1.setCpuUser(resourceDetailEntity2.getCpuUser());
                        resourceDetailEntity1.setCpuWait(resourceDetailEntity2.getCpuWait());
                        resourceDetailEntity1.setMemFree(resourceDetailEntity2.getMemFree());
                        resourceDetailEntity1.setNetRead(resourceDetailEntity2.getNetRead());
                        resourceDetailEntity1.setNetWrite(resourceDetailEntity2.getNetWrite());
                        resourceDetailEntity1.setDiskRead(resourceDetailEntity2.getDiskRead());
                        resourceDetailEntity1.setDiskWrite(resourceDetailEntity2.getDiskWrite());
                    }
                }
                resourceDetailEntities1.add(resourceDetailEntity1);
            }
        }
        // 根据应用名称+ip名称，把各版本的数据放到列表中前端展示
        for (StressTestHistoryResourceDetailEntity resourceDetailEntity: resourceDetailEntities1){
            String appName = resourceDetailEntity.getAppName() + resourceDetailEntity.getAppIp();
            List<Float> cpuPctList = new ArrayList<>();
            List<Float> memFreeList = new ArrayList<>();
            List<Float> netReadList = new ArrayList<>();
            List<Float> netWriteList = new ArrayList<>();
            List<Float> diskReadList = new ArrayList<>();
            List<Float> diskWriteList = new ArrayList<>();
            for (StressTestHistoryResourceDetailEntity resourceDetailEntity1 : resourceDetailEntities1){
                if (resourceDetailEntity.getAppIp().equals(resourceDetailEntity1.getAppIp())){
                    cpuPctList.add(resourceDetailEntity1.getCpuSys() + resourceDetailEntity1.getCpuUser() + resourceDetailEntity1.getCpuWait());
                    memFreeList.add(resourceDetailEntity1.getMemFree());
                    netReadList.add(resourceDetailEntity1.getNetRead());
                    netWriteList.add(resourceDetailEntity1.getNetWrite());
                    diskReadList.add(resourceDetailEntity1.getDiskRead());
                    diskWriteList.add(resourceDetailEntity1.getDiskWrite());
                }
            }
            cpuPct.put(appName, cpuPctList);
            memFree.put(appName, memFreeList);
            netRead.put(appName, netReadList);
            netWrite.put(appName, netWriteList);
            diskRead.put(appName, diskReadList);
            diskWrite.put(appName, diskWriteList);
        }
        map.put("cpuPctMap", cpuPct);
        map.put("memFreeMap", memFree);
        map.put("netReadMap", netRead);
        map.put("netWriteMap", netWrite);
        map.put("diskReadMap", diskRead);
        map.put("diskWriteMap", diskWrite);
        map.put("versionList", versions);

        return map;
    }


}
