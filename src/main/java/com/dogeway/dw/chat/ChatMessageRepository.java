package com.dogeway.dw.chat;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {

    List<ChatMessage> getAllBySenderAndSendto(Long usuarioActual, Long usuarioDestino);
}
