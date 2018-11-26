package com.prtr.ykgc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@MapperScan("com.prtr.ykgc.mapper")
@EnableTransactionManagement
@EnableSwagger2
public class YkgcApplication {
    public static void main(String[] args) {
        SpringApplication.run(YkgcApplication.class, args);
    }
}
