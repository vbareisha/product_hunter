package com.bareisha.producthunter.core.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.beans.PropertyVetoException;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.bareisha.producthunter.repository.base")
public class RepositoryConfig {
    private final Environment environment;

    @Autowired
    public RepositoryConfig(Environment environment) {
        this.environment = environment;
    }

    @Bean
    public DataSource dataSource() throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass(environment.getProperty("spring.datasource.driver-class-name"));
        dataSource.setJdbcUrl(environment.getProperty("spring.datasource.url"));
        dataSource.setUser(environment.getProperty("spring.datasource.username"));
        dataSource.setPassword(environment.getProperty("spring.datasource.password"));
        dataSource.setIdleConnectionTestPeriod(3000);
        return dataSource;
    }

    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder,
                                                                       @Qualifier("dataSource") DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .packages("com.bareisha.producthunter.model.base")
                .persistenceUnit("emfMain")
                .build();
    }

    @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManager(@Qualifier("entityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

}
