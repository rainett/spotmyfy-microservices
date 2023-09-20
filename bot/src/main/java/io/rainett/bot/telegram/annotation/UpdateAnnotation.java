package io.rainett.bot.telegram.annotation;

import io.rainett.bot.telegram.botactioncontainer.enumerations.UpdateType;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface UpdateAnnotation {

    UpdateType value() default UpdateType.MESSAGE;

}
