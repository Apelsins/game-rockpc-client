package com.game.client.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "connection")
@Getter
@Setter
public class ConnectionProperties {

    private String ip;
    private int port;

}
