package server.commands;

import common.exceptions.*;
import common.utility.requests.Request;
import common.utility.response.InfoCommandResponse;
import common.utility.response.Response;
import server.managers.CollectionManager;
import common.utility.CustomConsole;

import java.io.Serializable;

/**
 * Класс, содержащий команду "info". Выводит информацию о коллекции
 */

public class Info extends AbstractCommand{
    private final CollectionManager collMan;

    public Info(CollectionManager collMan) {
        super("info", "вывести информацию о коллекции");
        this.collMan=collMan;
    }

    /**
     * Выполняет команду
     * @return Успешность выполнения команды.
     */
    @Override
    public Response execute(Request request) {

        var message = "Сведения о коллекции:\n" +
                " Тип: " + collMan.type() + "\n" +
                " Количество элементов: " + collMan.size() + "\n" +
                " Дата создания коллекции: "+collMan.getCreationDate();
        return new InfoCommandResponse(message, null);
    }

}
