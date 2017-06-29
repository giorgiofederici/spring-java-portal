package com.giorgiofederici.sjp.config.hibernate;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@PropertySource(value = { "classpath:/hibernate/hibernate.properties" })
public class HibernateConfig {

	@Autowired
	private Environment env;

	@Bean
	public DataSource getDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getProperty("datasource.driver"));
		dataSource.setUrl(env.getProperty("datasource.url"));
		dataSource.setUsername(env.getProperty("datasource.username"));
		dataSource.setPassword(env.getProperty("datasource.password"));
		return dataSource;
	}

	@Bean
	public LocalSessionFactoryBean getSessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(getDataSource());
		sessionFactory.setPackagesToScan(new String[] { "com.giorgiofederici.sjp.domain.entity" });
		sessionFactory.setHibernateProperties(getHibernateProperties());
		return sessionFactory;

	}

	private Properties getHibernateProperties() {
		Properties properties = new Properties();
		properties.put(AvailableSettings.DIALECT, env.getProperty("hibernate.dialect"));
		properties.put(AvailableSettings.SHOW_SQL, env.getProperty("hibernate.show_sql"));
		properties.put(AvailableSettings.FORMAT_SQL, env.getProperty("hibernate.format_sql"));
		properties.put(AvailableSettings.STATEMENT_BATCH_SIZE, env.getProperty("hibernate.batch.size"));
		properties.put(AvailableSettings.CURRENT_SESSION_CONTEXT_CLASS,
				env.getProperty("hibernate.current.session.context.class"));

		// Comment out for HSQL testing DB
		// properties.put(AvailableSettings.HBM2DDL_AUTO,
		// env.getProperty("hibernate.hbm2ddl.auto"));

		return properties;
	}

	@Bean
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(sessionFactory);
		return transactionManager;
	}
}
