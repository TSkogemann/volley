package dk.tskogemann.data;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.persistence.config.PersistenceUnitProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;
import java.util.UUID;

import static org.eclipse.persistence.config.PersistenceUnitProperties.*;

/**
 * The JPA configuration, requires a DataSource
 *
 * @author Klaus Groenbaek
 *         Created 31/07/15.
 */
@Configuration
//@EnableJpaRepositories(basePackages = {"dk.tskogemann.data"})
@EnableTransactionManagement
@Slf4j
public class JPAConfig {

    public static final String ENTITIES_ROOT = "dk.tskogemann.data.entities";

    private JpaVendorAdapter jpaVendorAdapter() {
        EclipseLinkJpaVendorAdapter jpaVendorAdapter = new EclipseLinkJpaVendorAdapter();
        jpaVendorAdapter.setDatabase(Database.MYSQL);
        return jpaVendorAdapter;
    }

    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }

    @Bean()
    public DataSource createDataSource() {
        MysqlDataSource ds = new MysqlDataSource();
        ds.setUser("volley");
        ds.setPassword("0tsea!md3r");
        ds.setURL("jdbc:mysql://localhost:3306/volley?useUnicode=yes&characterEncoding=UTF-8");
        return ds;
    }


    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {

        Properties jpaProperties = new Properties();
        jpaProperties.setProperty("eclipselink.logging.parameters", "true");
        jpaProperties.setProperty(WEAVING, "false");
        //jpaProperties.setProperty(CACHE_SHARED_DEFAULT, "false");
        jpaProperties.put(DDL_GENERATION, PersistenceUnitProperties.CREATE_ONLY);
//        jpaProperties.put(CREATE_JDBC_DDL_FILE, "create.sql");
//        jpaProperties.put(DROP_JDBC_DDL_FILE, "drop.sql");
//        jpaProperties.put(DDL_GENERATION_MODE, DDL_BOTH_GENERATION);

//        jpaProperties.put(LOGGING_FILE, "output.log");
        jpaProperties.put(LOGGING_LEVEL, "FINE");

        LocalContainerEntityManagerFactoryBean lemfb = new LocalContainerEntityManagerFactoryBean();
        lemfb.setDataSource(dataSource);
        lemfb.setJpaVendorAdapter(jpaVendorAdapter());
        lemfb.setJpaProperties(jpaProperties);
        // If the don't have a unique name for the PU, we get in trouble in tests, since EclipseLink caches the PU by name
        // so if the name is not unique we will get a old PU even if we create a new Spring Context using DirtiesContext
        lemfb.setPersistenceUnitName(UUID.randomUUID().toString());
        lemfb.setPackagesToScan(ENTITIES_ROOT);
        return lemfb;
    }

}
