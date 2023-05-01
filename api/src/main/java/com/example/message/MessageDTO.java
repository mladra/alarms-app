package com.example.message;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;
import java.util.StringJoiner;

public class MessageDTO {

    private final String content;
    private final String addressee;

    @JsonCreator
    public MessageDTO(@JsonProperty("content") String content, @JsonProperty("addressee") String addressee) {
        this.content = content;
        this.addressee = addressee;
    }

    public String getContent() {
        return this.content;
    }

    public String getAddressee() {
        return this.addressee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MessageDTO that = (MessageDTO) o;
        return content.equals(that.content) && addressee.equals(that.addressee);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content, addressee);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", MessageDTO.class.getSimpleName() + "[", "]")
                .add("content='" + content + "'")
                .add("addressee='" + addressee + "'")
                .toString();
    }
}
