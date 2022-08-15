package com.example.demo.util;

import com.example.demo.model.RetryMqMessage;
import org.springframework.context.ApplicationEvent;

public class RequestEvent extends ApplicationEvent {

    private RetryMqMessage message;

    public RequestEvent(Object source, RetryMqMessage message) {
        super(source);
        this.message = message;
    }

    public RetryMqMessage getMessage() {
        return message;
    }

    public void setMessage(RetryMqMessage message) {
        this.message = message;
    }
}
