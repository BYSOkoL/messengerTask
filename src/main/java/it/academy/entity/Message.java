package it.academy.entity;

import java.time.LocalDateTime;

public class Message {
    private LocalDateTime timestamp;
    private String fromUser;
    private String toUser;
    private String text;

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getFromUser() {
        return fromUser;
    }

    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }

    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private LocalDateTime timestamp;
        private String fromUser;
        private String toUser;
        private String text;

        public Builder timestamp(LocalDateTime timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public Builder fromUser(String fromUser) {
            this.fromUser = fromUser;
            return this;
        }

        public Builder toUser(String toUser) {
            this.toUser = toUser;
            return this;
        }

        public Builder text(String text) {
            this.text = text;
            return this;
        }

        public Message build() {
            Message message = new Message();
            message.timestamp = this.timestamp;
            message.fromUser = this.fromUser;
            message.toUser = this.toUser;
            message.text = this.text;
            return message;
        }
    }
}