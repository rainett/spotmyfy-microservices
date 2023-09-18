package io.rainett.bot.telegram.setup;

import io.rainett.bot.telegram.config.BotConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Objects;

@Component
@RequiredArgsConstructor
@Slf4j
public class WebhookSetup implements ApplicationListener<ApplicationReadyEvent> {

    private final BotConfig botConfig;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        setupWebhook();
    }

    private void setupWebhook() {
        log.info("Setting up webhook");
        RestTemplate template = new RestTemplate();
        String pathTemplate = "https://api.telegram.org/bot%s/setWebhook?url=%s";
        URI url = URI.create(String.format(pathTemplate, botConfig.getToken(), botConfig.getPath()));
        ResponseEntity<WebhookSetupDto> responseEntity = template.getForEntity(url, WebhookSetupDto.class);
        log.info("Webhook info: " + Objects.requireNonNull(responseEntity.getBody()).description());
    }
}
