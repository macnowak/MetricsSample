package pl.net.nowak.metrics.config;

import javax.sql.DataSource;
import liquibase.integration.spring.SpringLiquibase;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class DataBaseConfig {

	@Bean
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
	}
}
