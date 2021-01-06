package io.renren.modules.test.entity;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;

/**
 * 性能测试环境列表及监控
 */
public class StressTestEnvironmentEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private Long slaveId;

    /**
     * IP地址
     */
    @NotBlank(message="IP地址不能为空")
    @Pattern(regexp = "^(([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\.){3}([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])$")
    private String ip;

    /**
     * 应用名
     */
    @NotBlank(message="应用名称不能为空")
    private String appName;

    /**
     * 安装路径
     */
    private String homeDir;

    /**
     * log路径
     */
    private String logDir;

    /**
     * cpu核数
     */
    private Integer cpu;

    /**
     * 内存
     */
    private String mem;

    /**
     * 磁盘
     */
    private String disk;

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

    /**
     * 资源监控端口
     */
    private Integer port;

    /**
     * 资源监控报告文件路径
     */
    private String reportPath;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getSlaveId() {
        return slaveId;
    }

    public void setSlaveId(Long slaveId) {
        this.slaveId = slaveId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getHomeDir() {
        return homeDir;
    }

    public void setHomeDir(String homeDir) {
        this.homeDir = homeDir;
    }

    public String getLogDir() {
        return logDir;
    }

    public void setLogDir(String logDir) {
        this.logDir = logDir;
    }

    public Integer getCpu() {
        return cpu;
    }

    public void setCpu(Integer cpu) {
        this.cpu = cpu;
    }

    public String getMem() {
        return mem;
    }

    public void setMem(String mem) {
        this.mem = mem;
    }

    public String getDisk() {
        return disk;
    }

    public void setDisk(String disk) {
        this.disk = disk;
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

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getReportPath() {
        return reportPath;
    }

    public void setReportPath(String reportPath) {
        this.reportPath = reportPath;
    }

    @Override
    public String toString() {
        return "StressTestEnvironmentEntity{" +
                "slaveId=" + slaveId +
                ", ip='" + ip + '\'' +
                ", appName='" + appName + '\'' +
                ", homeDir='" + homeDir + '\'' +
                ", logDir='" + logDir + '\'' +
                ", cpu=" + cpu +
                ", mem='" + mem + '\'' +
                ", disk='" + disk + '\'' +
                ", addBy='" + addBy + '\'' +
                ", updateBy='" + updateBy + '\'' +
                ", addTime=" + addTime +
                ", updateTime=" + updateTime +
                ", port=" + port +
                ", reportPath='" + reportPath + '\'' +
                '}';
    }
}
