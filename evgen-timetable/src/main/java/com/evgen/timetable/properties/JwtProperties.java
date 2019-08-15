package com.evgen.timetable.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "security.jwt")
public class JwtProperties {

    private String prefix = "Bearer";
    private int expiration = 24 * 60 * 60 * 1000;
    private String secret = "JwtSecretKey";

}