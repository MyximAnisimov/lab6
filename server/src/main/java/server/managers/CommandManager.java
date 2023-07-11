package server.managers;

import server.commands.Command;
import common.utility.CustomConsole;

import java.io.Serializable;
import java.util.*;

/**
 * Класс для создания и организации рабочих команд
 */
public class CommandManager {
    private final Map<String, Command> commands = new HashMap<>();

    public void register(String commandName, Command command) {
        commands.put(commandName, command);
    }

    /**
     * @return Словарь команд.
     */
    public Map<String, Command> getCommands() {
        return commands;
    }}
