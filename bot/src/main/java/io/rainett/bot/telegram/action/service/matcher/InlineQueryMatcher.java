package io.rainett.bot.telegram.action.service.matcher;

import io.rainett.bot.telegram.action.enumerations.UpdateType;
import io.rainett.bot.telegram.annotation.ActionMatcherAnnotation;
import org.telegram.telegrambots.meta.api.objects.Update;

@ActionMatcherAnnotation(UpdateType.INLINE_QUERY)
public class InlineQueryMatcher implements ActionMatcher {

    @Override
    public boolean match(Object action, Update update) {
        return update.hasInlineQuery() && queryMatches(action, update);
    }

    // TODO: create query matching system
    private boolean queryMatches(Object action, Update update) {
        return true;
    }

}
