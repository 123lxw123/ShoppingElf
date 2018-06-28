package com.lxw.shoppingelf

import org.mybatis.spring.annotation.MapperScan
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@MapperScan("com.lxw.shoppingelf.mapper")
@EnableScheduling
@SpringBootApplication
open class ShoppingElfApplication

fun main(args: Array<String>) {
    runApplication<ShoppingElfApplication>(*args)
}
