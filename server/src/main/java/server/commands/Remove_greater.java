package server.commands;

import common.collections.Person;
import common.exceptions.*;
import common.utility.ProductComparator;
import common.utility.requests.RemoveGreaterCommandRequest;
import common.utility.requests.Request;
import common.utility.response.RemoveGreaterCommandResponse;
import common.utility.response.Response;
import server.managers.CollectionManager;
import server.managers.PersonAdder;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Класс, содержащий команду "remove_greater". Удаляет элементы коллекции, значение поля height которых превышает введённое пользователем число
 */
public class Remove_greater extends AbstractCommand {
    private final CollectionManager collMan;
    public Remove_greater(CollectionManager collMan){
        super("remove_greater","убрать элементы коллекции, превышающий заданный");
        this.collMan=collMan;
    }    /**
     *Выполняет команду
     * @param argument аргумент, введённый пользователем
     * @return Успешность выполнения команды.
     */
    @Override
    public Response execute(Request request){
        var req = (RemoveGreaterCommandRequest) request;
        try {
            return new RemoveGreaterCommandResponse(removeByHeight(req.height), null);
        } catch (Exception e) {
            return new RemoveGreaterCommandResponse(null, e.toString());
        }
    }

    private List<Person> removeByHeight(Integer height) {
        return collMan.get().stream()
                .filter(person -> (person.getHeight().equals(height)))
                .sorted(new ProductComparator())
                .collect(Collectors.toList());
    }
}
