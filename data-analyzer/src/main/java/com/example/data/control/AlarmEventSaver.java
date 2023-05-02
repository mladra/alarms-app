package com.example.data.control;

import com.example.data.control.alarm.AlarmEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class AlarmEventSaver implements ApplicationListener<AlarmEvent> {

    private final static Logger logger = LoggerFactory.getLogger(AlarmEventSaver.class);

    @Override
    public void onApplicationEvent(AlarmEvent event) {
        logger.info("Received application event: {}", event);
    }
}
