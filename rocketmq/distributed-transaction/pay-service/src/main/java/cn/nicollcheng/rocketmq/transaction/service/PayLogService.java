package cn.nicollcheng.rocketmq.transaction.service;

import cn.nicollcheng.rocketmq.transaction.entity.PayLog;
import com.baomidou.mybatisplus.extension.service.IService;

public interface PayLogService extends IService<PayLog> {
    void log(PayLog payLog);
}