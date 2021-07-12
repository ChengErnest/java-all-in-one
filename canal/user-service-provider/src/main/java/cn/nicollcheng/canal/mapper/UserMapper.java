package cn.nicollcheng.canal.mapper;

import cn.nicollcheng.canal.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @author nicollcheng
 * <b>Creation Time:</b> 2021/7/10 22:44.
 */
public interface UserMapper extends BaseMapper<User> {
    List<User> findAll();
}
