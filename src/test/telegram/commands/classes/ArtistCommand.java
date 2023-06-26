package org.example.controllers.telegram.commands.classes;

import org.example.controllers.telegram.commands.api.TelegramCommand;
import org.example.controllers.telegram.services.api.SendBotMessageService;
import org.example.core.dto.ArtistDTO;
import org.example.services.api.IArtistService;
import org.telegram.telegrambots.meta.api.objects.Update;

public class ArtistCommand implements TelegramCommand {
    private final SendBotMessageService sendBotMessageService;

    public ArtistCommand(SendBotMessageService sendBotMessageService, IArtistService artistService) {
        this.sendBotMessageService = sendBotMessageService;
        this.artistService = artistService;
    }

    private final IArtistService artistService;

    @Override
    public void execute(Update update) {
        String text = update.getMessage().getText();
        String[] parts = text.split(" ");

        if (parts.length == 2) {
            int id;
            try {
                id = Integer.parseInt(parts[1]);
            } catch (NumberFormatException e) {
                sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), "Некорректный формат ID");
                return;
            }

            ArtistDTO artistDTO = artistService.get(id);
            if (artistDTO != null) {
                sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), artistDTO.toString());
            } else {
                sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), "Артист не найден");
            }
        } else {
            sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), "Некорректный формат команды. Используйте /artist {id}");
        }
    }

}
