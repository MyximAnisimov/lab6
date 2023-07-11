package client.utility;

import client.UDPclient;
import client.commands.*;
import common.exceptions.ScriptRecursionException;
import common.utility.CustomConsole;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Runner {
    public enum ExitCode {
        OK,
        ERROR,
        EXIT,
    }

    private final UDPclient client;
    private final Map<String, Command> commands;

    private final Deque<String> scriptStack = new ArrayDeque<>();

    public Runner(UDPclient client) {
       Interrogator.setUserScanner(new Scanner(System.in));
        this.client = client;
        this.commands = new HashMap<>() {{
           put("add", new Add(client));
                                   put("head", new Head(client));
                                           put("remove_first", new Remove_first(client));
                                                  put("exit", new Exit());
                                                   put("heightSum", new HeightSum(client));
                                                           put("print_field_descending_passport_id", new Print_field_descending_passport_id(client));
                                                           put("remove_greater", new Remove_greater(client));
                                                           put("count_less_than_location", new Count_less_than_location(client));
                                                           put("execute_script", new Execute_script());
                                                           put("help", new Help());
                                                           put("clear", new Clear(client));
                                                                   put("remove_by_id", new Remove_by_id(client));
        put("info", new Info(client));
        put("show",new Show(client));
        put("update_by_id",new Update_by_id(client));}
        };
    }

    /**
     * Интерактивный режим
     */
    public void interactiveMode() {
        var userScanner = Interrogator.getUserScanner();
        try {
            ExitCode commandStatus;
            String[] userCommand = {"", ""};

            do {
                userCommand = (userScanner.nextLine().trim() + " ").split(" ", 2);
                userCommand[1] = userCommand[1].trim();
                commandStatus = launchCommand(userCommand);
            } while (commandStatus != ExitCode.EXIT);

        } catch (NoSuchElementException exception) {
            CustomConsole.printError("Пользовательский ввод не обнаружен!");
        } catch (IllegalStateException exception) {
            CustomConsole.printError("Непредвиденная ошибка!");
        }
    }

    /**
     * Режим для запуска скрипта.
     * @param argument Аргумент скрипта
     * @return Код завершения.
     */
    public ExitCode scriptMode(String argument) {
        String[] userCommand = {"", ""};
        ExitCode commandStatus;
        scriptStack.add(argument);
        if (!new File(argument).exists()) {
            argument = "../" + argument;
        }
        try (Scanner scriptScanner = new Scanner(new File(argument))) {
            if (!scriptScanner.hasNext()) throw new NoSuchElementException();
            Scanner tmpScanner = new Scanner(System.in);

            do {
                userCommand = (scriptScanner.nextLine().trim() + " ").split(" ", 2);
                userCommand[1] = userCommand[1].trim();
                while (scriptScanner.hasNextLine() && userCommand[0].isEmpty()) {
                    userCommand = (scriptScanner.nextLine().trim() + " ").split(" ", 2);
                    userCommand[1] = userCommand[1].trim();
                }
                if (userCommand[0].equals("execute_script")) {
                    for (String script : scriptStack) {
                        if (userCommand[1].equals(script)) throw new ScriptRecursionException();
                    }
                }
                commandStatus = launchCommand(userCommand);
            } while (commandStatus == ExitCode.OK && scriptScanner.hasNextLine());


            if (commandStatus == ExitCode.ERROR && !(userCommand[0].equals("execute_script") && !userCommand[1].isEmpty())) {
                CustomConsole.printError("Проверьте скрипт на корректность введенных данных!");
            }

            return commandStatus;

        } catch (FileNotFoundException exception) {
            CustomConsole.printError("Файл со скриптом не найден!");
        } catch (NoSuchElementException exception) {
            CustomConsole.printError("Файл со скриптом пуст!");
        } catch (ScriptRecursionException exception) {
            CustomConsole.printError("Скрипты не могут вызываться рекурсивно!");
        } catch (IllegalStateException exception) {
            CustomConsole.printError("Непредвиденная ошибка!");
            System.exit(0);
        } finally {
            scriptStack.remove(scriptStack.size() - 1);
        }
        return ExitCode.ERROR;
    }

    /**
     * Запускает команду.
     * @param userCommand Команда для запуска
     * @return Код завершения.
     */
    private ExitCode launchCommand(String[] userCommand) {
        if (userCommand[0].equals("")) return ExitCode.OK;
        var command = commands.get(userCommand[0]);

        if (command == null) {
            CustomConsole.printError("Команда '" + userCommand[0] + "' не найдена. Наберите 'help' для справки");
            return ExitCode.ERROR;
        }

        switch (userCommand[0]) {
            case "exit" -> {
                if (!commands.get("exit").execute(userCommand)) return ExitCode.ERROR;
                else return ExitCode.EXIT;
            }
            case "execute_script" -> {
                if (!commands.get("execute_script").execute(userCommand)) return ExitCode.ERROR;
                else return scriptMode(userCommand[1]);
            }
            default -> { if (!command.execute(userCommand)) return ExitCode.ERROR; }
        };

        return ExitCode.OK;
    }}