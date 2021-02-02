package io.renren.modules.test.entity;

import java.util.Date;

/**
 * 展示文件ID的
 */
public class StressTestHistoryReportDetailEntity {

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
     * 接口名称
     */
    private String apiName;

    /**
     * 总请求数
     */
    private Long samples;

    /**
     * 失败数
     */
    private Long ko;

    /**
     * 失败率
     */
    private float errorPct;

    /**
     * 平均响应时间
     */
    private float average;

    /**
     * 最小响应时间
     */
    private float min;

    /**
     * 最大响应时间
     */
    private float max;

    /**
     * 90%响应时间
     */
    private float ninetiethPct;

    /**
     * 95%响应时间
     */
    private float ninetyfivePct;

    /**
     * 99%响应时间
     */
    private float ninetyninePct;

    /**
     * 每秒请求数
     */
    private float tps;

    /**
     * 吞吐量(接收数据KB/sec)
     */
    private float received;

    /**
     * 吞吐量(发送数据KB/sec)
     */
    private float sent;

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

    public String getApiName() {
        return apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
    }

    public Long getSamples() {
        return samples;
    }

    public void setSamples(Long samples) {
        this.samples = samples;
    }

    public Long getKo() {
        return ko;
    }

    public void setKo(Long ko) {
        this.ko = ko;
    }

    public float getErrorPct() {
        return errorPct;
    }

    public void setErrorPct(float errorPct) {
        this.errorPct = errorPct;
    }

    public void setErrorPct(Long errorPct) {
        this.errorPct = errorPct;
    }

    public float getAverage() {
        return average;
    }

    public void setAverage(float average) {
        this.average = average;
    }

    public float getMin() {
        return min;
    }

    public void setMin(float min) {
        this.min = min;
    }

    public float getMax() {
        return max;
    }

    public void setMax(float max) {
        this.max = max;
    }

    public float getNinetiethPct() {
        return ninetiethPct;
    }

    public void setNinetiethPct(float ninetiethPct) {
        this.ninetiethPct = ninetiethPct;
    }

    public float getNinetyfivePct() {
        return ninetyfivePct;
    }

    public void setNinetyfivePct(float ninetyfivePct) {
        this.ninetyfivePct = ninetyfivePct;
    }

    public float getNinetyninePct() {
        return ninetyninePct;
    }

    public void setNinetyninePct(float ninetyninePct) {
        this.ninetyninePct = ninetyninePct;
    }

    public float getTps() {
        return tps;
    }

    public void setTps(float tps) {
        this.tps = tps;
    }

    public float getReceived() {
        return received;
    }

    public void setReceived(float received) {
        this.received = received;
    }

    public float getSent() {
        return sent;
    }

    public void setSent(float sent) {
        this.sent = sent;
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
        return "StressTestHistoryReportDetailEntity{" +
                "id=" + id +
                ", reportId=" + reportId +
                ", version='" + version + '\'' +
                ", fileId=" + fileId +
                ", fileName='" + fileName + '\'' +
                ", apiName='" + apiName + '\'' +
                ", samples=" + samples +
                ", ko=" + ko +
                ", errorPct=" + errorPct +
                ", average=" + average +
                ", min=" + min +
                ", max=" + max +
                ", ninetiethPct=" + ninetiethPct +
                ", ninetyfivePct=" + ninetyfivePct +
                ", ninetyninePct=" + ninetyninePct +
                ", tps=" + tps +
                ", received=" + received +
                ", sent=" + sent +
                ", addBy='" + addBy + '\'' +
                ", updateBy='" + updateBy + '\'' +
                ", addTime=" + addTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
