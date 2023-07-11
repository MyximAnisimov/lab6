package server;

import common.utility.requests.Request;
import common.utility.response.Response;
import server.commands.Command;
import server.managers.CommandManager;

import java.util.HashMap;
import java.util.Map;

public class CommandExecutor {
private CommandManager commandManager;
    private final Map<String, Command> commands = new HashMap<>();
public CommandExecutor(CommandManager manager){
    this.commandManager= manager;
}
    public Response handle(Request request) {
        var command = commandManager.getCommands().get(request.getName());
        return command.execute(request);
}}

