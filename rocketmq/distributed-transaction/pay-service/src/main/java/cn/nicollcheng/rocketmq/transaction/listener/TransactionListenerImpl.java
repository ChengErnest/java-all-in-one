package cn.nicollcheng.rocketmq.transaction.listener;

import cn.nicollcheng.rocketmq.transaction.entity.PayLog;
import cn.nicollcheng.rocketmq.transaction.service.PayLogService;
import com.alibaba.fastjson.JSON;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Component
@RocketMQTransactionListener(txProducerGroup = "rocket")
public class TransactionListenerImpl implements RocketMQLocalTransactionListener {
    private final PayLogService payLogService;

    public TransactionListenerImpl(PayLogService payLogService) {
        this.payLogService = payLogService;
    }

    /***
     * 发送prepare消息成功后回调该方法用于执行本地事务
     * @param message:回传的消息，利用transactionId即可获取到该消息的唯一Id
     * @param o:调用send方法时传递的参数，当send时候若有额外的参数可以传递到send方法中，这
    里能获取到
     * @return
     */
    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(
            Message message, Object o) {
        try {
            //================本地事务操作开始=======================
            //将o转成PayLog
            String result = new String((byte[]) message.getPayload(), StandardCharsets.UTF_8);
            PayLog payLog = JSON.parseObject(result, PayLog.class);
            payLogService.log(payLog);
            //================本地事务操作结束========================
        } catch (Exception e) {
            //异常,消息回滚
            e.printStackTrace();
            return RocketMQLocalTransactionState.ROLLBACK;
        }
        return RocketMQLocalTransactionState.COMMIT;
    }

    /***
     * 消息回查
     * @param message
     * @return
     */
    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message message) {
        return RocketMQLocalTransactionState.COMMIT;
    }
}