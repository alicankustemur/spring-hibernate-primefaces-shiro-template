package io.github.alicankustemur.person.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import io.github.alicankustemur.person.base.Common;

@Configuration
@ComponentScan(basePackages = Common.BASE_PACKAGE_PATH)
public class ApplicationConfiguration {

}
