package server.commands;

import common.exceptions.*;
import common.utility.requests.RemoveFirstCommandRequest;
import common.utility.requests.Request;
import common.utility.response.HeadCommandResponse;
import common.utility.response.RemoveFirstCommandResponse;
import common.utility.response.Response;
import server.managers.CollectionManager;
import common.utility.CustomConsole;

import java.io.Serializable;

/**
 * Класс, содержащий команду "remove_first". Удаляет первый элемент коллекции
 */
public class Remove_first extends AbstractCommand{
    private final CollectionManager collMan;
    public Remove_first(CollectionManager collMan){
        super("remove_first","убрать первый элемент из коллекции");
        this.collMan=collMan;
    }
    /**
     *Выполняет команду
     * @param argument аргумент, введённый пользователем
     * @return Успешность выполнения команды.
     */
@Override
    public Response execute(Request request){
    try {

    collMan.removeFirst();
        return new RemoveFirstCommandResponse(null);
    } catch (Exception e) {
        return new HeadCommandResponse(null, e.toString());
    }
}}
