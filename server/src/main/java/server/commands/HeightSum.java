package server.commands;

import common.collections.Person;
import common.exceptions.*;
import common.utility.requests.Request;
import common.utility.response.HeightSumCommandResponse;
import common.utility.response.Response;
import server.managers.CollectionManager;
import common.utility.CustomConsole;

import java.io.Serializable;

/**
 * Класс, содержащий команду "HeightSum". Подсчитывает сумму всех значений полей height элементов коллекции
 */
public class HeightSum extends AbstractCommand{
    private final CollectionManager collMan;
    public HeightSum(CollectionManager collMan){
        super("height_sum","вывести сумму значений поля height для всех элементов коллекции");
        this.collMan=collMan;
    }

    /**
     * Выполняет команду
     * @return Успешность выполнения команды.
     */
    @Override
    public Response execute(Request request) {
        try {
            return new HeightSumCommandResponse(getSumOfHeight(), null);
        } catch (Exception e) {
            return new HeightSumCommandResponse(-1, e.toString());
        }
    }

    private Integer getSumOfHeight() {
        return collMan.get().stream()
                .map(Person::getHeight)
                .mapToInt(Integer::intValue)
                .sum();
    }}

