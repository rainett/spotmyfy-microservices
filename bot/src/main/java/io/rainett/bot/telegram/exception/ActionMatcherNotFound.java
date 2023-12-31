package io.rainett.bot.telegram.exception;

import io.rainett.bot.telegram.action.enumerations.UpdateType;

public class ActionMatcherNotFound extends RuntimeException {
    public ActionMatcherNotFound(UpdateType updateType) {
        super("No action matcher found for CombinedType = [" + updateType + "]");
    }
}
