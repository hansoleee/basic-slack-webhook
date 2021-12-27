package com.hansoleee.basicslackwebhook;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    public static final String TEST_WEBHOOK_URL = "https://hooks.slack.com/services/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";

    private final RestTemplate restTemplate;

    @ExceptionHandler({ContainsBadWordException.class})
    public ResponseEntity<String> handleIllegalStateException(Exception e) {
        errorLogging(e);
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(new HashMap<>() {{
            put("username", "webhookbot");
            put("text", e.getMessage());
        }});
        ResponseEntity<String> exchange = restTemplate.exchange(TEST_WEBHOOK_URL, HttpMethod.POST, entity, String.class);
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    private void errorLogging(Exception e) {
        log.error(e.getMessage(), e);
    }
}
