package me.liuweiqiang.hibernate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
@EnableJpaRepositories(repositoryImplementationPostfix = "Implement")
public class AccessingDataJpaApplication {

    private static final Logger logger = LoggerFactory.getLogger(AccessingDataJpaApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(AccessingDataJpaApplication.class);
    }

    @Bean
    @Transactional
    public CommandLineRunner demo(EventListener eventListener) {
        return (args) -> eventListener.start();
    }
}
