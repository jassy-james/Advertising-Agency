package com.application.config;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration //данный класс является java-конфигурацией
@ComponentScan(basePackages = "com.application")
@EnableTransactionManagement
public class HiberConfig extends WebMvcConfigurerAdapter {
    @Bean
    public LocalSessionFactoryBean sessionFactoryBean() {
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource());
        sessionFactoryBean.setPackagesToScan("com.application.domain.model");
        sessionFactoryBean.setHibernateProperties(hibernateProperties());
        return sessionFactoryBean;
    }

        @Bean
        public DataSource dataSource() {
            DriverManagerDataSource dataSource = new DriverManagerDataSource();
            dataSource.setDriverClassName("org.h2.Driver");
            dataSource.setUrl("jdbc:h2:~/agency");
            dataSource.setUsername("vitaliy");
            dataSource.setPassword("");
            return dataSource;
        }

        private Properties hibernateProperties() {
            Properties properties = new Properties();
            properties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
            properties.put("hibernate.show_sql", "true");
            properties.put("hbm2ddl.auto", "update");
            properties.put("hibernate.format_sql", "true");
            return properties;
        }

        @Bean
        @Autowired
        public HibernateTransactionManager transactionManager (SessionFactory sessionFactory) {
            HibernateTransactionManager hibernateTransactionManager = new HibernateTransactionManager();
            hibernateTransactionManager.setSessionFactory(sessionFactory);
            return hibernateTransactionManager;
        }
}
