package com.aorun.attorney;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// mapper 接口类扫描包配置
@MapperScan("com.aorun.attorney.dao")
public class AorunAttorneyApplication {

    public static void main(String[] args) {
        SpringApplication.run(AorunAttorneyApplication.class, args);
    }

}
