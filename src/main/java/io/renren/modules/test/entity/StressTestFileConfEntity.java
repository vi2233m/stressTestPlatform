package io.renren.modules.test.entity;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Min;
import java.io.Serializable;
import java.util.Date;

/**
 *  测试脚本线程组运行配置
 */
@Component
public class StressTestFileConfEntity implements Serializable{
    private static final long serialVersionUID = 1L;

    private Long fileId; //脚本文件id
    private String onSampleError; //失败是否继续，默认continue

    private Long numThreads; // 线程数，并发数
    private String rampTime; // 所有线程在几秒内启动完成
    private boolean continueForever; //循环次数是否永远，默认 false
    private String loops; //循环次数，跟 continueForever 关联，continueForever为 true时，则必须填写次数

    private boolean scheduler; //  是否勾选调度器， 默认 false(不勾)
    private String duration; // 持续时间（s）
    private String delay; // 启动延时时间（s）,点击启动后延时多久运行

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getOnSampleError() {
        return onSampleError;
    }

    public void setOnSampleError(String onSampleError) {
        this.onSampleError = onSampleError;
    }

    public Long getNumThreads() {
        return numThreads;
    }

    public void setNumThreads(Long numThreads) {
        this.numThreads = numThreads;
    }

    public String getRampTime() {
        return rampTime;
    }

    public void setRampTime(String rampTime) {
        this.rampTime = rampTime;
    }

    public boolean getContinueForever() {
        return continueForever;
    }

    public void setContinueForever(boolean continueForever) {
        this.continueForever = continueForever;
    }

    public String getLoops() {
        return loops;
    }

    public void setLoops(String loops) {
        this.loops = loops;
    }

    public boolean getScheduler() {
        return scheduler;
    }

    public void setScheduler(boolean scheduler) {
        this.scheduler = scheduler;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDelay() {
        return delay;
    }

    public void setDelay(String delay) {
        this.delay = delay;
    }
}
