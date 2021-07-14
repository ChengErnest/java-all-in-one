package cn.nicollcheng.rocketmq.transaction.controller;

import cn.nicollcheng.rocketmq.transaction.entity.PayLog;
import cn.nicollcheng.rocketmq.transaction.service.PayLogService;
import com.alibaba.fastjson.JSON;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author nicollcheng
 * <b>Creation Time:</b> 2021/7/13 22:30.
 */
@RestController
@RequestMapping("/pay")
public class WechatPayController {
    private final PayLogService payLogService;
    private final RocketMQTemplate rocketMQTemplate;

    public WechatPayController(PayLogService payLogService, RocketMQTemplate rocketMQTemplate) {
        this.payLogService = payLogService;
        this.rocketMQTemplate = rocketMQTemplate;
    }

    /***
     * 记录支付结果
     * 执行事务消息发送
     */
    @GetMapping(value = "/result")
    public ResponseEntity<String> payLog() {
        //记录日志
        PayLog payLog = new PayLog("1", 1, "test", "No001", new Date());
        Message<String> message =
                MessageBuilder.withPayload(JSON.toJSONString(payLog)).build();
        TransactionSendResult transactionSendResult = rocketMQTemplate.sendMessageInTransaction("rocket", "log", message, null);
        System.out.println(JSON.toJSONString(transactionSendResult));
        return ResponseEntity.ok("success");
    }
}
