package com.enliple.config;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import java.util.Arrays;
import java.util.List;

/**
 * Created by lcroms on 2017. 2. 22..
 * MongoDB 설정
 */
@Configuration
public class MongoConfig {
	
	@Value("${spring.data.mongodb.host}")
    private String host;
	@Value("${spring.data.mongodb.database}")
    private String dbName;
	@Value("${spring.data.mongodb.port}")
    private int port;
	@Value("${spring.data.mongodb.username}")
    private String userName;
	@Value("${spring.data.mongodb.password}")
    private String passWord;

    private MongoDbFactory mongoDbFactory() {
        return new SimpleMongoDbFactory(new MongoClient(getServerAddress(), getUserCredentials(), getMongoClientOption()), dbName);
    }

    private MongoClientOptions getMongoClientOption() {
        return MongoClientOptions.builder()
                .connectionsPerHost(8)
                .threadsAllowedToBlockForConnectionMultiplier(4)
                .connectTimeout(1500).maxWaitTime(1500)
                .socketKeepAlive(true)
                .socketTimeout(5000)
                .build();
    }

    private List<MongoCredential> getUserCredentials() {
        return Arrays.asList(MongoCredential.createCredential(userName, dbName, passWord.toCharArray()));
    }

    private ServerAddress getServerAddress() {
        return new ServerAddress(host, port);
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoDbFactory());
    }
}
