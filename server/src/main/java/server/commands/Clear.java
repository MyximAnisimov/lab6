package server.commands;

import common.exceptions.*;
import common.utility.requests.Request;
import common.utility.response.ClearCommandResponse;
import common.utility.response.Response;
import server.managers.CollectionManager;
import common.utility.CustomConsole;

import java.io.Serializable;

/**
 * Класс, содержащий команду "clear". Очищает коллекцию
 */
public class Clear extends AbstractCommand{
    private final CollectionManager collMan;

    public Clear(CollectionManager collMan) {
        super("clear", "очистить коллекцию");
        this.collMan=collMan;
    }

    /**
     * Выполняет команду
     * @return Успешность выполнения команды.
     */
    @Override
    public Response execute(Request request) {
        try {
            collMan.clear();
            return new ClearCommandResponse(null);
        } catch (Exception e) {
            return new ClearCommandResponse(e.toString());
        }
    }
    }

