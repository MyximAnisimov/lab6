package client.commands;

import client.UDPclient;
import common.exceptions.*;
import common.utility.CustomConsole;
import common.utility.requests.RemoveGreaterCommandRequest;
import common.utility.requests.Request;
import common.utility.response.RemoveGreaterCommandResponse;

import java.io.IOException;

/**
 * Класс, содержащий команду "remove_greater". Удаляет элементы коллекции, значение поля height которых превышает введённое пользователем число
 */
public class Remove_greater extends AbstractCommand {
    private final UDPclient client;
    public Remove_greater(UDPclient client){
        super("remove_greater","убрать элементы коллекции, превышающий заданный");
        this.client=client;
    }    /**
     *Выполняет команду
     * @param argument аргумент, введённый пользователем
     * @return Успешность выполнения команды.
     */
    @Override
    public boolean execute(String [] arguments){
        try {
            if (arguments[1].isEmpty()) throw new WrongAmountOfElementsException();
            var id = Integer.parseInt(arguments[1]);

            var response = (RemoveGreaterCommandResponse) client.sendAndReceiveCommand(new RemoveGreaterCommandRequest(id));

            CustomConsole.printLn("Продукт успешно удален.");
            return true;
        }  catch(IOException e) {
            CustomConsole.printError("Ошибка взаимодействия с сервером");
        }catch (WrongAmountOfElementsException exception) {
            CustomConsole.printError("Неправильное количество аргументов!");
            CustomConsole.printLn("Использование: '" + getName() + "'");
        } catch (NumberFormatException exception) {
            CustomConsole.printError("ID должен быть представлен числом!");
        }
        return false;
    }
}
