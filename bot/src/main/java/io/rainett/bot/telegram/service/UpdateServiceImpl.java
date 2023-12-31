package io.rainett.bot.telegram.service;

import io.rainett.bot.telegram.action.ActionContainer;
import io.rainett.bot.telegram.exception.RunnableMethodNotFound;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class UpdateServiceImpl implements UpdateService {

    private final ActionContainer actionContainer;

    @Override
    public void processUpdate(Update update) {
        Object botAction = actionContainer.findByUpdate(update);
        Method method = getRunnableMethod(botAction);
        try {
            method.invoke(botAction, update);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    private static Method getRunnableMethod(Object botAction) {
        Method[] methods = botAction.getClass().getDeclaredMethods();
        return Arrays.stream(methods)
                .filter(m -> m.getParameterCount() == 1
                        && m.getParameterTypes()[0].equals(Update.class))
                .findAny()
                .orElseThrow(() -> new RunnableMethodNotFound(botAction));
    }
}
