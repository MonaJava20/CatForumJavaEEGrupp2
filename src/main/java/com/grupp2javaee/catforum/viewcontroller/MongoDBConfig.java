package com.grupp2javaee.catforum.viewcontroller;

import com.mongodb.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;

import java.util.Collections;

@Configuration
public class MongoDBConfig extends AbstractMongoClientConfiguration {

    @Override
    protected String getDatabaseName() {
        return "catforum";
    }

    @Override
    protected void configureClientSettings(MongoClientSettings.Builder builder) {

        builder.credential(MongoCredential.createCredential(
                        "admin", "catforum", "password".toCharArray())
                )
                .applyToClusterSettings(settings -> {

                    settings.hosts(Collections.singletonList(new ServerAddress("localhost", 27017)));
                });
    }
}