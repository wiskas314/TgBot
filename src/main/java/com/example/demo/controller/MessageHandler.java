package com.example.demo.controller;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * класс-обработчик сообщений Telegram бота
 * обеспечивает обработку входящих сообщений и генерацию соответствующих текстовых ответов
 */
@Component
public class MessageHandler {
    /**
     * обрабатывает входящее обновление от Telegram  и возвращает текстовый ответ
     */
    public String handleUpdate(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();

            if (messageText.equals("/start")) {
                Chat chat = update.getMessage().getChat();
                String userName = (chat != null) ? chat.getFirstName() : "Неизвестный пользователь";
                return sendStartMessage(userName);
            } else if (messageText.equals("/help")) {
                return sendHelpMessage();
            } else {
                return sendMessage(messageText);
            }
        } else {
            return "Ошибка обработки входных данных, проверьте что вы ввели текст!";
        }
    }

    /**
     *формирует приветственное сообщение для команды /start
     *содержит персональное приветствие с именем пользователя и информацию о доступных командах
     */
    private String sendStartMessage( String userName) {
        return "Привет, " + userName + "! Я бот, готовый помогать.\nЧтобы узнать, что я умею, введи /help";
    }

    /**
     * формирует справочное сообщение для команды /help
     * содержит список всех доступных команд с их описанием
     */
    private String sendHelpMessage() {
        return "Вот список доступных команд:\n" +
                "/start - Начать общение с ботом\n" +
                "/help - Получить список команд";
    }

    /**
     *обрабатывает обычные текстовые сообщения (не команды)
     * возвращает эхо-ответ с текстом полученного сообщения
     */
    private String sendMessage(String messageText) {
        return "Вы написали: " + messageText;
    }
}
