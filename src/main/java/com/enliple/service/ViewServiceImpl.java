package com.enliple.service;

import com.enliple.listener.Listener;
import com.enliple.vo.DumpData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lcroms on 2017. 2. 23..
 */
@Service
public class ViewServiceImpl {    

	private static final Logger logger = LoggerFactory.getLogger(ViewServiceImpl.class);
	
    private final String COLLECTION_NAME = "viewCollection";

    @Autowired
    private MongoTemplate mongoTemplate;
    
    String changeDate = "";

    public void insertAndUpdateViewCount(DumpData data) {
    	
    	Calendar calender = Calendar.getInstance();
    	String nowday = new SimpleDateFormat("yyyyMMdd").format(calender.getTime());
    	
		if(!data.getStatDate().substring(0,8).equals(nowday)){
			logger.info("날짜 변경 : {}" ,data.getStatDate().substring(0,8));
			data.setStatDate(data.getSendDate());
		}    	
        Long _id = Long.parseLong(data.getSendDate());
          
        Update update = new Update(); 
      
        Query secondQuery = new Query().addCriteria(Criteria.where("_id").is(_id));
        
        if(data.getSendDate().equals(changeDate)){        	
        	update.push("value", data.convertMap());                
            mongoTemplate.updateFirst(secondQuery, update, COLLECTION_NAME);
            changeDate = data.getSendDate(); 
            logger.info("mongo db changeDate update finish!");
        }else{
        	if(mongoTemplate.exists(secondQuery, COLLECTION_NAME)){        	
                update.push("value", data.convertMap());                
                mongoTemplate.updateFirst(secondQuery, update, COLLECTION_NAME);
                changeDate = data.getSendDate(); 
                logger.info("mongo db update finish!");
            }else{        	 
                Map<String, Object> obj = new HashMap<>();
                obj.put("_id", _id);                
                List<Map<String, Object>> values = new ArrayList<>();
                values.add(data.convertMap());
                obj.put("value",values);
                mongoTemplate.save(obj, COLLECTION_NAME);
                changeDate = data.getSendDate(); 
                logger.info("mongo db save finish!");
            }
        }    	
    }
}
