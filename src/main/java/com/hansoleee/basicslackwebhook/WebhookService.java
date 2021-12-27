package com.hansoleee.basicslackwebhook;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class WebhookService {

    public Map<String, Object> sendTestWebhook(SlackMessage slackMessage) {
        if (slackMessage.getText().contains("fuck")) {
            throw new ContainsBadWordException("text[" + slackMessage.getText() + "] contains bad words");
        }

        Map<String, Object> request = new HashMap<>();
        request.put("username", slackMessage.getUsername());
        request.put("text", slackMessage.getText());
        return request;
    }
}
