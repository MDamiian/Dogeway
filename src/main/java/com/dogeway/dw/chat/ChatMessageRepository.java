package com.dogeway.dw.chat;
import com.chat.demo.model.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;



public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {

}
