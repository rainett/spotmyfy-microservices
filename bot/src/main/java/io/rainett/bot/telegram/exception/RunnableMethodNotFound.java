package io.rainett.bot.telegram.exception;

public class RunnableMethodNotFound extends RuntimeException {
    public RunnableMethodNotFound(Object object) {
        super("No runnable method found in object = [" + object + "]");
    }
}
