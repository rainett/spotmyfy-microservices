package io.rainett.bot.telegram.botactioncontainer.combined;

import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;
import java.util.Map;

public interface UpdateService {
    Object getActionFromUpdate(Map<CombinedType, List<Object>> botActions, Update update);
}
