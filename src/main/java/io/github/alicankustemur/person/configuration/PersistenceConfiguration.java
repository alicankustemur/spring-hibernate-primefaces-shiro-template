package io.github.alicankustemur.person.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import io.github.alicankustemur.person.base.Common;
import io.github.alicankustemur.person.repository.base.BaseRepositoryFactoryBean;
import io.github.alicankustemur.person.service.EnvironmentService;

@Configuration
@EnableTransactionManagement
@ComponentScan({ Common.BASE_PACKAGE_PATH })
@EnableJpaRepositories(basePackages = {Common.REPOSITORY_PACKAGE_PATH}, repositoryFactoryBeanClass = BaseRepositoryFactoryBean.class) 
public class PersistenceConfiguration {

	@Autowired
	private EnvironmentService environmentService;

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setDataSource(dataSource());
		entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
		entityManagerFactoryBean.setPackagesToScan(Common.ENTITY_PACKAGE_PATH);
		entityManagerFactoryBean.setJpaProperties(hibernateProperties());
		return entityManagerFactoryBean;
	}

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(environmentService.getProperty("jdbc.driverClassName"));
		dataSource.setUrl(environmentService.getProperty("jdbc.url"));
		dataSource.setUsername(environmentService.getProperty("jdbc.username"));
		dataSource.setPassword(environmentService.getProperty("jdbc.password"));
		return dataSource;
	}

	private Properties hibernateProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.dialect", environmentService.getProperty("hibernate.dialect"));
		properties.put("hibernate.show_sql", environmentService.getProperty("hibernate.show_sql"));
		properties.put("hibernate.format_sql", environmentService.getProperty("hibernate.format_sql"));
		return properties;
	}

	@Bean
	@Autowired
	public JpaTransactionManager transactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
		return transactionManager;
	}

}
