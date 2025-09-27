package com.example.demo.config;



import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


@Configuration

@PropertySource("application.properties")
public class BotConfig {
    @Value("${bot.name}")
    private String botName;
    @Value("${bot.token}")
    private String token;

    public String getBotName() {
        return botName;
    }

    public String getToken() {
        return token;
    }

    public BotConfig(){

    }
}
