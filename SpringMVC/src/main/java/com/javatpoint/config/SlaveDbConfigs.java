package com.javatpoint.config;

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
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by anand on 20/4/17.
 */
@Configuration
@EnableJpaRepositories(basePackages = "com.javatpoint.slaveRepository" ,
        entityManagerFactoryRef = "emFactoryForSlaveDb",
        transactionManagerRef = "transactionManagerSlaveDb")
@ComponentScan(basePackages = "com.javatpoint.slaveRepository")
@EnableTransactionManagement
@PropertySource(value = "classpath:common-config.properties")
public class SlaveDbConfigs {

    private static final Logger LOGGER = LoggerFactory.getLogger(SlaveDbConfigs.class);

    @Autowired
    Environment environment ;

    static{
        LOGGER.info("loading slave db beans");
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
    public DataSource dataSourceForSlaveDb() {
        String ds = environment.getProperty("jdbc.slave.ds");
        String url = environment.getProperty("jdbc.slave.url");
        String userName = environment.getProperty("jdbc.slave.username");
        String password = environment.getProperty("jdbc.slave.password");
        return makeDataSource(ds,url,userName,password);
    }


    @Bean
    public EntityManagerFactory emFactoryForSlaveDb() {
        JpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSourceForSlaveDb());
        entityManagerFactoryBean.setPackagesToScan("com.javatpoint.entity");
        entityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter);
        entityManagerFactoryBean.setJpaProperties(getJpaProperties());
        return entityManagerFactoryBean.getObject();
    }

    @Bean
    public JpaTransactionManager transactionManagerSlaveDb() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emFactoryForSlaveDb());
        return transactionManager;
    }

}
