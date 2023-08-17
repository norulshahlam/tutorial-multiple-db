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
@EnableJpaRepositories(basePackages = "com.shah.multipledb.repository.mysql",
        entityManagerFactoryRef = "mysqlManagerFactory",
        transactionManagerRef = "mysqlTransactionManager")
public class MysqlDbConfig {

    @Value("${spring.datasource.mysql.show-sql:false}")
    private boolean showSql;
    @Value("${spring.datasource.mysql.generate-ddl:false}")
    private boolean generateDdl;

    @Autowired
    private Environment env;

    @Bean
    @ConfigurationProperties("spring.datasource.mysql")
    public DataSourceProperties mysqlDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.mysql.configuration")
    public DataSource mysqlDataSource() {

        return mysqlDataSourceProperties()
                .initializeDataSourceBuilder()
                .type(BasicDataSource.class)
                .build();
    }

    @Bean(name = "mysqlManagerFactory")
    public LocalContainerEntityManagerFactoryBean mysqlManagerFactory(
            ) {
        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();

        final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setShowSql(showSql);
        vendorAdapter.setGenerateDdl(generateDdl);
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", env.getProperty("spring.datasource.mysql.ddl-auto"));
        properties.put("hibernate.dialect", env.getProperty("spring.datasource.mysql.driver-class-name"));
        properties.put("hibernate.default_schema", env.getProperty("spring.datasource.mysql.schema"));
        em.setJpaPropertyMap(properties);

        em.setPackagesToScan("com.shah.multipledb.entity.mysql");
        em.setDataSource(mysqlDataSource());
        em.setJpaVendorAdapter(vendorAdapter);

      return em;
    }

    @Bean
    public PlatformTransactionManager mysqlTransactionManager(
            final @Qualifier("mysqlManagerFactory") LocalContainerEntityManagerFactoryBean mysqlManagerFactory) {
        return new JpaTransactionManager(mysqlManagerFactory.getObject());
    }
}
