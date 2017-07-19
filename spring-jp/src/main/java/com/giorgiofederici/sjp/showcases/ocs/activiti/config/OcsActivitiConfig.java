package com.giorgiofederici.sjp.showcases.ocs.activiti.config;

import javax.sql.DataSource;

import org.activiti.spring.ProcessEngineFactoryBean;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;

@Configuration
public class OcsActivitiConfig {

	@Autowired
	private DataSource dataSource;

	@Autowired
	private HibernateTransactionManager transactionManager;

	@Bean
	public SpringProcessEngineConfiguration getProcessEngineConfiguration() {
		SpringProcessEngineConfiguration springProcessEngineConfiguration = new SpringProcessEngineConfiguration();
		springProcessEngineConfiguration.setDataSource(this.dataSource);
		springProcessEngineConfiguration.setDatabaseSchemaUpdate("true");
		springProcessEngineConfiguration.setTransactionManager(transactionManager);
		springProcessEngineConfiguration.setJpaHandleTransaction(false);
		springProcessEngineConfiguration.setJpaCloseEntityManager(false);
		springProcessEngineConfiguration.setJobExecutorActivate(false);
		springProcessEngineConfiguration.setDeploymentResources(
				new Resource[] { new ClassPathResource("showcases/ocs/activiti/cartevents.bpmn20.xml") });
		return springProcessEngineConfiguration;
	}

	@Bean(name = "processEngine")
	public ProcessEngineFactoryBean processEngine() {
		ProcessEngineFactoryBean processEngineFactoryBean = new ProcessEngineFactoryBean();
		processEngineFactoryBean.setProcessEngineConfiguration(this.getProcessEngineConfiguration());
		return processEngineFactoryBean;
	}

}
