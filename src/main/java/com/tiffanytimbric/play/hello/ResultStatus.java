package com.tiffanytimbric.play.hello;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Nullable;

public enum ResultStatus {
    Success("Success"), Failure("Failure");

    private final StringBuilder message = new StringBuilder();

    public ResultStatus addMessage(
            @Nullable final String newMessage
    ) {
        if (StringUtils.isBlank(newMessage)) {
            return this;
        }

        if (!message.toString().contains(":")) {
            message.append(": ");
        }
        else {
            message.append(" ");
        }

        message.append(newMessage);

        return this;
    }

    public String getMessage() {
        return message.toString();
    }

    ResultStatus(
            @Nullable final String... messages
    ) {
        if (ArrayUtils.isEmpty(messages)) {
            return;
        }

        this.message.append(
                String.join(" ", messages)
        );
    }
}
