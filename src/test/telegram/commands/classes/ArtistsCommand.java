package org.example.controllers.telegram.commands.classes;

import org.example.controllers.telegram.commands.api.TelegramCommand;
import org.example.controllers.telegram.services.api.SendBotMessageService;
import org.example.core.dto.ArtistDTO;
import org.example.services.api.IArtistService;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

public class ArtistsCommand implements TelegramCommand {
    private final SendBotMessageService sendBotMessageService;
    private final IArtistService artistService;

    public ArtistsCommand(SendBotMessageService sendBotMessageService, IArtistService artistService) {
        this.sendBotMessageService = sendBotMessageService;
        this.artistService = artistService;
    }


    @Override
    public void execute(Update update) {
        List<ArtistDTO> artists = artistService.get();
        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), artists.toString());

    }
}
