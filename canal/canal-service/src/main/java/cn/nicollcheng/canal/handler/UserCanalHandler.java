package cn.nicollcheng.canal.handler;

import cn.nicollcheng.canal.entity.User;
import cn.nicollcheng.canal.rest.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.javatool.canal.client.annotation.CanalTable;
import top.javatool.canal.client.handler.EntryHandler;

/**
 * @author nicollcheng
 * <b>Creation Time:</b> 2021/7/11 12:10.
 */
@CanalTable(value = "t_user")
@Component
public class UserCanalHandler implements EntryHandler<User> {

    private final UserService userService;

    @Autowired
    public UserCanalHandler(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void insert(User user) {
        userService.updateCache(user.getId());
    }

    @Override
    public void update(User before, User after) {
        userService.updateCache(after.getId());
    }

    @Override
    public void delete(User user) {
        userService.deleteCacheById(user.getId());
    }
}
