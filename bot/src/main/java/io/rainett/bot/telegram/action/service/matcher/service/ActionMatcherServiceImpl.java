package io.rainett.bot.telegram.action.service.matcher.service;

import io.rainett.bot.telegram.action.enumerations.UpdateType;
import io.rainett.bot.telegram.action.service.matcher.ActionMatcher;
import io.rainett.bot.telegram.annotation.ActionMatcherAnnotation;
import io.rainett.bot.telegram.exception.ActionMatcherNotFound;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ActionMatcherServiceImpl implements ActionMatcherService {

    private final Map<UpdateType, ActionMatcher> matchersMap;

    public ActionMatcherServiceImpl(ApplicationContext context) {
        this.matchersMap = context.getBeansWithAnnotation(ActionMatcherAnnotation.class).values().stream()
                .map(b -> (ActionMatcher) b)
                .collect(Collectors.toMap(ActionMatcherServiceImpl::getUpdateTypeFromAction, Function.identity()));
    }

    private static UpdateType getUpdateTypeFromAction(Object matcher) {
        return matcher.getClass().getDeclaredAnnotation(ActionMatcherAnnotation.class).value();
    }

    @Override
    public ActionMatcher getMatcher(UpdateType updateType) {
        return Optional.ofNullable(matchersMap.get(updateType))
                .orElseThrow(() -> new ActionMatcherNotFound(updateType));
    }
}
