package client.commands;

import client.UDPclient;
import common.utility.CustomConsole;
import common.utility.requests.PrintFieldDescendingPassportIdCommandRequest;
import common.utility.requests.Request;
import common.utility.response.PrintFieldDescendingPassportIdCommandResponse;

import java.io.IOException;

/**
 * Класс, содержащий команду "print_field_descending_passport_id". Сортирует элементы коллекции по ID паспорта в порядке убывания
 */
public class Print_field_descending_passport_id extends AbstractCommand {
    private final UDPclient client;
    public Print_field_descending_passport_id(UDPclient client){
        super("print_field_descending_passport_id","вывод значения поля passportID всех элементов в порядке убывания");
        this.client = client;
    }
    /**
     *Выполняет команду
     * @param argument аргумент, введённый пользователем
     * @return Успешность выполнения команды.
     */
    @Override
    public boolean execute(String [] arguments){
        try {
            var response =(PrintFieldDescendingPassportIdCommandResponse) client.sendAndReceiveCommand(new PrintFieldDescendingPassportIdCommandRequest());
            CustomConsole.printLn(response.person);
            return true;
        }
        catch(IOException e){
            System.out.println("Ошибка IO в команде!");
        }
        return false;
    }}
