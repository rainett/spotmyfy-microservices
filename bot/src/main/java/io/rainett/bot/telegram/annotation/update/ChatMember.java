package io.rainett.bot.telegram.annotation.update;

import io.rainett.bot.telegram.annotation.BotAction;
import io.rainett.bot.telegram.annotation.UpdateAnnotation;
import io.rainett.bot.telegram.botactioncontainer.enumerations.UpdateType;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@BotAction
@UpdateAnnotation(UpdateType.CHAT_MEMBER)
@Retention(RetentionPolicy.RUNTIME)
public @interface ChatMember {
}
