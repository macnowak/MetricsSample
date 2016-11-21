package pl.net.nowak.metrics.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class DataBaseConfig {

/*	@Bean
	@ConfigurationProperties(prefix = "datasource.main")
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}


	@Bean
	@ConfigurationProperties(prefix = "datasource.main")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
		emf.setPackagesToScan("pl.net.nowak.metrics");
		emf.setPersistenceUnitName("main");
		emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		emf.setDataSource(dataSource());
		return emf;

	}

	@Bean
	public PlatformTransactionManager transactionManager() {
		JpaTransactionManager jpaTransactionManager = new JpaTransactionManager(entityManagerFactory().getObject());
		jpaTransactionManager.setDataSource(dataSource());
		return jpaTransactionManager;
	}

	@Bean
	public SpringLiquibase liquibase() {
		SpringLiquibase liquibase = new SpringLiquibase();
		liquibase.setDataSource(dataSource());
		liquibase.setChangeLog("classpath:liquibase/changelog-master.xml");
		liquibase.setDropFirst(false);
		return liquibase;
	}*/
}
