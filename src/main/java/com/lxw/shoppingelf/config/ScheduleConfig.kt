package com.lxw.shoppingelf.config

import org.springframework.context.annotation.Configuration
import java.util.concurrent.Executors
import org.springframework.scheduling.config.ScheduledTaskRegistrar
import org.springframework.scheduling.annotation.SchedulingConfigurer

/**
 * 将所有的定时任务都放在一个线程池中
 */
@Configuration
open class ScheduleConfig : SchedulingConfigurer {
    override fun configureTasks(taskRegistrar: ScheduledTaskRegistrar) {
        taskRegistrar.setScheduler(Executors.newScheduledThreadPool(3))
    }
}