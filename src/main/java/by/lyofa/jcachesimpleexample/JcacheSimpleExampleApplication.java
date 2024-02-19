package by.lyofa.jcachesimpleexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class JcacheSimpleExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(JcacheSimpleExampleApplication.class, args);
    }

}
