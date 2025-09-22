package com.example.demo.controller;

import com.example.demo.config.BotConfig;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class TelegeramBot extends TelegramLongPollingBot {
    private final BotConfig botConfig;

    public TelegeramBot(BotConfig botConfig) {
        super(botConfig.getToken());
        this.botConfig = botConfig;
    }




    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String message = update.getMessage().getText();
            SendMessage response = new SendMessage();

            response.setChatId(update.getMessage().getChatId().toString());
            response.setText("Вы написали: " + message);
            try {
                execute(response);
            } catch (TelegramApiException e) {
                e.printStackTrace();

            }
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
