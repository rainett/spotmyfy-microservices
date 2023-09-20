package io.rainett.bot.telegram.exception;

import org.telegram.telegrambots.meta.api.objects.Message;

public class UnknownMessageType extends RuntimeException {
    public UnknownMessageType(Message message) {
        super("Received a message with unknown type: " + message);
    }
}
