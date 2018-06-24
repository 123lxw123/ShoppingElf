package com.lxw.shoppingelf

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.scheduling.annotation.EnableScheduling

@EnableScheduling
@EnableJpaAuditing
@SpringBootApplication
open class ShoppingElfApplication

fun main(args: Array<String>) {
    runApplication<ShoppingElfApplication>(*args)
}
