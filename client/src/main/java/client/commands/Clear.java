package client.commands;

import client.UDPclient;
import common.exceptions.WrongAmountOfElementsException;
import common.utility.CustomConsole;
import common.utility.requests.ClearCommandRequest;
import common.utility.requests.Request;
import common.utility.response.ClearCommandResponse;

import java.io.IOException;

/**
 * Класс, содержащий команду "clear". Очищает коллекцию
 */
public class Clear extends AbstractCommand {
    private final UDPclient client;
    public Clear(UDPclient client){
        super("clear","очистить коллекцию");
        this.client=client;
    }
    /**
     * Выполняет команду
     * @param commandStringArgument аргумент, введённый пользователем
     * @return Успешность выполнения команды.
     */
    @Override
    public boolean execute(String [] arguments){
        try {
            if (!arguments[1].isEmpty()) throw new WrongAmountOfElementsException();

            var response = (ClearCommandResponse) client.sendAndReceiveCommand(new ClearCommandRequest());

            CustomConsole.printLn("Коллекция очищена!");
            return true;
        } catch(IOException e) {
            CustomConsole.printError("Ошибка взаимодействия с сервером");
        } catch (WrongAmountOfElementsException exception) {
            CustomConsole.printError("Неправильное количество аргументов!");
            CustomConsole.printLn("Использование: '" + getName() + "'");
        }
        return false;
    }
    }

