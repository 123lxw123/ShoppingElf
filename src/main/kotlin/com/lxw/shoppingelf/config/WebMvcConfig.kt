package com.lxw.shoppingelf.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor
import org.springframework.web.context.request.async.TimeoutCallableProcessingInterceptor
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport


@Configuration
class WebMvcConfig : WebMvcConfigurationSupport() {

    override fun configureAsyncSupport(configurer: AsyncSupportConfigurer) {
        configurer.setDefaultTimeout(60 * 1000L)
        configurer.registerCallableInterceptors(timeoutInterceptor())
        configurer.setTaskExecutor(threadPoolTaskExecutor())
    }

    @Bean
    fun timeoutInterceptor(): TimeoutCallableProcessingInterceptor {
        return TimeoutCallableProcessingInterceptor()
    }

    @Bean
    fun threadPoolTaskExecutor(): ThreadPoolTaskExecutor {
        val t = ThreadPoolTaskExecutor()
        t.corePoolSize = 10
        t.maxPoolSize = 50
        t.setThreadNamePrefix("YJH")
        return t
    }
}