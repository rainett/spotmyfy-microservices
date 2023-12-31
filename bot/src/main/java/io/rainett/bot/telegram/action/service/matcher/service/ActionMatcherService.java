package io.rainett.bot.telegram.action.service.matcher.service;

import io.rainett.bot.telegram.action.enumerations.UpdateType;
import io.rainett.bot.telegram.action.service.matcher.ActionMatcher;

public interface ActionMatcherService {
    ActionMatcher getMatcher(UpdateType combinedType);
}
