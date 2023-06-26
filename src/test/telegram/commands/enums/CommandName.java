package org.example.controllers.telegram.commands.enums;

public enum CommandName {
    ARTISTS("/artists"),
    ARTIST("/artist");
    private final String commandName;

    CommandName(String commandName) {
        this.commandName = commandName;
    }

    public String getCommandName() {
        return commandName;
    }
}
