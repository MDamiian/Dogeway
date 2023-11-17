package com.dogeway.dw.chat.message;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Table(name = "message")
@Entity(name = "Message")
@Setter
@Getter
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String senderId;
    private String recipientId;
    private String messageContent;
}

