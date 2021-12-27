package com.hansoleee.basicslackwebhook;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/v1/slack")
@RequiredArgsConstructor
public class WebhookController {

    public static final String TEST_WEBHOOK_URL = "https://hooks.slack.com/services/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";

    private final RestTemplate restTemplate;
    private final WebhookService webhookService;

    @PostMapping("/test-webhook")
    public String sendTestWebhook(@RequestBody SlackMessage slackMessage) {
        Map<String, Object> request = webhookService.sendTestWebhook(slackMessage);
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(request);
        ResponseEntity<String> exchange = restTemplate.exchange(TEST_WEBHOOK_URL, HttpMethod.POST, entity, String.class);
        log.info("statusCode: {}, body: {}", exchange.getStatusCode(), exchange.getBody());
        return "OK";
    }
}
