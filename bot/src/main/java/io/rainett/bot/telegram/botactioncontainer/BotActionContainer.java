package io.rainett.bot.telegram.botactioncontainer;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface BotActionContainer {
    Object findByUpdate(Update update);
}
