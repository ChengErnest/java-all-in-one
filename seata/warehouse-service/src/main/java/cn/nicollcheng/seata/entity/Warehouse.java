package cn.nicollcheng.seata.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

/**
 * <b><code>Warehouse</code></b>
 * <p/>
 * Description
 * <p/>
 * <b>Creation Time:</b> 2021/7/12 15:42.
 *
 * @author nicollcheng
 * @since java-all-in-one 2021
 */
@TableName("t_warehouse")
@Data
@Builder
public class Warehouse {
    private Long id;
    private Long quantity;
}
