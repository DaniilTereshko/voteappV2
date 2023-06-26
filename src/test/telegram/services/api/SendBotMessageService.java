package org.example.controllers.telegram.services.api;

import org.telegram.telegrambots.meta.api.objects.polls.Poll;

public interface SendBotMessageService {
    void sendMessage(String chatId, String message);

}
