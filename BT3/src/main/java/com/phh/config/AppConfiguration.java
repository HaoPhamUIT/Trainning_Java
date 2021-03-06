package com.phh.config;

/**
 * Created by hao-pham on 6/12/17.
 */

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan({"com.phh.*"})
@EnableTransactionManagement

// Load to Environment.
@PropertySources({@PropertySource("classpath:datasource-cfg.properties")})

public class AppConfiguration {

    // Lưu trữ các giá thuộc tính load bởi @PropertySource.
    @Autowired
    private Environment env;


    // Spring BEAN
    @Bean(name = "dataSource")
    public DataSource getDataSource() throws InterruptedException {
        BasicDataSource dataSource = new BasicDataSource();
        // See: datasouce-cfg.properties
        dataSource.setDriverClassName(env.getProperty("ds.database-driver"));
        dataSource.setUrl(env.getProperty("ds.url"));
        dataSource.setUsername(env.getProperty("ds.username"));
        dataSource.setPassword(env.getProperty("ds.password"));
        System.out.println("## getDataSource: " + dataSource);

        return dataSource;
    }

}