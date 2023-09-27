package io.rainett.bot.telegram.annotation.update.message;

import io.rainett.bot.telegram.annotation.BotAction;
import io.rainett.bot.telegram.annotation.MessageAnnotation;
import io.rainett.bot.telegram.botactioncontainer.enumerations.MessageType;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@BotAction
@MessageAnnotation(MessageType.TEXT)
@Retention(RetentionPolicy.RUNTIME)
public @interface Text {
}
