package com.dogeway.dw.chat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chat")
public class ChatController {

    @Autowired
    private ChatMessageRepository chatMessageRepository;

    @PostMapping("/create")
    public ChatMessage createMessage(@RequestBody ChatMessage message) {
        return chatMessageRepository.save(message);
    }

}
