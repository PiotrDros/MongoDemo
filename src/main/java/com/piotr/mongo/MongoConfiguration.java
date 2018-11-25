package com.piotr.mongo;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.CustomConversions;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class MongoConfiguration extends AbstractMongoConfiguration {
    @Value("${spring.data.mongodb.database:test}")
    private String database;

    @Value("${spring.data.mongodb.host:localhost}:${spring.data.mongodb.port:27017}")
    private String host;

    @Override
    protected String getDatabaseName() {
        return database;
    }

    @Override
    public MongoTemplate mongoTemplate() throws Exception {
        return super.mongoTemplate();
    }


//    @Bean
//    @Primary
//    public MongoTemplate mongoTemplate(MongoDbFactory mongoDbFactory, MappingMongoConverter converter, CustomConversions customConversions) {
//
//
//        converter.setCustomConversions(customConversions);
//
//        return new MongoTemplate(mongoDbFactory, converter);
//    }


    @Override
    public MongoClient mongoClient() {
        return new MongoClient(host);
    }
    @Bean
    @Override
    public CustomConversions customConversions() {
        List<Converter<?, ?>> converterList = new ArrayList<Converter<?, ?>>();
        //Jsr310Converters.getConvertersToRegister()
        converterList.add(new ZonedDateTimeReadConverter());
        converterList.add(new ZonedDateTimeWriteConverter());
        return new CustomConversions(CustomConversions.StoreConversions.NONE, converterList);
    }
}