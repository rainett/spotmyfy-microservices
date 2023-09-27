package io.rainett.bot.telegram.annotation;

import io.rainett.bot.telegram.botactioncontainer.enumerations.MessageType;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@UpdateAnnotation
@Retention(RetentionPolicy.RUNTIME)
public @interface MessageAnnotation {

    MessageType value() default MessageType.TEXT;

}
