package io.renren.modules.test.entity;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;

/**
 * 性能测试报告对应资源监控表
 */
public class StressTestMonitorEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private Long monitorId;

    /**
     * 测试报告ID
     */
    private Long reportId;

    /**
     * 应用名
     */
    @NotBlank(message="应用名称不能为空")
    private String appName;

    /**
     * IP地址
     */
    @NotBlank(message="IP地址不能为空")
    @Pattern(regexp = "^(([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\.){3}([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])$")
    private String appIp;

    /**
     * 状态
     */
    private String status;

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
     * 资源监控报告文件路径
     */
    private String monitorPath;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getMonitorId() {
        return monitorId;
    }

    public void setMonitorId(Long monitorId) {
        this.monitorId = monitorId;
    }

    public Long getReportId() {
        return reportId;
    }

    public void setReportId(Long reportId) {
        this.reportId = reportId;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getMonitorPath() {
        return monitorPath;
    }

    public void setMonitorPath(String monitorPath) {
        this.monitorPath = monitorPath;
    }

    @Override
    public String toString() {
        return "StressTestMonitorEntity{" +
                "monitorId=" + monitorId +
                ", reportId=" + reportId +
                ", appName='" + appName + '\'' +
                ", appIp='" + appIp + '\'' +
                ", status='" + status + '\'' +
                ", addBy='" + addBy + '\'' +
                ", updateBy='" + updateBy + '\'' +
                ", addTime=" + addTime +
                ", updateTime=" + updateTime +
                ", monitorPath='" + monitorPath + '\'' +
                '}';
    }
}
