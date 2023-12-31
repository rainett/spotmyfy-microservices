package io.rainett.bot.telegram.action.service;

import io.rainett.bot.telegram.action.enumerations.UpdateType;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;
import java.util.Map;

public interface ActionService {
    Object getActionFromUpdate(Map<UpdateType, List<Object>> botActions, Update update);
}
