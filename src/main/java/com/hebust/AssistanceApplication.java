package com.hebust;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.hebust.mapper")
@SpringBootApplication
public class AssistanceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AssistanceApplication.class, args);
    }

}
