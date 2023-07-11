package server.commands;

import common.collections.Person;
import common.exceptions.*;
import common.utility.requests.Request;
import common.utility.response.Response;
import common.utility.response.ShowCommandResponse;
import server.managers.CollectionManager;
import common.utility.CustomConsole;

import java.io.Serializable;

/**
 * Класс, содержащий команду "show". Вывести на экран все коллекции
 */
public class Show extends  AbstractCommand {
    private final CollectionManager collMan;
    public Show(CollectionManager collMan){
        super("show","вывести все элементы коллекции");
        this.collMan=collMan;
    }
    /**
     *Выполняет команду
     * @param argument аргумент, введённый пользователем
     * @return Успешность выполнения команды.
     */
    @Override
    public Response execute(Request request)  {
        try {
            return new ShowCommandResponse(collMan.sorted(), null);
        } catch (Exception e) {
            return new ShowCommandResponse(null, e.toString());
        }
    }
}
