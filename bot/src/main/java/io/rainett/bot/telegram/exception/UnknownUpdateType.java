package io.rainett.bot.telegram.exception;

import org.telegram.telegrambots.meta.api.objects.Update;

public class UnknownUpdateType extends RuntimeException {
    public UnknownUpdateType(Update update) {
        super("Received an update with unknown type: " + update);
    }
}
