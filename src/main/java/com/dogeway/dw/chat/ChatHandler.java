package com.dogeway.dw.chat;

import com.dogeway.dw.chat.message.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ChatHandler extends TextWebSocketHandler {

    @Autowired
    private MessageService messageService;

    private final Map<String, WebSocketSession> activeSessions = new ConcurrentHashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // Almacena la sesión activa cuando se establece la conexión
        activeSessions.put(session.getId(), session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        // Elimina la sesión activa cuando se cierra la conexión
        activeSessions.remove(session.getId());
    }

    @Override
    protected void handleTextMessage(WebSocketSession senderSession, TextMessage message) throws Exception {
        String payload = message.getPayload();
        String[] parts = payload.split(":", 2);

        if (parts.length == 2) {
            String recipientId = parts[0].trim();
            String messageContent = parts[1].trim();

            messageService.saveMessage(senderSession.getId(), recipientId, messageContent);

            WebSocketSession recipientSession = activeSessions.get(recipientId);
            if (recipientSession != null && recipientSession.isOpen()) {
                recipientSession.sendMessage(new TextMessage("From " + senderSession.getId() + ": " + messageContent));
            } else {
                // El destinatario no está conectado, el mensaje se guardó en la base de datos y se enviará cuando se conecte.
            }
        } else {
            // Maneja el formato de mensaje inválido.
            senderSession.sendMessage(new TextMessage("Invalid message format. Use 'recipientId: messageContent'."));
        }
    }
}
