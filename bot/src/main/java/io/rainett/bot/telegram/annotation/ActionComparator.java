package io.rainett.bot.telegram.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Component
public @interface ActionComparator {
    Class<? extends Annotation> value();
}
