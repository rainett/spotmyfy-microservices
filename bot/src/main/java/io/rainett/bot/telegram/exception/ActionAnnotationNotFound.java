package io.rainett.bot.telegram.exception;

public class ActionAnnotationNotFound extends RuntimeException {
    public ActionAnnotationNotFound(Class<?> actionClass) {
        super("BotAction annotation was not found in class = [" + actionClass + "]");
    }
}
