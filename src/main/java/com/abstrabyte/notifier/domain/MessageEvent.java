package com.abstrabyte.notifier.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MessageEvent {
    public String topic;
    public String message;
}

