package server.commands;

import common.exceptions.*;
import common.utility.CustomConsole;
import common.utility.requests.Request;
import common.utility.response.HelpCommandResponse;
import common.utility.response.Response;
import server.managers.CommandManager;

import java.io.Serializable;

/**
 * Класс, содержащий команду "help". Выводит справку по командам
 */
public class Help extends AbstractCommand {
    private final CommandManager commandManager;

    public Help(CommandManager commandManager) {
        super("help", "вывести справку по доступным командам");
        this.commandManager = commandManager;
    }

    /**
     * Выполняет команду
     * @return Успешность выполнения команды.
     */
    @Override
    public Response execute(Request request) {
        var helpMessage = new StringBuilder();

        commandManager.getCommands().values().forEach(command -> {
            helpMessage.append(" %-35s%-1s%n".formatted(command.getName(), command.getDescription()));
        });

        return new HelpCommandResponse(helpMessage.toString(), null);
    }
}
