package org.home.server.services;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
@PropertySource("classpath:application.properties")
@ComponentScan(basePackages = {"org.home.server.services"})
public class ConfigTest {

}