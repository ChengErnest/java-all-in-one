package cn.nicollcheng.rabbitmq.controller;

import cn.nicollcheng.rabbitmq.service.RabbitMQService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/rabbitmq")
public class RabbitMQController {
    @Resource
    private RabbitMQService rabbitMQService;
    /**
     * 发送消息
     */
    @GetMapping("/sendMsg")
    public String sendMsg(@RequestParam(name = "msg") String msg) throws Exception {
        return rabbitMQService.sendMsg(msg);
    }
}