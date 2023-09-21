package io.rainett.bot.telegram.controller;

import io.rainett.bot.telegram.service.UpdateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

@RestController
@RequiredArgsConstructor
public class UpdateController {

    private final UpdateService updateService;

    @PostMapping("/")
    public BotApiMethod<?> onUpdateReceived(@RequestBody Update update) {
        System.out.println("Received an update with id = [" + update.getUpdateId() + "]");
        updateService.processUpdate(update);
        return null;
    }

}
