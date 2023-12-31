package io.rainett.bot.telegram.action.service.matcher;

import io.rainett.bot.telegram.action.enumerations.UpdateType;
import io.rainett.bot.telegram.annotation.ActionMatcherAnnotation;
import io.rainett.bot.telegram.annotation.update.message.Text;
import org.telegram.telegrambots.meta.api.objects.Update;

@ActionMatcherAnnotation(UpdateType.TEXT)
public class TextMatcher implements ActionMatcher {

    @Override
    public boolean match(Object action, Update update) {
        return isText(update) && textMatches(action, update);
    }

    private boolean isText(Update update) {
        return update.hasMessage() && update.getMessage().hasText();
    }

    /**
     * Checks if the text matches the text-action.
     * Instead of proving, that it matches,
     * it considers that the text matches action by default, and tries to refute it.
     * @param action Text-Action, also: user's update processor, user's action
     * @param update Update that contains a text message
     * @return true if update matches action
     */
    private boolean textMatches(Object action, Update update) {
        Text textAnnotation = action.getClass().getDeclaredAnnotation(Text.class);
        String messageText = update.getMessage().getText();

        boolean equalsMatches = textAnnotation.equals().isEmpty() ||
                messageText.equals(textAnnotation.equals());

        boolean startsWithMatches = textAnnotation.startsWith().isEmpty() ||
                messageText.startsWith(textAnnotation.startsWith());

        boolean endsWithMatches = textAnnotation.endsWith().isEmpty() ||
                messageText.endsWith(textAnnotation.endsWith());

        boolean containsMatches = textAnnotation.contains().isEmpty() ||
                messageText.contains(textAnnotation.contains());

        return equalsMatches && startsWithMatches && endsWithMatches && containsMatches;

    }
}
