package com.example.api.entity;

import java.util.Objects;
import java.util.Optional;

public class MessageEntity {

    private final Long id;
    private final String content;
    private final String addressee;

    private MessageEntity(Long id, String content, String addressee) {
        this.id = id;
        this.content = content;
        this.addressee = addressee;
    }

    public Optional<Long> getId() {
        return Optional.ofNullable(this.id);
    }

    public String getContent() {
        return this.content;
    }

    public String getAddressee() {
        return this.addressee;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Long id;
        private String content;
        private String addressee;

        private Builder() {
            // intentionally empty
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder content(String content) {
            this.content = content;
            return this;
        }

        public Builder addressee(String addressee) {
            this.addressee = addressee;
            return this;
        }

        public MessageEntity build() {
            Objects.requireNonNull(content, "Message content cannot be null");
            Objects.requireNonNull(addressee, "Message addressee cannot be null");
            return new MessageEntity(this.id, this.content, this.addressee);
        }
    }
}
