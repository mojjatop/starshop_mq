package com.enliple.listener;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

import com.enliple.service.ViewServiceImpl;
import com.enliple.vo.DumpData;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by lcroms on 2017. 2. 20..
 */
public class Listener {

    private static final Logger logger = LoggerFactory.getLogger(Listener.class);

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private ViewServiceImpl viewService;

    public final CountDownLatch countDownLatch = new CountDownLatch(1);

    @KafkaListener(topics = "view")
    public void listener(ConsumerRecord<String, String> record){                	
        //logger.info("Listening record data is {}", record);                
        try {        	
            viewService.insertAndUpdateViewCount(objectMapper.readValue(record.value(), DumpData.class));            
        } catch (IOException e) {
            logger.error("Json String to DumpData Convert error...\nOriginalData : {}",record.value(), e);
        }
        countDownLatch.countDown();
    }
}