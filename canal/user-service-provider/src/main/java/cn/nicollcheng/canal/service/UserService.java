package cn.nicollcheng.canal.service;

import cn.nicollcheng.canal.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author nicollcheng
 * <b>Creation Time:</b> 2021/7/10 22:45.
 */
public interface UserService extends IService<User> {
    User findById(Long id);
    List<User> findAll();

    /**
     * 更新缓存
     * @param id 用户id
     * @return {@link User}
     */
    User updateCache(Long id);

    /**
     * 根据用户id清理用户缓存信息
     * @param id 用户id
     */
    void deleteCacheById(Long id);
}
