package io.rainett.bot.telegram.action;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface ActionContainer {
    Object findByUpdate(Update update);
}
