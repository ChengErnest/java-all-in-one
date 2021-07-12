package cn.nicollcheng.seata.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

/**
 * <b><code>Order</code></b>
 * <p/>
 * Description
 * <p/>
 * <b>Creation Time:</b> 2021/7/12 15:35.
 *
 * @author nicollcheng
 * @since java-all-in-one 2021
 */
@TableName("t_order")
@Data
@Builder
public class Order {
    private Long id;
    private String content;
}
