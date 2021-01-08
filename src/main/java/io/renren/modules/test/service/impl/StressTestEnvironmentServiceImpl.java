package io.renren.modules.test.service.impl;

import io.renren.modules.test.dao.StressTestEnvironmentDao;
import io.renren.modules.test.dao.StressTestMonitorDao;
import io.renren.modules.test.dao.StressTestReportsDao;
import io.renren.modules.test.entity.StressTestEnvironmentEntity;
import io.renren.modules.test.entity.StressTestMonitorEntity;
import io.renren.modules.test.service.StressTestEnvironmentService;
import io.renren.modules.test.utils.StressTestUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("stressTestEnvironmentService")
public class StressTestEnvironmentServiceImpl implements StressTestEnvironmentService {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private StressTestEnvironmentDao stressTestEnvironmentDao;

    @Autowired
    private StressTestMonitorDao stressTestMonitorDao;

    @Autowired
    private StressTestReportsDao stressTestReportsDao;

    @Autowired
    private StressTestUtils stressTestUtils;

    @Override
    public List<StressTestEnvironmentEntity> queryList(Map<String, Object> map) {
        return stressTestEnvironmentDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return stressTestEnvironmentDao.queryTotal(map);
    }

    @Override
    public String startUp(List<StressTestEnvironmentEntity> environmentList) {
        // 创建Httpclient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();
        for (StressTestEnvironmentEntity environment : environmentList) {
            // 创建http GET请求
            HttpGet httpGet = new HttpGet(getRequestUrl(environment));
            // 设置超时时间
            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(2000).setConnectTimeout(2000).build();
            httpGet.setConfig(requestConfig);
            CloseableHttpResponse response = null;
            try {
                response = httpclient.execute(httpGet);
                if (response.getStatusLine().getStatusCode() == 200){
                    String content = EntityUtils.toString(response.getEntity(), "UTF-8");
                    logger.info("response msg: " + content);
                    environment.setUpdateTime(new Date());
                    environment.setReportPath(StringUtils.substringBetween(content, ": ", "\""));
                    // 更新资源监控报告路径和更新时间
                    updateReportPath(environment);
                }
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            }catch (HttpHostConnectException e) {
                logger.error("请求连接超时！请查看服务器easyNmon服务是否启动！");
                e.printStackTrace();
            }catch (IOException e){
                e.printStackTrace();
            }finally {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "";
    }

    @Override
    public String saveMonitor(List<StressTestEnvironmentEntity> environmentList) {
        StressTestMonitorEntity stressTestMonitorEntity = new StressTestMonitorEntity();
        Long report_id = stressTestReportsDao.queryMaxId();
        for (StressTestEnvironmentEntity environment : environmentList){
            stressTestMonitorEntity.setReportId(report_id);
            stressTestMonitorEntity.setAppName(environment.getAppName());
            stressTestMonitorEntity.setAppIp(environment.getIp());
            stressTestMonitorEntity.setStatus("init");
            stressTestMonitorEntity.setAddTime(new Date());
            stressTestMonitorEntity.setUpdateTime(new Date());
            stressTestMonitorEntity.setMonitorPath("http://" + environment.getIp() + ":" + environment.getPort() + "/report/" + environment.getReportPath());
            if (stressTestMonitorDao.queryTotal(stressTestMonitorEntity) == 0) {
                stressTestMonitorDao.save(stressTestMonitorEntity);
            }
        }
        return "";
    }

    @Override
    public void updateReportPath(StressTestEnvironmentEntity environment) {
        Map<String, Object> map = new HashMap<>();
        map.put("reportPath", environment.getReportPath());
        map.put("updateTime", environment.getUpdateTime());
        map.put("ip", environment.getIp());
        stressTestEnvironmentDao.updateReportPath(map);
    }

    private String getRequestUrl(StressTestEnvironmentEntity environment){
        return "http://" + environment.getIp() + ":" + environment.getPort() + "/start?n="+ environment.getIp() + "&t=45&f=10";
    }

}
