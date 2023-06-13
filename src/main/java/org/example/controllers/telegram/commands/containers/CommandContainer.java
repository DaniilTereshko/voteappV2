package org.example.controllers.telegram.commands.containers;

import org.example.controllers.telegram.commands.api.TelegramCommand;
import org.example.controllers.telegram.commands.classes.ArtistCommand;
import org.example.controllers.telegram.commands.classes.ArtistsCommand;
import org.example.controllers.telegram.commands.enums.CommandName;
import org.example.controllers.telegram.services.api.SendBotMessageService;
import org.example.services.factory.ArtistServiceFactory;

import java.util.HashMap;
import java.util.Map;

public class CommandContainer {
    private final Map<String, TelegramCommand> commands;

    public CommandContainer(SendBotMessageService sendBotMessageService) {
        commands = new HashMap<>();
        commands.put(CommandName.ARTISTS.getCommandName(), new ArtistsCommand(sendBotMessageService, ArtistServiceFactory.getInstance()));
        commands.put(CommandName.ARTIST.getCommandName(), new ArtistCommand(sendBotMessageService, ArtistServiceFactory.getInstance()));
    }

    public TelegramCommand retrieveCommand(String commandIdentifier) {
        return commands.get(commandIdentifier);
    }

}
