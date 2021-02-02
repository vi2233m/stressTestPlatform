package io.renren.modules.test.entity;

import java.util.Date;

/**
 * 展示文件ID的
 */
public class StressTestHistoryResourceDetailEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private Long id;

    /**
     * 报告ID
     */
    private Long reportId;

    /**
     * 版本号
     */
    private String version;

    /**
     * 文件ID
     */
    private Long fileId;

    /**
     * 文件名称
     */
    private String fileName;

    /**
     * 应用名称
     */
    private String appName;

    /**
     * 应用IP
     */
    private String appIp;

    /**
     * 用户CPU%
     */
    private float cpuUser;

    /**
     * 系统CPU%
     */
    private float cpuSys;

    /**
     * 等待CPU%
     */
    private float cpuWait;

    /**
     * 空闲内存
     */
    private float memFree;

    /**
     * 活跃内存
     */
    private float memActive;

    /**
     * 总内存
     */
    private float memTotal;

    /**
     * 带宽read(KB/s)
     */
    private float netRead;

    /**
     * 带宽write(KB/s)
     */
    private float netWrite;

    /**
     * 磁盘读(KB/s)
     */
    private float diskRead;

    /**
     * 磁盘写(KB/s)
     */
    private float diskWrite;

    /**
     * 提交的用户
     */
    private String addBy;

    /**
     * 修改的用户
     */
    private String updateBy;

    /**
     * 提交的时间
     */
    private Date addTime;

    /**
     * 更新的时间
     */
    private Date updateTime;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getReportId() {
        return reportId;
    }

    public void setReportId(Long reportId) {
        this.reportId = reportId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppIp() {
        return appIp;
    }

    public void setAppIp(String appIp) {
        this.appIp = appIp;
    }

    public float getCpuUser() {
        return cpuUser;
    }

    public void setCpuUser(float cpuUser) {
        this.cpuUser = cpuUser;
    }

    public float getCpuSys() {
        return cpuSys;
    }

    public void setCpuSys(float cpuSys) {
        this.cpuSys = cpuSys;
    }

    public float getCpuWait() {
        return cpuWait;
    }

    public void setCpuWait(float cpuWait) {
        this.cpuWait = cpuWait;
    }

    public float getMemFree() {
        return memFree;
    }

    public void setMemFree(float memFree) {
        this.memFree = memFree;
    }

    public float getMemActive() {
        return memActive;
    }

    public void setMemActive(float memActive) {
        this.memActive = memActive;
    }

    public float getMemTotal() {
        return memTotal;
    }

    public void setMemTotal(float memTotal) {
        this.memTotal = memTotal;
    }

    public float getNetRead() {
        return netRead;
    }

    public void setNetRead(float netRead) {
        this.netRead = netRead;
    }

    public float getNetWrite() {
        return netWrite;
    }

    public void setNetWrite(float netWrite) {
        this.netWrite = netWrite;
    }

    public float getDiskRead() {
        return diskRead;
    }

    public void setDiskRead(float diskRead) {
        this.diskRead = diskRead;
    }

    public float getDiskWrite() {
        return diskWrite;
    }

    public void setDiskWrite(float diskWrite) {
        this.diskWrite = diskWrite;
    }

    public String getAddBy() {
        return addBy;
    }

    public void setAddBy(String addBy) {
        this.addBy = addBy;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "StressTestHistoryResourceDetailEntity{" +
                "id=" + id +
                ", reportId=" + reportId +
                ", version='" + version + '\'' +
                ", fileId=" + fileId +
                ", fileName='" + fileName + '\'' +
                ", appName='" + appName + '\'' +
                ", appIp='" + appIp + '\'' +
                ", cpuUser=" + cpuUser +
                ", cpuSys=" + cpuSys +
                ", cpuWait=" + cpuWait +
                ", memFree=" + memFree +
                ", memActive=" + memActive +
                ", memTotal=" + memTotal +
                ", netRead=" + netRead +
                ", netWrite=" + netWrite +
                ", diskRead=" + diskRead +
                ", diskWrite=" + diskWrite +
                ", addBy='" + addBy + '\'' +
                ", updateBy='" + updateBy + '\'' +
                ", addTime=" + addTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
