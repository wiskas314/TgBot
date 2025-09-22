package com.example.demo.controller;

import com.example.demo.config.BotConfig;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.api.objects.Chat;

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
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            if (messageText.equals("/start")) {
                Chat chat = update.getMessage().getChat();
                String userName = (chat != null) ? chat.getFirstName() : "Неизвестный пользователь";
                sendStartMessage(chatId, userName);
            } else if (messageText.equals("/help")) {
                sendHelpMessage(chatId);
            } else {
                sendMessage(chatId, "Вы написали: " + messageText);
            }
        }
        else{
            long chatId = update.getMessage().getChatId();
            sendMessage(chatId, "Ошибка обработки входных данных, проверьте что вы ввели текст!");
        }
    }

    private void sendStartMessage(long chatId, String userName) {
        String responseText = "Привет, " + userName + "! Я бот, готовый помогать.\nЧтобы узнать, что я умею, введи /help";
        sendMessage(chatId, responseText);
    }

    private void sendHelpMessage(long chatId) {
        String responseText = "Вот список доступных команд:\n" +
                "/start - Начать общение с ботом\n" +
                "/help - Получить список команд";
        sendMessage(chatId, responseText);
    }

    private void sendMessage(long chatId, String text) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(text);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            System.err.println("Error sending message: " + e.getMessage());
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