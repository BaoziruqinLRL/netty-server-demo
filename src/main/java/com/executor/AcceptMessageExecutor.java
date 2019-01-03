package com.executor;

import com.alibaba.fastjson.JSON;
import com.baozi.business.BusinessExecutor;
import com.baozi.data.CacheIdParam;
import com.baozi.data.TransferData;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;

/**
 * @Description: 接收消息执行器
 * @Author: baozi
 * @Create: 2019-01-03 12:21
 */
@Component
public class AcceptMessageExecutor implements BusinessExecutor {

    @Override
    public CacheIdParam exec(TransferData transferData) {
        var ack = transferData.getAck();
        var content = transferData.getContent();
        var cacheParam = new CacheIdParam(content,content,System.currentTimeMillis());
        cacheParam.setAckMessage(ack);
        System.out.print(MessageFormat.format("i receive message {0}", JSON.toJSON(transferData)));
        return cacheParam;
    }
}
