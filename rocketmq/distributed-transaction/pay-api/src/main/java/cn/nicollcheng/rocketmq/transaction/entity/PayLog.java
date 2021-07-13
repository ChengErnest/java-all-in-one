package cn.nicollcheng.rocketmq.transaction.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
//MyBatisPlus表映射注解
@TableName(value = "t_pay_log")
public class PayLog {
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;
    private Integer status;
    private String content;
    private String payId;
    private Date createTime;
}
