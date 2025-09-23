package com.example.demo.controller;

import com.example.demo.config.BotConfig;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


@Component
public class TelegramBot extends TelegramLongPollingBot {
    private final MessageHandler messageHandler;
    private final BotConfig botConfig;

    public TelegramBot(MessageHandler messageHandler, BotConfig botConfig) {
        super(botConfig.getToken());
        this.messageHandler = messageHandler;
        this.botConfig = botConfig;
    }

    @Override
    public void onUpdateReceived(Update update) {
        String responseText = messageHandler.handleUpdate(update);
        long chatId = update.getMessage().getChatId();

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