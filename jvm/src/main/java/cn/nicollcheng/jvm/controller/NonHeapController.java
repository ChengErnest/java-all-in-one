package cn.nicollcheng.jvm.controller;

import cn.nicollcheng.jvm.utils.MetaspaceUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class NonHeapController {

    List<Class<?>> list=new ArrayList<Class<?>>();
    @GetMapping("/nonheap")
    public String heap() throws Exception{
        while(true){
            list.addAll(MetaspaceUtil.createClasses());
            Thread.sleep(5);
        }
    }
}
