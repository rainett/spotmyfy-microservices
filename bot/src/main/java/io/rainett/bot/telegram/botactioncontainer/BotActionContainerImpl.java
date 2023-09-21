package io.rainett.bot.telegram.botactioncontainer;

import io.rainett.bot.telegram.annotation.BotAction;
import io.rainett.bot.telegram.annotation.MessageAnnotation;
import io.rainett.bot.telegram.annotation.UpdateAnnotation;
import io.rainett.bot.telegram.botactioncontainer.combined.CombinedType;
import io.rainett.bot.telegram.botactioncontainer.combined.BotActionService;
import io.rainett.bot.telegram.botactioncontainer.enumerations.MessageType;
import io.rainett.bot.telegram.botactioncontainer.enumerations.UpdateType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class BotActionContainerImpl implements BotActionContainer {

    private final Map<CombinedType, List<Object>> botActions;
    private final BotActionService botActionService;

    @Autowired
    public BotActionContainerImpl(ApplicationContext context, BotActionService botActionService) {
        this.botActions = context.getBeansWithAnnotation(BotAction.class).values().stream()
                .collect(Collectors.groupingBy(BotActionContainerImpl::getCombinedType, Collectors.toList()));
        this.botActionService = botActionService;
    }

    private static CombinedType getCombinedType(Object bean) {
        UpdateType updateType = Objects.requireNonNull(AnnotationUtils
                .findAnnotation(bean.getClass(), UpdateAnnotation.class)).value();
        MessageAnnotation messageAnnotation = AnnotationUtils
                .findAnnotation(bean.getClass(), MessageAnnotation.class);
        MessageType messageType = messageAnnotation != null ? messageAnnotation.value() : null;
        return new CombinedType(updateType, messageType);
    }

    @Override
    public Object findByUpdate(Update update) {
        return botActionService.getActionFromUpdate(botActions, update);
    }
}
