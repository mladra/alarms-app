package com.example.message.control;

import com.example.message.entity.MessageEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MessageRepository {

    private final MessageJPARepository messageJPARepository;

    @Autowired
    public MessageRepository(MessageJPARepository messageJPARepository) {
        this.messageJPARepository = messageJPARepository;
    }

    public MessageEntity save(MessageEntity message) {
        MessageEntityJPA messageEntityJPA = new MessageEntityJPA();
        messageEntityJPA.setContent(message.getContent());
        messageEntityJPA.setAddressee(message.getAddressee());

        MessageEntityJPA savedJPAEntity = messageJPARepository.save(messageEntityJPA);

        return MessageEntity.builder()
                .id(savedJPAEntity.getId())
                .content(savedJPAEntity.getContent())
                .addressee(savedJPAEntity.getAddressee())
                .build();
    }
}
