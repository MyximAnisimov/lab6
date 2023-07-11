package client.commands;

import client.UDPclient;
import common.exceptions.WrongAmountOfElementsException;
import common.utility.CustomConsole;
import common.utility.requests.HeightSumCommandRequest;
import common.utility.requests.Request;
import common.utility.response.HeightSumCommandResponse;

import java.io.IOException;

/**
 * Класс, содержащий команду "HeightSum". Подсчитывает сумму всех значений полей height элементов коллекции
 */
public class HeightSum extends AbstractCommand {
    private final UDPclient client;
    public HeightSum(UDPclient client){
        super("height_sum","вывести сумму значений поля height для всех элементов коллекции");
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
            if (!arguments[1].isEmpty()) throw new WrongAmountOfElementsException();

            var response =  (HeightSumCommandResponse) client.sendAndReceiveCommand(new HeightSumCommandRequest());

            CustomConsole.printLn("Сумма цен всех продуктов: " + response.sum);
            return true;

        }  catch(IOException e) {
            CustomConsole.printError("Ошибка взаимодействия с сервером");
        }
        catch(WrongAmountOfElementsException e){
            CustomConsole.printError("Ошибка элементов");}
        return false;

}}
