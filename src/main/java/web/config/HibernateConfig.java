package web.config;


import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import web.model.User;

import javax.naming.NamingException;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;


@Configuration
@PropertySource("classpath:db.properties")
@EnableTransactionManagement

@ComponentScan(value = "web")
public class HibernateConfig {

   @Autowired
   private Environment env;

   @Bean
   public DataSource getDataSource() {
      DriverManagerDataSource dataSource = new DriverManagerDataSource();
      dataSource.setDriverClassName(env.getProperty("db.driver"));
      dataSource.setUrl(env.getProperty("db.url"));
      dataSource.setUsername(env.getProperty("db.username"));
      dataSource.setPassword(env.getProperty("db.password"));

      return dataSource;
   }

   @Bean
   LocalContainerEntityManagerFactoryBean entityManagerFactory() {
      LocalContainerEntityManagerFactoryBean emFactory = new LocalContainerEntityManagerFactoryBean();

      emFactory.setDataSource(getDataSource());
      emFactory.setPersistenceProviderClass(HibernatePersistenceProvider.class);
      emFactory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
      emFactory.setPackagesToScan("*.model*");

      Properties props = new Properties();


      props.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
      props.put("hibernate.format_sql", env.getProperty("hibernate.format_sql"));
      props.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
      props.put("hibernate.hibernate.dialect", env.getProperty("hibernate.dialect"));


      emFactory.setJpaProperties(props);

      return emFactory;
   }



   @Bean
   @Autowired
   public PlatformTransactionManager getTransactionManager(EntityManagerFactory emf) throws NamingException {
      JpaTransactionManager jpaTransaction = new JpaTransactionManager();
      jpaTransaction.setEntityManagerFactory(emf);
      return jpaTransaction;
   }
}
