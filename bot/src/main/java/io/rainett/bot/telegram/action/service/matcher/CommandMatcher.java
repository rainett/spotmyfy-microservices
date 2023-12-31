package io.rainett.bot.telegram.action.service.matcher;

import io.rainett.bot.telegram.action.enumerations.UpdateType;
import io.rainett.bot.telegram.annotation.ActionMatcherAnnotation;
import io.rainett.bot.telegram.annotation.update.message.Command;
import org.telegram.telegrambots.meta.api.objects.Update;

@ActionMatcherAnnotation(UpdateType.COMMAND)
public class CommandMatcher implements ActionMatcher {

    @Override
    public boolean match(Object action, Update update) {
        return isCommand(update) && commandMatches(action, update);
    }

    private boolean commandMatches(Object action, Update update) {
        String actionCommand = action.getClass().getDeclaredAnnotation(Command.class).value();
        String updateCommand = update.getMessage().getText().split(" ")[0];
        return actionCommand.equals(updateCommand);
    }

    private static boolean isCommand(Update update) {
        return update.hasMessage() && update.getMessage().isCommand();
    }
}
