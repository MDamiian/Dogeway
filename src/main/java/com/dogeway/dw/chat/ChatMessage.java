package com.dogeway.dw.chat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.CurrentTimestamp;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Data
@Table(name="messages")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idmessage;
    private Long sender;
    private Long sendto;
    private String message;
    @CreationTimestamp
    private LocalDateTime localDateTime;

    public ChatMessage(ChatMessageDTO chatMessageDTO){
        this.sender = chatMessageDTO.sender();
        this.sendto = chatMessageDTO.sendto();
        this.message = chatMessageDTO.message();
    }
}
