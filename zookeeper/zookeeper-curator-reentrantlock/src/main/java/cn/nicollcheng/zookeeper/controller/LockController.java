package cn.nicollcheng.zookeeper.controller;

import cn.nicollcheng.zookeeper.lock.DistributedLock;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <b><code>LockController</code></b>
 * <p/>
 * Description
 * <p/>
 * <b>Creation Time:</b> 2021/7/7 17:43.
 *
 * @author nicollcheng
 * @since zookeeper-curator 2021
 */
@RestController
public class LockController {

    @GetMapping("/lock")
    @DistributedLock("#name")
    public String lock(String name){
        return "ok lock";
    }

    @GetMapping("/save")
    public String save(String name){
        return "ok save";
    }

    @GetMapping("/update")
    public String update(String name){
        return "ok update";
    }

    @GetMapping("/delete")
    public String delete(String name){
        return "ok delete";
    }

    @GetMapping("/select")
    public String select(String name){
        return "ok select";
    }

}
