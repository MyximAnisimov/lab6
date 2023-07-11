package client.commands;

import client.UDPclient;
import common.exceptions.WrongAmountOfElementsException;
import common.utility.CustomConsole;
import common.utility.requests.InfoCommandRequest;
import common.utility.response.InfoCommandResponse;

import java.io.IOException;
import java.io.Serializable;

/**
 * Класс, содержащий команду "info". Выводит информацию о коллекции
 */

public class Info extends AbstractCommand {
    private final UDPclient client;
public Info(UDPclient client){
    super("info","вывод информации о коллекции");
    this.client = client;
}
    /**
     *Выполняет команду
     *
     * @return Успешное выполнение команды
     */
@Override
public boolean execute(String [] arguments){
    try{
        if(!arguments[1].isEmpty()) {
            CustomConsole.printError("Неправильное количество аргументов");
        return false;}
            var response = (InfoCommandResponse) client.sendAndReceiveCommand(new InfoCommandRequest());
            CustomConsole.printLn(response.infoMessage);
            return true;
        } catch(IOException e) {
            CustomConsole.printError("Ошибка взаимодействия с сервером");
        }
        return false;
}
}
