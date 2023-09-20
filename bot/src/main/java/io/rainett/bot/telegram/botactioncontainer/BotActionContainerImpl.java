package io.rainett.bot.telegram.botactioncontainer;

import io.rainett.bot.telegram.annotation.BotAction;
import io.rainett.bot.telegram.annotation.MessageAnnotation;
import io.rainett.bot.telegram.annotation.UpdateAnnotation;
import io.rainett.bot.telegram.botactioncontainer.combined.CombinedType;
import io.rainett.bot.telegram.botactioncontainer.combined.UpdateService;
import io.rainett.bot.telegram.botactioncontainer.enumerations.MessageType;
import io.rainett.bot.telegram.botactioncontainer.enumerations.UpdateType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class BotActionContainerImpl implements BotActionContainer {

    private final Map<CombinedType, List<Object>> botActions;
    private final UpdateService updateService;

    @Autowired
    public BotActionContainerImpl(ApplicationContext context, UpdateService updateService) {
        this.botActions = context.getBeansWithAnnotation(BotAction.class).values().stream()
                .collect(Collectors.groupingBy(BotActionContainerImpl::getCombinedType, Collectors.toList()));
        this.updateService = updateService;
    }

    private static CombinedType getCombinedType(Object bean) {
        UpdateType updateType = bean.getClass().getAnnotation(UpdateAnnotation.class).value();
        MessageType messageType = bean.getClass().isAnnotationPresent(MessageAnnotation.class)
                ? bean.getClass().getAnnotation(MessageAnnotation.class).value()
                : null;
        return new CombinedType(updateType, messageType);
    }

    @Override
    public Object findByUpdate(Update update) {
        return updateService.getActionFromUpdate(botActions, update);
    }
}
