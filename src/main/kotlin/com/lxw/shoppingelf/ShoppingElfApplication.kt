package com.lxw.shoppingelf

import org.mybatis.spring.annotation.MapperScan
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer
import org.springframework.scheduling.annotation.EnableScheduling





@MapperScan("com.lxw.shoppingelf.mapper")
@EnableScheduling
@SpringBootApplication
class ShoppingElfApplication: SpringBootServletInitializer() {

    override fun configure(application: SpringApplicationBuilder): SpringApplicationBuilder {
        return application.sources(ShoppingElfApplication::class.java)
    }
}

fun main(args: Array<String>) {
    SpringApplication.run(ShoppingElfApplication::class.java, *args)
}