package com.dogeway.dw.chat;

import com.chat.demo.model.ChatMessage;
import com.chat.demo.repository.ChatMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class ChatController {

    @Autowired
    private ChatMessageRepository chatMessageRepository;

    @GetMapping("/all")
    public List<ChatMessage> getAllMessages() {
        return chatMessageRepository.findAll();
    }

    @PostMapping("/create")
    public ChatMessage createMessage(@RequestBody ChatMessage message) {
        return chatMessageRepository.save(message);
    }

    @GetMapping("/all-response-entity")
    public ResponseEntity<List<ChatMessage>> getAllMessagesWithResponseEntity() {
        List<ChatMessage> messages = chatMessageRepository.findAll();
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }

    @PostMapping("/create-response-entity")
    public ResponseEntity<ChatMessage> createMessageWithResponseEntity(@RequestBody ChatMessage message) {
        ChatMessage savedMessage = chatMessageRepository.save(message);
        return new ResponseEntity<>(savedMessage, HttpStatus.CREATED);
    }
}
