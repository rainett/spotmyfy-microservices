package io.rainett.bot.telegram.action.service.matcher;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface ActionMatcher {
    boolean match(Object action, Update update);
}
