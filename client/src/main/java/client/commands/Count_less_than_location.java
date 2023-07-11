package client.commands;

import client.UDPclient;
import common.exceptions.WrongAmountOfElementsException;
import common.utility.CustomConsole;
import common.utility.requests.CountLessThanLocationCommandRequest;
import common.utility.requests.Request;
import common.utility.response.CountLessThanLocationCommandResponse;

import java.io.IOException;

/**
 * Класс, содержащий команду "class_less_than_location". Подсчитывает количество элементов коллекции,
 * у которых поле name класса location меньше, чем введённое пользователем с клавиатуры
 */
public class Count_less_than_location extends AbstractCommand{
    private final UDPclient client;
    public Count_less_than_location(UDPclient client){
        super("сount_less_than_location", "вывести количество элементов, значение поля location которых меньше заданного");
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
String locationName = arguments[1];
            var response = (CountLessThanLocationCommandResponse) client.sendAndReceiveCommand(new CountLessThanLocationCommandRequest(locationName));

            CustomConsole.printLn("Количество элементов: " + response.sum);
            return true;

        }  catch(IOException e) {
            CustomConsole.printError("Ошибка взаимодействия с сервером");
        }
        catch(WrongAmountOfElementsException e){
            CustomConsole.printError("Ошибка в подсчёте количества элементов");}
        return false;

    }}
