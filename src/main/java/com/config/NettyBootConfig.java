package com.config;

import com.baozi.business.BusinessExecutor;
import com.baozi.constructor.ServerConstructor;
import com.executor.AcceptMessageExecutor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 配置服务启动
 * @Author: baozi
 * @Create: 2018-09-25 14:52
 */
@Component
public class NettyBootConfig {

    @Resource(type = AcceptMessageExecutor.class)
    private BusinessExecutor acceptExecutor;

    @Value("${netty.port}")
    private int port;

    @Value("#{'${netty.timeSlice}'.split(',')}")
    private long[] timeSlice;

    @PostConstruct
    private void init(){
        Map<String, BusinessExecutor> executorMap = new HashMap<>(2);
        executorMap.put("accept", acceptExecutor);
        ServerConstructor.setExecutorMap(executorMap);
        ServerConstructor.setPort(port);
        ServerConstructor.setHeartbeatType("ping");
        ServerConstructor.setHeartbeatReply("pong");
        ServerConstructor.setResendTimeWheel(timeSlice);
        ServerConstructor.setServerHeartbeat(false);
    }

    public void start(){
        ServerConstructor.start();
    }
}
