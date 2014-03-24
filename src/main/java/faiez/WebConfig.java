package faiez;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;


@EnableTransactionManagement
@EnableAutoConfiguration
@Configuration
@ComponentScan(basePackages = "faiez")
@ImportResource("classpath:setup-database.xml")
@EnableJpaRepositories
public class WebConfig {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(WebConfig.class, args);
    }


	@Bean
	public DataSource dataSource() {
		EmbeddedDatabaseBuilder embeddedDatabaseBuilder = new EmbeddedDatabaseBuilder();
		return embeddedDatabaseBuilder.setType(EmbeddedDatabaseType.H2).build();

	}

	@Bean
	public HibernateTransactionManager transactionManager() {
		HibernateTransactionManager transactionManager=new HibernateTransactionManager();
		transactionManager.setSessionFactory(createSessionFactoryBean().getObject());
		return transactionManager;
	}

	@Bean
	public LocalSessionFactoryBean createSessionFactoryBean(){
		LocalSessionFactoryBean localSessionFactoryBean=new LocalSessionFactoryBean();
		/*Properties properties=new Properties();
		properties.setProperty("hibernate.dialect","org.hibernate.dialect.H2Dialect");
		properties.setProperty("hibernate.connection.driver_class","org.h2.Driver");
		properties.setProperty("hibernate.default_schema","PUBLIC");
		properties.setProperty("hibernate.connection.username","sa");
		properties.setProperty("hibernate.connection.password","");
		properties.setProperty("hibernate.hbm2ddl.auto","validate");
		properties.setProperty("hibernate.show_sql","true");
		localSessionFactoryBean.setHibernateProperties(properties);*/

		localSessionFactoryBean.setAnnotatedClasses(new Class<?>[]{Book.class});
		localSessionFactoryBean.setDataSource(dataSource());
		return localSessionFactoryBean;
	}
}
