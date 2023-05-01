package com.example.api.boundary;

import com.example.api.SensorDataDTO;
import com.example.api.control.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<String> createMessage(@RequestBody SensorDataDTO message) {
        // TODO: mladra: FIXME
//        MessageEntity messageEntity = MessageEntity.builder()
//                .content(message.getContent())
//                .addressee(message.getAddressee())
//                .build();
//        MessageEntity savedEntity = messageRepository.save(messageEntity);
        return ResponseEntity.ok("Message successfully created with id: ");
    }

}
