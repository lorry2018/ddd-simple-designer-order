package com.screw.service;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 应用层是基于用户任务的设计。
 */
@Configuration
@EnableAspectJAutoProxy
@EnableTransactionManagement
@ComponentScan
public class AppConfiguration {
}
