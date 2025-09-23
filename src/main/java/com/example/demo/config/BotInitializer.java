package com.example.demo.config;

import com.example.demo.controller.TelegramBot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

/**
 * Класс-инициализатор Telegram-бота.
 */
@Component
public class BotInitializer {

    /**
     * Экземпляр бота, который будет зарегистрирован в Telegram API.
     */
    private final TelegramBot telegramBot;

    /**
     * Конструктор класса инициализатора.
     *
     * @param telegramBot экземпляр бота для регистрации
     */
    @Autowired
    public BotInitializer(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }

    /**
     * Метод инициализации бота.

     */
    @EventListener({ContextRefreshedEvent.class})
    public void init() throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        try {
            telegramBotsApi.registerBot(telegramBot);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}