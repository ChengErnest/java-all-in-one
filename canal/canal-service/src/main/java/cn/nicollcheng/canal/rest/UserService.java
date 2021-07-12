package cn.nicollcheng.canal.rest;

import cn.nicollcheng.canal.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author nicollcheng
 * <b>Creation Time:</b> 2021/7/12 21:12.
 */
@Component
@RequestMapping("/users")
@FeignClient("user-service-provider")
public interface UserService {
    @GetMapping
    List<User> findAll();

    @DeleteMapping("/{id}")
    String deleteCacheById(@PathVariable("id") Long id);

    @GetMapping("/cache")
    User updateCache(@RequestParam("id") Long id);
}
