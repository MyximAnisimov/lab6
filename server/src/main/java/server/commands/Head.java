package server.commands;

import common.utility.requests.Request;
import common.utility.response.HeadCommandResponse;
import common.utility.response.Response;
import server.managers.CollectionManager;

/**
 * Класс, содержащий команду "head". Выводит первый элемент коллекции
 */
public class Head extends AbstractCommand {
    private final CollectionManager collMan;

    public Head(CollectionManager collMan) {
        super("head", "вывести первый элемент коллекции");
        this.collMan= collMan;
    }

    /**
     * Выполняет команду
     * @return Успешность выполнения команды.
     */
    @Override
    public Response execute(Request request) {
        try {
            return new HeadCommandResponse(collMan.first(), null);
        } catch (Exception e) {
            return new HeadCommandResponse(null, e.toString());
        }
    }
    }

