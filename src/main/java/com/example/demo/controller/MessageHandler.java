package com.example.demo.controller;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class MessageHandler {

    public String handleUpdate(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            if (messageText.equals("/start")) {
                Chat chat = update.getMessage().getChat();
                String userName = (chat != null) ? chat.getFirstName() : "Неизвестный пользователь";
                return sendStartMessage(chatId, userName);
            } else if (messageText.equals("/help")) {
                return sendHelpMessage(chatId);
            } else {
                return sendMessage(chatId, messageText);
            }
        } else {
            return "Ошибка обработки входных данных, проверьте что вы ввели текст!";
        }
    }

    private String sendStartMessage(long chatId, String userName) {
        return "Привет, " + userName + "! Я бот, готовый помогать.\nЧтобы узнать, что я умею, введи /help";
    }

    private String sendHelpMessage(long chatId) {
        return "Вот список доступных команд:\n" +
                "/start - Начать общение с ботом\n" +
                "/help - Получить список команд";
    }

    private String sendMessage(long chatId, String messageText) {
        return "Вы написали: " + messageText;
    }
}
