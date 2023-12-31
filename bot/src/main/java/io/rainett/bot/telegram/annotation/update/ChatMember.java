package io.rainett.bot.telegram.annotation.update;

import io.rainett.bot.telegram.action.enumerations.UpdateType;
import io.rainett.bot.telegram.annotation.BotAction;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@BotAction(UpdateType.CHAT_MEMBER)
@Retention(RetentionPolicy.RUNTIME)
public @interface ChatMember {
}
