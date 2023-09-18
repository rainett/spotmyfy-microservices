package io.rainett.bot.telegram.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;

@Configuration
@RequiredArgsConstructor
public class WebhookConfig {

    private final io.rainett.bot.telegram.config.BotConfig botConfig;

    @Bean
    public SetWebhook setWebhook() {
        return new SetWebhook(botConfig.getPath());
    }

}
