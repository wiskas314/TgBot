package com.example.demo.controller;

import com.example.demo.config.BotConfig;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.ArgumentMatchers;


import org.mockito.junit.jupiter.MockitoExtension;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TelegeramBotTest {
    BotConfig botConfig = mock(BotConfig.class);



    TelegeramBot telegramBot = new TelegeramBot(botConfig);

    Update update = mock(Update.class);
    Message message = mock(Message.class);

    @Test
    void testOnUpdateReceived() throws TelegramApiException {





        when(update.hasMessage()).thenReturn(true);
        when(update.getMessage()).thenReturn(message);
        when(message.hasText()).thenReturn(true);
        when(message.getText()).thenReturn("Привет, бот!");
        when(message.getChatId()).thenReturn(123456789L);


        TelegeramBot botSpy = spy(telegramBot);



        botSpy.onUpdateReceived(update);
        verify(botSpy).execute(ArgumentMatchers.argThat((SendMessage sendMessage) ->
                "123456789".equals(sendMessage.getChatId()) &&
                        "Вы написали: Привет, бот!".equals(sendMessage.getText())
        ));

    }

}
