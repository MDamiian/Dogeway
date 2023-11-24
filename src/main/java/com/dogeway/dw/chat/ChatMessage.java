package com.dogeway.dw.chat;
import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name="messages")
public class ChatMessage implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String user;
    private String message;



    // Getters y Setters

    // Constructor por defecto y constructor con parámetros si es necesario
}
