package client.commands;

import common.exceptions.WrongAmountOfElementsException;
import common.utility.CustomConsole;

import java.io.Serializable;

/**
 * Класс, содержащий команду "help". Выводит справку по командам
 */
public class Help extends AbstractCommand {
    public Help(){
        super("help"," вывести все доступные команды");
    }
    /**
     *Выполняет команду
     * @param argument аргумент, введённый пользователем
     * @return Успешность выполнения команды.
     */
    @Override
    public boolean execute(String [] argument){
        try {
            if (!argument[1].isEmpty()) throw new WrongAmountOfElementsException();
        } catch (WrongAmountOfElementsException exception) {
            CustomConsole.printLn("Использование: '" + getName() + "'");
        }
        return false;
    }

}
