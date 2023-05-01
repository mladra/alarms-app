package com.example.message.control;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MESSAGES")
class MessageEntityJPA {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String content;

    @Column
    private String addressee;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    String getContent() {
        return content;
    }

    void setContent(String content) {
        this.content = content;
    }

    String getAddressee() {
        return addressee;
    }

    void setAddressee(String addressee) {
        this.addressee = addressee;
    }
}
