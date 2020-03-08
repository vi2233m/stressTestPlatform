package io.renren;

import io.renren.modules.test.entity.StressTestFileConfEntity;
import io.renren.modules.test.service.StressTestFileService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoTest {
    @Autowired
    private StressTestFileService stressTestFileService;
    @Autowired
    StressTestFileConfEntity stressTestFileConf;

    @Test
    public void test(){
//        List<StressTestFileConfEntity> list = stressTestFileService.getJmeterRunParams(2L);
//        for (StressTestFileConfEntity stressTestFileConf : list) {
//            System.out.println("getThreadGroupName" + stressTestFileConf.getThreadGroupName());
//            System.out.println("isThreadEnabled" + stressTestFileConf.isThreadEnabled());
//            System.out.println("OnSampleError" + stressTestFileConf.getOnSampleError());
//            System.out.println("NumThreads" + stressTestFileConf.getNumThreads());
//            System.out.println("RampTime" + stressTestFileConf.getRampTime());
//            System.out.println("ContinueForever" + stressTestFileConf.isContinueForever());
//            System.out.println("Loops" + stressTestFileConf.getLoops());
//            System.out.println("Scheduler" + stressTestFileConf.isScheduler());
//            System.out.println("Duration" + stressTestFileConf.getDuration());
//            System.out.println("Delay" + stressTestFileConf.getDelay());
//        }
    }

    @Test
    public void test1(){
//        stressTestFileConf.setFileId(9L);
//        stressTestFileConf.setOnSampleError("continue");
//        stressTestFileConf.setNumThreads(1000L);
//        stressTestFileConf.setRampTime("1000");
//        stressTestFileConf.setDelay("2000");
//        stressTestFileService.UpdateJmeterRunParams(stressTestFileConf);
    }

}
