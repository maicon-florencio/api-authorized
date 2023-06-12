package com.example.demo.config;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.lifecycle.Startables;

import java.util.Map;
import java.util.stream.Stream;

@ContextConfiguration(initializers =  AbstractIntegrationTest.Initialazer.class)
public class AbstractIntegrationTest {

    static class Initialazer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        static MySQLContainer<?> mysql = new MySQLContainer<>("mysql:8.0.33");
        
        private static void startContainers(){
            Startables.deepStart(Stream.of(mysql)).join();
        }

        @Override
        @SuppressWarnings({"unchecked", "rastypes"})
        public void initialize(ConfigurableApplicationContext applicationContext) {
            startContainers();
            ConfigurableEnvironment enviroment =  applicationContext.getEnvironment();

            MapPropertySource testeContainers =  new MapPropertySource(
                    "testcontainers",
                    (Map) createConnectionConfiguration()
            );
            enviroment.getPropertySources().addFirst(testeContainers);
        }

        private static Map<String,String> createConnectionConfiguration() {
            return Map.of("spring.datasource.url", mysql.getJdbcUrl(),
            "spring.datasource.username", mysql.getUsername(),
            "spring.datasource.password", mysql.getPassword()
            );
        }
    }
}
