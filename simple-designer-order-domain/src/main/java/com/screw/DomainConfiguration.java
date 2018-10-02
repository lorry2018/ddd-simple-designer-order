package com.screw;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 领域层是基于领域模型的设计。
 */
@Configuration
@ComponentScan({"com.screw.infrastructure.mapper", "com.screw.infrastructure.repository"})
@MapperScan("com.screw.infrastructure.mapper")
public class DomainConfiguration {
}
