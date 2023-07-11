package client.commands;

import client.UDPclient;
import common.utility.CustomConsole;
import common.utility.requests.RemoveFirstCommandRequest;
import common.utility.requests.Request;
import common.utility.response.RemoveFirstCommandResponse;

import java.io.IOException;

/**
 * Класс, содержащий команду "remove_first". Удаляет первый элемент коллекции
 */
public class Remove_first extends AbstractCommand {
   private final UDPclient client;
    public Remove_first(UDPclient client){
        super("remove_first","убрать первый элемент из коллекции");
        this.client=client;
    }
    /**
     *Выполняет команду
     * @param argument аргумент, введённый пользователем
     * @return Успешность выполнения команды.
     */
@Override
    public boolean execute(String [] arguments){
    try {

        var response = (RemoveFirstCommandResponse) client.sendAndReceiveCommand(new RemoveFirstCommandRequest());

        CustomConsole.printLn("Продукт успешно удален.");
        return true;
}
    catch(IOException e){
        CustomConsole.printLn("Ошибка");
    }
    return false;
}}
