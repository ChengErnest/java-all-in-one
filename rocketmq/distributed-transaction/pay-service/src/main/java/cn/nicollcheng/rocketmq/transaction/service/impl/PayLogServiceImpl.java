package cn.nicollcheng.rocketmq.transaction.service.impl;

import cn.nicollcheng.rocketmq.transaction.entity.PayLog;
import cn.nicollcheng.rocketmq.transaction.mapper.PayLogMapper;
import cn.nicollcheng.rocketmq.transaction.service.PayLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PayLogServiceImpl extends ServiceImpl<PayLogMapper, PayLog>
        implements PayLogService {
    private final PayLogMapper payLogMapper;

    public PayLogServiceImpl(PayLogMapper payLogMapper) {
        this.payLogMapper = payLogMapper;
    }

    /***
     * 记录日志
     * @param payLog
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void log(PayLog payLog) {
        //本地操作
        int count = payLogMapper.insert(payLog);
    }
}
