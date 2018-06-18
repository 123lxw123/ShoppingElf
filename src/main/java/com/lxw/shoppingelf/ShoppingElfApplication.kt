package com.lxw.shoppingelf

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
open class ShoppingElfApplication {

    fun main(args: Array<String>) {
        SpringApplication.run(ShoppingElfApplication::class.java, *args)
    }
}
