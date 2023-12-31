package io.rainett.bot.telegram.action;

import io.rainett.bot.telegram.Bot;
import io.rainett.bot.telegram.action.enumerations.UpdateType;
import io.rainett.bot.telegram.action.service.ActionService;
import io.rainett.bot.telegram.action.service.comparator.service.ComparatorService;
import io.rainett.bot.telegram.action.service.type.TypeService;
import io.rainett.bot.telegram.annotation.BotAction;
import io.rainett.bot.telegram.annotation.update.message.Command;
import io.rainett.bot.telegram.exception.CommandUpdateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Component
@Slf4j
public class ActionContainerImpl implements ActionContainer {

    private final Map<UpdateType, List<Object>> botActions;
    private final ActionService actionService;

    @Autowired
    public ActionContainerImpl(ApplicationContext context,
                               ActionService actionService,
                               TypeService typeService,
                               ComparatorService comparatorService,
                               Bot bot) {
        this.actionService = actionService;
        this.botActions = context.getBeansWithAnnotation(BotAction.class).values().stream()
                .collect(Collectors.groupingBy(typeService::getUpdateTypeFromAction,
                        getBotActionQueueCollector()));
        this.botActions.values().forEach(a -> a.sort(comparatorService::compare));
        log.info("Actions created successfully: {}", botActions);
        updateCommands(bot);
    }

    private void updateCommands(Bot bot) {
        List<BotCommand> commands = getBotCommands();
        try {
            bot.executeAsync(new SetMyCommands(commands, null, null));
        } catch (TelegramApiException e) {
            throw new CommandUpdateException(commands);
        }
    }

    private List<BotCommand> getBotCommands() {
        List<BotCommand> commands = new LinkedList<>();
        if (!this.botActions.containsKey(UpdateType.COMMAND)) {
            return commands;
        }
        for (Object c : this.botActions.get(UpdateType.COMMAND)) {
            Command command = c.getClass().getDeclaredAnnotation(Command.class);
            if (!command.ignoreInMenu()) {
                commands.add(new BotCommand(command.value(), command.description()));
            }
        }
        return commands;
    }

    private Collector<Object, ?, List<Object>> getBotActionQueueCollector() {
        return Collectors.toCollection(LinkedList::new);
    }

    @Override
    public Object findByUpdate(Update update) {
        return actionService.getActionFromUpdate(botActions, update);
    }
}
