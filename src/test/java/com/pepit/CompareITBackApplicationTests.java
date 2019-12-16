package com.pepit;

import com.pepit.config.Conf;
import org.junit.ClassRule;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.MySQLContainer;

import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@ActiveProfiles("test")
@AutoConfigureMockMvc
@ContextConfiguration(initializers = {CompareITBackApplicationTests.Initializer.class})
@Transactional
public abstract class CompareITBackApplicationTests {

    @ClassRule
    public static MySQLContainer mysqlContainer = new MySQLContainer("mysql:8")
            .withDatabaseName("compareIt")
            .withUsername("test")
            .withPassword("test");

    static class Initializer
            implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertyValues.of(
                    "spring.datasource.url=" + mysqlContainer.getJdbcUrl(),
                    "spring.datasource.username=" + mysqlContainer.getUsername(),
                    "spring.datasource.password=" + mysqlContainer.getPassword()
            ).applyTo(configurableApplicationContext.getEnvironment());
            mysqlContainer.addExposedPort(33060);
            Conf.getInstance().setXPORT(mysqlContainer.getMappedPort(33060) + "");
            Conf.getInstance().setHOST(mysqlContainer.getContainerIpAddress());
            Conf.getInstance().setPASSWORD(mysqlContainer.getPassword());
            Conf.getInstance().setNAME(mysqlContainer.getDatabaseName());
        }

    }
}
