package com.dongverine.config;

import java.sql.SQLException;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = { "com.dongverine.db1.repository", "com.dongverine.db1.domain" },
		  transactionManagerRef   = "DB1_TransactionManager"
		, entityManagerFactoryRef = "DB1_EntityManagerFactory")
public class Db1HikariCpDataSource {

	@Bean(name = "DB1_DataSource")
	@ConfigurationProperties(prefix = "db1.datasource")
	public DataSource backofficeDataSource() throws SQLException {
		HikariDataSource hikariDataSource = new HikariDataSource();
		return hikariDataSource;
	}

	@Bean(name = "DB1_EntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean Db1EntityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("DB1_DataSource") DataSource dataSource) {
		return builder.dataSource(dataSource)
				.packages("com.dongverine.db1.repository", "com.dongverine.db1.domain").build();

	}

	@Bean(name = "DB1_TransactionManager")
	public PlatformTransactionManager Db1TransactionManager(
			@Qualifier("DB1_EntityManagerFactory") EntityManagerFactory entityManagerFactory) {

		return new JpaTransactionManager(entityManagerFactory);

	}

}
