package io.rainett.bot.telegram.annotation;

import io.rainett.bot.telegram.action.enumerations.UpdateType;
import org.springframework.stereotype.Component;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Component
public @interface ActionMatcherAnnotation {
    UpdateType value();
}
