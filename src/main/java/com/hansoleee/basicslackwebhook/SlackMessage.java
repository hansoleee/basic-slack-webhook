package com.hansoleee.basicslackwebhook;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class SlackMessage {

    private String username;
    private String text;
}
