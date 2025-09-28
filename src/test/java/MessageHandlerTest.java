

import com.example.demo.controller.MessageHandler;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class MessageHandlerTest {

    @Test
    void testEchoCommand() {

        MessageHandler messageHandler = new MessageHandler();

        String message = "TEST";
        String username = "Bob";

        String result = messageHandler.handleUpdate(message, username);

        assertEquals("Вы написали: TEST", result);
}}