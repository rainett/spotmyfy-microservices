package io.rainett.bot.telegram.exception;

import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;

import java.util.List;

public class CommandUpdateException extends RuntimeException {
    public CommandUpdateException(List<BotCommand> commands) {
        super("Failed to set bot commands = [" + commands + "]");
    }
}
