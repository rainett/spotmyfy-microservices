package io.rainett.bot.telegram.annotation.update.message;

import io.rainett.bot.telegram.action.enumerations.UpdateType;
import io.rainett.bot.telegram.annotation.BotAction;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@BotAction(UpdateType.COMMAND)
@Retention(RetentionPolicy.RUNTIME)
public @interface Command {

    String value();

    boolean ignoreInMenu() default false;

    String description() default "\u200E";

}
