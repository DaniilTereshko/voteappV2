package org.example.controllers.telegram.commands.api;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface TelegramCommand {
    void execute(Update update);
}
