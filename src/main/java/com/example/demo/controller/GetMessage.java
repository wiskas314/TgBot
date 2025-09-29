package com.example.demo.controller;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;


@Component
public class GetMessage {
    /**
     * считывание сообщения пользователя
     */
    public String getMessage(Update update) {
        String messageText = "";
        if (update.hasMessage() && update.getMessage().hasText()) {
            messageText = update.getMessage().getText();
        }
        return messageText;
    }
}
