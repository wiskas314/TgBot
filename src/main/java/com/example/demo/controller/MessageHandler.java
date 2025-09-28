package com.example.demo.controller;

import org.springframework.stereotype.Component;

/**
 * класс-обработчик сообщений Telegram бота
 * обеспечивает обработку входящих сообщений и генерацию соответствующих текстовых ответов
 */
@Component
public class MessageHandler {
    /**
     * обрабатывает входящее обновление от Telegram  и возвращает текстовый ответ
     */
    public String handleUpdate(String message, String userName) {
        if (!message.equals("")) {
            if (message.equals("/start")) {
                return startMessage(userName);
            } else if (message.equals("/help")) {
                return helpMessage();
            } else {
                return echoMessage(message);
            }
        } else {
            return ErrMessage();
        }
    }


    private String ErrMessage() {
        return "Ошибка обработки входных данных, проверьте что вы ввели текст!";
    }

    private String echoMessage(String messageText) {
        return "Вы написали: " + messageText;
    }

    private String startMessage(String userName) {
        return "Привет, " + userName + "! Я бот, готовый помогать.\nЧтобы узнать, что я умею, введи /help";
    }

    /**
     * формирует справочное сообщение для команды /help
     * содержит список всех доступных команд с их описанием
     */
    private String helpMessage() {
        return """
                Вот список доступных команд:
                /start - Начать общение с ботом
                /help - Получить список команд""";
    }
}
