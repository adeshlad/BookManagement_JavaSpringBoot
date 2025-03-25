package com.demo.bookmanagement.config;

import com.azure.cosmos.CosmosClientBuilder;
import com.azure.spring.data.cosmos.config.AbstractCosmosConfiguration;
import com.azure.spring.data.cosmos.repository.config.EnableCosmosRepositories;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCosmosRepositories(basePackages = "com.demo.bookmanagement.book")
public class CosmosDbConfig extends AbstractCosmosConfiguration {

    @Bean
    public CosmosClientBuilder getCosmosClientBuilder() {
        return new CosmosClientBuilder()
                .endpoint("URI")
                .key("KEY");
    }

    @Override
    protected String getDatabaseName() {
        return "Learning";
    }
}