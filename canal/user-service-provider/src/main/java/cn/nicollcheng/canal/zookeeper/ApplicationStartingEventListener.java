package cn.nicollcheng.canal.zookeeper;

import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationListener;

public class ApplicationStartingEventListener implements ApplicationListener<ApplicationStartingEvent> {
 
    @Override
    public void onApplicationEvent(ApplicationStartingEvent event) {

    }
 
}