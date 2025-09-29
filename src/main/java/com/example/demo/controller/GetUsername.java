package com.example.demo.controller;


import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * Получение/назначение имени пользователя
 */


@Component
public class GetUsername {
    public String getUsername(Update update){
        Chat chat = update.getMessage().getChat();
        String userName = (chat != null) ? chat.getFirstName() : "Неизвестный пользователь";
        return userName;
    }
}
