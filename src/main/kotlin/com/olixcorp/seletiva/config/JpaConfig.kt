package com.olixcorp.seletiva.config


import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import org.springframework.transaction.PlatformTransactionManager
import javax.persistence.EntityManagerFactory
import javax.sql.DataSource

@Configuration
@EnableJpaRepositories(basePackages = ["com.olixcorp.seletiva.repository"])
class JpaConfig {

    @Value("\${spring.datasource.url}")
    private lateinit var dataSourceUrl: String

    @Value("\${spring.datasource.username}")
    private lateinit var dataSourceUsername: String

    @Value("\${spring.datasource.password}")
    private lateinit var dataSourcePassword: String

    @Value("\${spring.datasource.driver-class-name}")
    private lateinit var driverClassName: String

    @Bean
    @Primary
    fun dataSource(): DataSource {
        return DataSourceBuilder.create()
            .url(dataSourceUrl)
            .username(dataSourceUsername)
            .password(dataSourcePassword)
            .driverClassName(driverClassName)
            .build()
    }

    @Bean
    fun entityManagerFactory(): LocalContainerEntityManagerFactoryBean {
        val factoryBean = LocalContainerEntityManagerFactoryBean()
        factoryBean.dataSource = dataSource()
        factoryBean.setPackagesToScan("com.olixcorp.seletiva.model")
        factoryBean.jpaVendorAdapter = HibernateJpaVendorAdapter()
        return factoryBean
    }

    @Bean
    fun transactionManager(entityManagerFactory: EntityManagerFactory): PlatformTransactionManager {
        return JpaTransactionManager(entityManagerFactory)
    }
}