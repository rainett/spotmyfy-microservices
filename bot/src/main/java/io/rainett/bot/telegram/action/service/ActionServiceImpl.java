package io.rainett.bot.telegram.action.service;

import io.rainett.bot.telegram.action.DefaultAction;
import io.rainett.bot.telegram.action.enumerations.UpdateType;
import io.rainett.bot.telegram.action.service.matcher.ActionMatcher;
import io.rainett.bot.telegram.action.service.matcher.service.ActionMatcherService;
import io.rainett.bot.telegram.action.service.type.TypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ActionServiceImpl implements ActionService {

    private final TypeService typeService;
    private final ActionMatcherService actionMatcherService;
    private final DefaultAction defaultAction;


    @Override
    public Object getActionFromUpdate(Map<UpdateType, List<Object>> botActionsMap, Update update) {
        UpdateType updateType = typeService.getUpdateTypeFromUpdate(update);
        List<Object> botActionsQueue = botActionsMap.get(updateType);
        ActionMatcher actionMatcher = actionMatcherService.getMatcher(updateType);
        return getBotActionFromList(update, botActionsQueue, actionMatcher);
    }

    private Object getBotActionFromList(Update update, List<Object> botActionsQueue, ActionMatcher actionMatcher) {
        if (botActionsQueue != null && !botActionsQueue.isEmpty()) {
            for (Object action : botActionsQueue) {
                if (actionMatcher.match(action, update)) {
                    return action;
                }
            }
        }
        return defaultAction;
    }

}
