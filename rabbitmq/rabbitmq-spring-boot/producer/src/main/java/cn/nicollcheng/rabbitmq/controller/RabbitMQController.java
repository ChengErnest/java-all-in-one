package cn.nicollcheng.rabbitmq.controller;

import cn.nicollcheng.rabbitmq.service.RabbitMQService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.lang.reflect.Type;
import java.util.Map;

@RestController
@RequestMapping("/rabbitmq")
public class RabbitMQController {
    @Resource
    private RabbitMQService rabbitMQService;
    /**
     * 发送消息
     */
    @GetMapping("/direct")
    public String sendDirectMsg(@RequestParam(name = "msg") String msg) throws Exception {
        return rabbitMQService.sendDirectMsg(msg);
    }
    /**
     * 发送消息
     */
    @GetMapping("/fanout")
    public String sendFanoutMsg(@RequestParam(name = "msg") String msg) throws Exception {
        return rabbitMQService.sendFanoutMsg(msg);
    }

    /**
     * 通配符交换机发送消息
     */
    @PostMapping("/topic")
    public String sendTopicMsg(@RequestParam(name = "msg") String msg, @RequestParam(name = "routingKey") String routingKey) throws Exception {
        return rabbitMQService.sendTopicMsg(msg, routingKey);
    }

    @PostMapping("/headersSend")
    public String headersSend(@RequestParam(name = "msg") String msg,
                              @RequestParam(name = "headers") String headers) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = mapper.readValue(headers, new TypeReference<Map<String, Object>>() {
            @Override
            public Type getType() {
                return super.getType();
            }
        });
        return rabbitMQService.sendHeadersMsg(msg, map);
    }
}