package client.commands;

import client.UDPclient;
import common.exceptions.*;
import common.utility.CustomConsole;
import common.utility.PersonAdder;
import common.utility.requests.Request;
import common.utility.requests.UpdateByIdCommandRequest;
import common.utility.response.UpdateByIdCommandResponse;

import java.io.IOException;
import java.util.Scanner;
/**
 * Класс, содержащий команду "update_id". Позволяет обновить элемент коллекции по введённому пользователем id
 */
public class Update_by_id extends AbstractCommand {
    private final UDPclient client;
    public Update_by_id(UDPclient client){
        super("update_by_id","изменение элемента коллекции по его id");
        this.client=client;
    }
    /**
     * Выполняет команду
     * @param argument аргумент, введённый пользователем
     * @return Успешность выполнения команды.
     */
    @Override
    public boolean execute(String [] arguments){
        try {
            Scanner sc = new Scanner (System.in);
            if (arguments[1].isEmpty()) throw new WrongAmountOfElementsException();

            var id = Integer.parseInt(arguments[1]);

            CustomConsole.printLn("* Введите данные обновленного продукта:");
            var updatedProduct = (new PersonAdder(sc)).build();

            var response =(UpdateByIdCommandResponse) client.sendAndReceiveCommand(new UpdateByIdCommandRequest(id, updatedProduct));

            CustomConsole.printLn("Продукт успешно обновлен.");
            return true;

        }
        catch(IOException e) {
            CustomConsole.printError("Ошибка взаимодействия с сервером");
        }
        catch (WrongAmountOfElementsException exception) {
            CustomConsole.printError("Неправильное количество аргументов!");
            CustomConsole.printLn("Использование: '" + getName() + "'");
        }catch (NumberFormatException exception) {
            CustomConsole.printError("ID должен быть представлен числом!");
        }catch (IncorrectInputInScriptException ignored) {}
        catch(OutOfLimitsException ignored){}
        catch(WrongDateFormatException ignored){}
        catch(UnknownCountryException ignored){}
        return false;
    }
}
