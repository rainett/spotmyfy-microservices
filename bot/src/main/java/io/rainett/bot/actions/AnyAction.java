package io.rainett.bot.actions;

import io.rainett.bot.telegram.Bot;
import io.rainett.bot.telegram.annotation.BotAction;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@BotAction
@RequiredArgsConstructor
public class AnyAction {

    private final Bot bot;

    @SneakyThrows
    public void handleUpdate(Update update) {
        if (!update.hasMessage()) {
            return;
        }
        String chatId = update.getMessage().getChatId().toString();
        bot.execute(new SendMessage(chatId, "ping-pong"));
    }

}
