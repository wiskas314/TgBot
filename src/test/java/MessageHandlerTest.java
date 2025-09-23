

import com.example.demo.controller.MessageHandler;

import org.junit.jupiter.api.Test;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import static org.junit.jupiter.api.Assertions.assertEquals;


class MessageHandlerTest {

    @Test
    void testEchoCommand() {

        MessageHandler messageHandler = new MessageHandler();

        Update update = new Update();
        Message message = new Message();
        message.setText("ТЕСТ");
        update.setMessage(message);

        String result = messageHandler.handleUpdate(update);

        assertEquals("Вы написали: ТЕСТ", result);
}}