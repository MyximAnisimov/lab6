package server.commands;

import common.exceptions.*;
import common.utility.requests.RemoveByIdCommandRequest;
import common.utility.requests.Request;
import common.utility.response.RemoveByIdCommandResponse;
import common.utility.response.Response;
import server.managers.CollectionManager;
import common.utility.CustomConsole;

import java.io.Serializable;

/**
 * Класс, содержащий команду "remove_by_id". Удаляет элемент коллекции по введённому пользователем id
 */
public class Remove_by_id extends AbstractCommand{
    private final CollectionManager collMan;
    public Remove_by_id(CollectionManager collMan){
        super("remove_by_id","удалить элемент из коллекции по его id");
        this.collMan=collMan;
    }
    /**
     *Выполняет команду
     * @param argument аргумент, введённый пользователем
     * @return Успешность выполнения команды.
     */
    @Override
    public Response execute(Request request){
            var req = (RemoveByIdCommandRequest) request;

            try {
                if (!collMan.checkExist(req.id)) {
                    return new RemoveByIdCommandResponse("Продукта с таким ID в коллекции нет!");
                }

                collMan.remove(req.id);
                return new RemoveByIdCommandResponse(null);
            } catch (Exception e) {
                return new RemoveByIdCommandResponse(e.toString());
            }
        }
    }

