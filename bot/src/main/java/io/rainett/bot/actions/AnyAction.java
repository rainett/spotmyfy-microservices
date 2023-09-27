package io.rainett.bot.actions;

import io.rainett.bot.telegram.Bot;
import io.rainett.bot.telegram.annotation.UpdateAnnotation;
import io.rainett.bot.telegram.annotation.update.message.Text;
import io.rainett.bot.telegram.botactioncontainer.enumerations.UpdateType;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Text
@UpdateAnnotation(UpdateType.CHANNEL_POST)
@RequiredArgsConstructor
public class AnyAction {

    private final Bot bot;

    @SneakyThrows
    public void handleUpdate(Update update) {
        if (!update.hasChannelPost()) {
            return;
        }
        String chatId = update.getChannelPost().getChatId().toString();
        bot.execute(new SendMessage(chatId, "i received your updated command"));
    }

}
