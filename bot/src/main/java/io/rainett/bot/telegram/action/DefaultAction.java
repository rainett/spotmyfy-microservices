package io.rainett.bot.telegram.action;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@Slf4j
public class DefaultAction {

    public void emptyMethod(Update update) {
        log.error("No suitable BotAction was found for update with id = [" + update.getUpdateId() + "]");
    }


}
