package client.commands;

import client.UDPclient;
import common.utility.CustomConsole;
import common.utility.requests.HeadCommandRequest;
import common.utility.requests.Request;
import common.utility.response.HeadCommandResponse;

import java.io.IOException;

/**
 * Класс, содержащий команду "head". Выводит первый элемент коллекции
 */
public class Head extends AbstractCommand {
    private final UDPclient client;
    public Head(UDPclient client){
super("head","вывод первого элемента коллекции");
        this.client=client;
    }
    /**
     *Выполняет команду
     * @param commandStringArgument аргумент, введённый пользователем
     * @return Успешность выполнения команды.
     */
    @Override
    public boolean execute(String [] arguments){
        try {
            var response =(HeadCommandResponse) client.sendAndReceiveCommand(new HeadCommandRequest());
            if (response.person == null) {
                CustomConsole.printLn("Коллекция пуста!");
                return true;
            }
            CustomConsole.printLn(response.person);
            return true;
}
        catch(IOException e){
            System.out.println("Ошибка IO в Head!");
        }
        return false;
    }}

