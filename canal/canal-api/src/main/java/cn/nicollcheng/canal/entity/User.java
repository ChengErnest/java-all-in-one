package cn.nicollcheng.canal.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author nicollcheng
 * <b>Creation Time:</b> 2021/7/10 22:38.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
// MyBatisPlus表映射注解
@TableName(value = "t_user")
public class User {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    private Long phone;
    private String password;
}
