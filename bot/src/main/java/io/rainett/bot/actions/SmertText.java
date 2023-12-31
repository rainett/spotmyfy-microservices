package io.rainett.bot.actions;

import io.rainett.bot.telegram.Bot;
import io.rainett.bot.telegram.annotation.update.message.Text;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@RequiredArgsConstructor
@Text(startsWith = "smert")
public class SmertText {

    private final Bot bot;

    @SneakyThrows
    public void handleUpdate(Update update) {
        Long chatId = update.getMessage().getChatId();
        bot.execute(new SendMessage(chatId.toString(), "smert"));
    }

}
