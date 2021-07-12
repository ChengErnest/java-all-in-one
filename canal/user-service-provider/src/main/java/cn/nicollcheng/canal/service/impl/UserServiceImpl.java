package cn.nicollcheng.canal.service.impl;

import cn.nicollcheng.canal.entity.User;
import cn.nicollcheng.canal.mapper.UserMapper;
import cn.nicollcheng.canal.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author nicollcheng
 * <b>Creation Time:</b> 2021/7/10 22:46.
 */
@Service
@CacheConfig(cacheNames = "user")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    /**
     * 根据Id查询用户信息
     * @param id 用户ID
     * @return {@link User}
     *
     * <p>
     *     cacheNames = "user"：缓存命名空间
     *     key = "#id"：入参id作为缓存的key，使用SpEL表达式
     * </p>
     */
    @Cacheable(key = "#id")
    @Override
    public User findById(Long id) {
        return userMapper.selectById(id);
    }

    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }

    @CachePut(key = "#id")
    @Override
    public User updateCache(Long id) {
        return userMapper.selectById(id);
    }

    @CacheEvict(key = "#id")
    @Override
    public void deleteCacheById(Long id) {
    }
}
