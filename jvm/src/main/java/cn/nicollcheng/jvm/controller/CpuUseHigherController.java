package cn.nicollcheng.jvm.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author nicollcheng
 * <b>Creation Time:</b> 2021/7/23 14:07.
 */
@RestController
public class CpuUseHigherController {
    private volatile boolean start = true;

    @GetMapping("/startFullCpuUse")
    public void startFullCpuUse(){
        start = true;
        while (start){}
    }
    @GetMapping("/stopFullCpuUse")
    public void stopFullCpuUse(){
        start = false;
    }
}
