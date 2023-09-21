package io.rainett.bot.telegram.annotation;

import io.rainett.bot.telegram.botactioncontainer.enumerations.MessageType;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@BotAction
@UpdateAnnotation
@MessageAnnotation(MessageType.COMMAND)
@Retention(RetentionPolicy.RUNTIME)
public @interface Command {
}
