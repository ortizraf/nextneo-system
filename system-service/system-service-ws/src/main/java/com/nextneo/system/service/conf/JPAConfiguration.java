package com.nextneo.system.service.conf;

import java.util.Properties;

import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
* @author  Rafael M Ortiz
*/
@EnableTransactionManagement
@EnableJpaRepositories("com.nextneo.system.service.*")
public class JPAConfiguration {
	
    @Resource
    private Environment env;
	
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();

        factoryBean.setJpaVendorAdapter(vendorAdapter);

        factoryBean.setDataSource(dataSource);   

        factoryBean.setJpaProperties(additionalProperties());

        factoryBean.setPackagesToScan("com.nextneo.system.models");

        return factoryBean;
    }
    
    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory emf){
        return new JpaTransactionManager(emf);
    }
    
    @Bean
    public DriverManagerDataSource dataSource(){
    	 DriverManagerDataSource dataSource = new DriverManagerDataSource();
         dataSource.setUsername(env.getRequiredProperty("db.username"));
         dataSource.setPassword(env.getRequiredProperty("db.password"));
         dataSource.setUrl(env.getRequiredProperty("db.url"));
         dataSource.setDriverClassName(env.getRequiredProperty("db.driverClass"));
         return dataSource;
    }
    
    private Properties additionalProperties(){
    	Properties props = new Properties();
        props.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
        props.setProperty("hibernate.show_sql", "true");
        props.setProperty("hibernate.hbm2ddl.auto", "update");
        return props;
    }
}