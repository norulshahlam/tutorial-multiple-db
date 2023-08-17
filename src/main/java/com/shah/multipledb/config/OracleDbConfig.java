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
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
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
@EnableJpaRepositories(basePackages = "com.shah.multipledb.repository.oracle",
        entityManagerFactoryRef = "oracleManagerFactory",
        transactionManagerRef = "oracleTransactionManager")
public class OracleDbConfig {

    @Value("${spring.datasource.oracle.show-sql:false}")
    private boolean showSql;
    @Value("${spring.datasource.oracle.generate-ddl:false}")
    private boolean generateDdl;

    @Autowired
    private Environment env;

    @Bean
    @ConfigurationProperties("spring.datasource.oracle")
    public DataSourceProperties oracleDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.oracle.configuration")
    public DataSource oracleDataSource() {

        ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator();
        resourceDatabasePopulator.addScript(new ClassPathResource("/data.sql"));

        return oracleDataSourceProperties()
                .initializeDataSourceBuilder()
                .type(BasicDataSource.class)
                .build();
    }

    @Bean(name = "oracleManagerFactory")
    public LocalContainerEntityManagerFactoryBean oracleManagerFactory(
            ) {
        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();

        final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setShowSql(showSql);
        vendorAdapter.setGenerateDdl(generateDdl);
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", env.getProperty("spring.datasource.oracle.ddl-auto"));
        properties.put("hibernate.dialect", env.getProperty("spring.datasource.oracle.driver-class-name"));
        properties.put("hibernate.default_schema", env.getProperty("spring.datasource.oracle.schema"));
        em.setJpaPropertyMap(properties);
        em.setPackagesToScan("com.shah.multipledb.entity.oracle");
        em.setDataSource(oracleDataSource());
        em.setJpaVendorAdapter(vendorAdapter);

      return em;
    }

    @Bean
    public PlatformTransactionManager oracleTransactionManager(
            final @Qualifier("oracleManagerFactory") LocalContainerEntityManagerFactoryBean oracleManagerFactory) {
        return new JpaTransactionManager(oracleManagerFactory.getObject());
    }
}
