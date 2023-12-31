package io.rainett.bot.telegram.annotation.update;

import io.rainett.bot.telegram.action.enumerations.UpdateType;
import io.rainett.bot.telegram.annotation.BotAction;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@BotAction(UpdateType.PRE_CHECKOUT_QUERY)
@Retention(RetentionPolicy.RUNTIME)
public @interface PreCheckoutQuery {
}
