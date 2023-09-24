package main.command;

import java.util.Optional;

public class CommandProvider {
    private static CommandProvider instance = null;

    private CommandProvider() {
    }

    public static CommandProvider getInstance() {
        if (instance == null) {
            instance = new CommandProvider();
        }
        return instance;
    }

    public Optional<Command> getCommand(String commandName) {
        Optional<Command> command;
        if (commandName != null && !commandName.trim().isEmpty()) {
            try {
                CommandContainer currentCommand = CommandContainer.valueOf(commandName.toUpperCase());
                command = Optional.of(currentCommand.getCommand());
            } catch (IllegalArgumentException e) {
                command = Optional.empty();
            }
        } else {
            command = Optional.empty();
        }
        return command;

    }
}
