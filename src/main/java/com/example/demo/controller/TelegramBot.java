package com.example.demo.controller;

import com.example.demo.config.BotConfig;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 * Основной класс Telegram бота , обеспечивает обработку входящих сообщений
 * и отправку ответов пользователям
 */
@Component
public class TelegramBot extends TelegramLongPollingBot {
    private final MessageHandler messageHandler;
    private final BotConfig botConfig;
    private final GetMessage getmessage;
    private final GetUsername getusername;
    /**
     * конструктор класса
     * @param messageHandler обработчик бизнес-логики сообщений
     * @param botConfig конфигурация бота, содержащая токен и имя
     */
    public TelegramBot(MessageHandler messageHandler, BotConfig botConfig, GetMessage getmessage, GetUsername getusername) {
        super(botConfig.getToken());
        this.messageHandler = messageHandler;
        this.botConfig = botConfig;
        this.getmessage = getmessage;
        this.getusername = getusername;
    }


    @Override
    public void onUpdateReceived(Update update) {
        long chatId = update.getMessage().getChatId();
        String userName = getusername.getUsername(update);
        String userMessage = getmessage.getMessage(update);

        String responseText = messageHandler.handleUpdate(userMessage, userName);

        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(responseText);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return botConfig.getBotName();
    }

    @Override
    public String getBotToken() {
        return botConfig.getToken();
    }
}