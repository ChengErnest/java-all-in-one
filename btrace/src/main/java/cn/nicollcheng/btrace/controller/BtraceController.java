package cn.nicollcheng.btrace.controller;

/**
 * <b><code>BtraceController</code></b>
 * <p/>
 * Description
 * <p/>
 * <b>Creation Time:</b> 2021/7/2 10:23.
 *
 * @author nicollcheng
 * @since java-all-in-one 2021
 */

import cn.nicollcheng.btrace.entity.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/btrace")
public class BtraceController {

    @RequestMapping("/arg1")
    public String arg1(@RequestParam("name") String name) {
        return "hello: " + name;
    }

    @RequestMapping("/constructor")
    public User constructor(@RequestBody User user) {
        return user;
    }

    @RequestMapping("/same1")
    public String same(@RequestParam("name") String name) {
        return "hello: " + name;
    }

    @RequestMapping("/same2")
    public User same(@RequestParam("id") int id,
                     @RequestParam("name") String name) {
        return new User(id, name);
    }
    @RequestMapping("/exception")
    public String exception() {
        try {
            System.out.println("start...");
            System.out.println(1 / 0);
            System.out.println("end...");
        } catch (Exception e) {

        }

        return "success";
    }

    @RequestMapping("/arg2")
    public User arg2(@RequestBody User user) {
        return user;
    }
}
