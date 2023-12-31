package io.rainett.bot.telegram.action.service.type;

import io.rainett.bot.telegram.action.enumerations.UpdateType;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface TypeService {
    UpdateType getUpdateTypeFromUpdate(Update update);
    UpdateType getUpdateTypeFromAction(Object action);
}
