package com.kesmarki.task.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("application.validation")
@Getter
@Setter
public class ValidationProperties {
    private Long stringLength;
}
