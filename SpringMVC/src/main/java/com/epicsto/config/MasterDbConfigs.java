package com.epicsto.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by anand on 20/4/17.
 */
@Configuration
@EnableJpaRepositories(basePackages = "com.epicsto.repository",
        entityManagerFactoryRef = "emFactoryForMasterDb",
        transactionManagerRef = "transactionManagerMasterDb")
@ComponentScan(basePackages = "com.epicsto.repository")
@EnableTransactionManagement
@PropertySource(value = "classpath:common-config.properties")
public class MasterDbConfigs {

    private static final Logger LOGGER = LoggerFactory.getLogger(MasterDbConfigs.class);

    @Autowired
    Environment environment ;


    static{
        LOGGER.info("loading master db beans");
    }
    private Properties getJpaProperties(){
        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect","org.hibernate.dialect.MySQL5Dialect");
        properties.setProperty("showSql", "false");
        return properties ;
    }

    private DataSource makeDataSource(String ds, String url, String userName, String password){
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(environment.getProperty("jdbc.driverClassName"));
        dataSource.setUrl(url);
        dataSource.setUsername(userName);
        dataSource.setPassword(password);
        dataSource.setMaxIdle(NumberUtils.toInt(environment.getProperty("jdbc.max.idle", "5")));
        dataSource.setDefaultAutoCommit(Boolean.valueOf(environment.getProperty("jdbc.defaultAutoCommit")));
        dataSource.setMinIdle(NumberUtils.toInt(environment.getProperty("jdbc.minIdle")));
        dataSource.setInitialSize(NumberUtils.toInt(environment.getProperty("jdbc.initialSize")));
        dataSource.setValidationQuery(environment.getProperty("jdbc.validationQuery"));
        dataSource.setTestOnBorrow(Boolean.valueOf(environment.getProperty("jdbc.testOnBorrow")));
        return dataSource;
    }


    //master db beans


    @Bean
    public JpaTransactionManager transactionManagerMasterDb() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emFactoryForMasterDb().getObject());
        return transactionManager;
    }


    @Bean
    public LocalContainerEntityManagerFactoryBean emFactoryForMasterDb() {
        JpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSourceForMasterDb());
        entityManagerFactoryBean.setPackagesToScan("com.epicsto.entity");
        entityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter);
        entityManagerFactoryBean.setJpaProperties(getJpaProperties());
        return entityManagerFactoryBean;
    }

    @Bean
    public DataSource dataSourceForMasterDb() {
        String ds = environment.getProperty("jdbc.ds");
        String url = environment.getProperty("jdbc.url");
        String userName = environment.getProperty("jdbc.username");
        String password = environment.getProperty("jdbc.password");
        return makeDataSource(ds,url,userName,password);
    }

}
