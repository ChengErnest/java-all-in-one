package cn.nicollcheng.canal.controller;

import cn.nicollcheng.canal.entity.User;
import cn.nicollcheng.canal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author nicollcheng
 * <b>Creation Time:</b> 2021/7/10 22:47.
 */
@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public User findById(@PathVariable("id") Long id) {
        return userService.findById(id);
    }

    @GetMapping
    public List<User> findAll() {
        return userService.findAll();
    }


    @GetMapping("/cache")
    public User cache(@RequestParam("id") Long id) {
        return userService.findById(id);
    }

    /**
     * 更新缓存
     *
     * @param id 用户id
     * @return {@link User}
     */
    public User updateCache(Long id) {
        return userService.updateCache(id);
    }

    /**
     * 根据用户id清理用户缓存信息
     *
     * @param id 用户id
     */
    @DeleteMapping("/{id}")
    public String deleteCacheById(@PathVariable("id") Long id) {
        userService.deleteCacheById(id);
        return "success";
    }
}
