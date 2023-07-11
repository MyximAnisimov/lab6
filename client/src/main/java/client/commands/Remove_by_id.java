package client.commands;

import client.UDPclient;
import common.exceptions.*;
import common.utility.CustomConsole;
import common.utility.requests.RemoveByIdCommandRequest;
import common.utility.response.RemoveByIdCommandResponse;

import java.io.IOException;

/**
 * Класс, содержащий команду "remove_by_id". Удаляет элемент коллекции по введённому пользователем id
 */
public class Remove_by_id extends AbstractCommand {
    private final UDPclient client;
    public Remove_by_id(UDPclient client){
        super("remove_by_id","удалить элемент из коллекции по его id");
        this.client=client;
    }
    /**
     *Выполняет команду
     * @param argument аргумент, введённый пользователем
     * @return Успешность выполнения команды.
     */
    @Override
    public boolean execute(String [] arguments){
        try {
            if (arguments[1].isEmpty()) throw new WrongAmountOfElementsException();
            var id = Integer.parseInt(arguments[1]);

            var response = (RemoveByIdCommandResponse) client.sendAndReceiveCommand(new RemoveByIdCommandRequest(id));

            CustomConsole.printLn("Продукт успешно удален.");
            return true;
        }
        catch(IOException e) {
            CustomConsole.printError("Ошибка взаимодействия с сервером");
        }
        catch (WrongAmountOfElementsException exception) {
            CustomConsole.printError("Неправильное количество аргументов!");
            CustomConsole.printError("Использование: '" + getName() + "'");
        } catch (NumberFormatException exception) {
            CustomConsole.printError("ID должен быть представлен числом!");
        }

        return false;
    }
    }

