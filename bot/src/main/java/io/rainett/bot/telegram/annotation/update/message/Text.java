package io.rainett.bot.telegram.annotation.update.message;

import io.rainett.bot.telegram.action.enumerations.UpdateType;
import io.rainett.bot.telegram.annotation.BotAction;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@BotAction(UpdateType.TEXT)
@Retention(RetentionPolicy.RUNTIME)
public @interface Text {

    String equals() default "";
    String startsWith() default "";
    String endsWith() default "";
    String contains() default "";

}
