package org.example.controllers.telegram;

import org.example.controllers.telegram.commands.containers.CommandContainer;
import org.example.controllers.telegram.services.classes.SendBotMessageServiceImpl;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

public class Bot extends TelegramLongPollingBot {
    private static Bot bot = new Bot();
    public static String COMMAND_PREFIX = "/";
    private final CommandContainer commandContainer;


    private Bot() {
        super();
        this.commandContainer = new CommandContainer(new SendBotMessageServiceImpl(this));
    }

    public static Bot getInstance() {
        return bot;
    }

    @Override
    public String getBotUsername() {
        return "TestOurChat_bot";
    }

    @Override
    public String getBotToken() {
        return "6196456901:AAEZWFeJmc5rlsILBxa4iv0-RyQuog-_Csc";
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String message = update.getMessage().getText().trim();
            if (message.startsWith(COMMAND_PREFIX)) {
                String commandIdentifier = message.split(" ")[0].toLowerCase();

                commandContainer.retrieveCommand(commandIdentifier).execute(update);
            } else {

            }
        }
    }
}
