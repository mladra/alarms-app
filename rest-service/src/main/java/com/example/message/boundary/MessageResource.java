package com.example.message.boundary;

import com.example.message.MessageDTO;
import com.example.message.control.MessageRepository;
import com.example.message.entity.MessageEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping("/message")
public class MessageResource {

    private final MessageRepository messageRepository;

    @Autowired
    public MessageResource(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @PutMapping
    public ResponseEntity<String> createMessage(@RequestBody MessageDTO message) {
        MessageEntity messageEntity = MessageEntity.builder()
                .content(message.getContent())
                .addressee(message.getAddressee())
                .build();
        MessageEntity savedEntity = messageRepository.save(messageEntity);
        return ResponseEntity.ok("Message successfully created with id: " + savedEntity.getId());
    }

}
