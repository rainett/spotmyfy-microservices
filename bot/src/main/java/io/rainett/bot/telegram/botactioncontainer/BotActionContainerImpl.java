package io.rainett.bot.telegram.botactioncontainer;

import io.rainett.bot.telegram.annotation.BotAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class BotActionContainerImpl implements BotActionContainer {

    private final Object[] botActions;

    @Autowired
    public BotActionContainerImpl(ApplicationContext context) {
        this.botActions = context.getBeansWithAnnotation(BotAction.class).values().toArray();
    }

    @Override
    public Object findByUpdate(Update update) {
        // TODO: Implement search of needed executable
        return botActions[0];
    }
}
