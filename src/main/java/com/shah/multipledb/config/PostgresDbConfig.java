package com.shah.multipledb.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;


/**
 * @author NORUL
 */
@Configuration
@EnableJpaRepositories(basePackages = "com.shah.multipledb.repository.postgres",
        entityManagerFactoryRef = "postgresManagerFactory",
        transactionManagerRef = "postgresTransactionManager")
public class PostgresDbConfig {

    @Value("${spring.datasource.postgres.show-sql:false}")
    private boolean showSql;
    @Value("${spring.datasource.postgres.generate-ddl:false}")
    private boolean generateDdl;

    @Autowired
    private Environment env;

    @Bean
    @ConfigurationProperties("spring.datasource.postgres")
    public DataSourceProperties postgresDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.postgres.configuration")
    public DataSource postgresDataSource() {
        return postgresDataSourceProperties()
                .initializeDataSourceBuilder()
                .type(BasicDataSource.class)
                .build();
    }

//    @Bean
//    @ConfigurationProperties("spring.datasource.postgres")
//    public DataSourceInitializer dataSourceInitializer(DataSource ds) {
//        ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator();
//        resourceDatabasePopulator.addScript(new ClassPathResource("/data.sql"));
//
//        DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
//        dataSourceInitializer.setDataSource(ds);
//        dataSourceInitializer.setDatabasePopulator(resourceDatabasePopulator);
//        return dataSourceInitializer;
//    }

    @Bean(name = "postgresManagerFactory")
    public LocalContainerEntityManagerFactoryBean postgresManagerFactory(
    ) {
        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();

        final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setShowSql(showSql);
        vendorAdapter.setGenerateDdl(generateDdl);
        HashMap<String, Object> properties = new HashMap<>(3);
        properties.put("hibernate.hbm2ddl.auto", env.getProperty("spring.datasource.postgres.ddl-auto"));
        properties.put("hibernate.dialect", env.getProperty("spring.datasource.postgres.driver-class-name"));
        properties.put("hibernate.default_schema", env.getProperty("spring.datasource.postgres.schema"));
        em.setJpaPropertyMap(properties);
        em.setPackagesToScan("com.shah.multipledb.entity.postgres");
        em.setDataSource(postgresDataSource());
        em.setJpaVendorAdapter(vendorAdapter);

        return em;
    }

    @Bean
    public PlatformTransactionManager postgresTransactionManager(
            final @Qualifier("postgresManagerFactory") LocalContainerEntityManagerFactoryBean postgresManagerFactory) {
        return new JpaTransactionManager(postgresManagerFactory.getObject());
    }
}
