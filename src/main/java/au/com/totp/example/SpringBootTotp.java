package au.com.totp.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by pablocaif on 16/09/15.
 */

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class SpringBootTotp {
    public static void main(String[] args){
        SpringApplication.run(SpringBootTotp.class, args);
    }
}


