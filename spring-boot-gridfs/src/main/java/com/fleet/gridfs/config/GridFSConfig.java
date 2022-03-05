package com.fleet.gridfs.config;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;

import javax.annotation.Resource;

@Configuration
public class GridFSConfig {

    @Resource
    private MongoDbFactory mongoDbFactory;

    @Bean
    public GridFSBucket gridFsBucket() {
        MongoDatabase db = mongoDbFactory.getDb();
        return GridFSBuckets.create(db);
    }
}
