package com.prtr.ykgc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("com.prtr.ykgc.mapper")
@EnableTransactionManagement
public class YkgcApplication {
    public static void main(String[] args) {
        SpringApplication.run(YkgcApplication.class, args);
    }
}
