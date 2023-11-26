package com.dogeway.dw.chat;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/chat")
public class ChatController {

    @Autowired
    private ChatMessageRepository chatMessageRepository;

    @PostMapping("/createmessage")
    public ResponseEntity<ChatMessage> createMessage(@RequestBody ChatMessageDTO message) {
        ChatMessage chatMessage = chatMessageRepository.save(new ChatMessage(message));
        return ResponseEntity.ok(chatMessage);
    }

    @GetMapping("/getmessage")
    public ResponseEntity<List<ChatMessage>> getMessage(@RequestParam Long usuarioActual,
                                                           @RequestParam Long usuarioDestino){

        List<ChatMessage> listOne = chatMessageRepository.getAllBySenderAndSendto(usuarioActual, usuarioDestino);
        List<ChatMessage> listTwo = chatMessageRepository.getAllBySenderAndSendto(usuarioDestino, usuarioActual);


        List<ChatMessage> messages = Stream.concat(listOne.stream(), listTwo.stream()).collect(Collectors.toList());
        return ResponseEntity.ok(messages.stream().sorted(Comparator.comparing(ChatMessage::getLocalDateTime)).collect(Collectors.toList()));
    }
}
