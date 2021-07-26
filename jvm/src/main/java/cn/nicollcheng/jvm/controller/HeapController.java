package cn.nicollcheng.jvm.controller;

import cn.nicollcheng.jvm.entity.Person;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class HeapController {
    List<Person> list= new ArrayList<>();
    @GetMapping("/heap")
    public String heap() {
        while(true){
            list.add(new Person());
        }
    }
}
