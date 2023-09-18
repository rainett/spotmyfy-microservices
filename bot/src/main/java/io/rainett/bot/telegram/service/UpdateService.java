package io.rainett.bot.telegram.service;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface UpdateService {
    void processUpdate(Update update);
}
