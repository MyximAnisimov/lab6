package server.commands;

import common.exceptions.*;
import common.utility.requests.CountLessThanLocationCommandRequest;
import common.utility.requests.Request;
import common.utility.response.CountLessThanLocationCommandResponse;
import common.utility.response.Response;
import server.managers.CollectionManager;
import common.utility.CustomConsole;

import java.io.Serializable;

/**
 * Класс, содержащий команду "class_less_than_location". Подсчитывает количество элементов коллекции,
 * у которых поле name класса location меньше, чем введённое пользователем с клавиатуры
 */
public class Count_less_than_location extends AbstractCommand{
    private final CollectionManager collMan;
    public Count_less_than_location(CollectionManager collMan){
        super("count_less_than_location", "вывести количество элементов, значение поля location которых меньше заданного");
        this.collMan=collMan;
    }
    /**
     * Выполняет команду
     * @param commandStringArgument аргумент, введённый пользователем
     * @return Успешность выполнения команды.
     */
    @Override
    public Response execute(Request request){
        var req = (CountLessThanLocationCommandRequest) request;
        return new CountLessThanLocationCommandResponse(collMan.countElementsLessThanLocation(req.locationName), null);
}}
