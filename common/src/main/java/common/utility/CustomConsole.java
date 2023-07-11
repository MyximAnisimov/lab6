package common.utility;

import common.exceptions.ScriptRecursionException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.NoSuchElementException;
import java.util.Scanner;
/**
 * Класс для работы с консолью
 */

public class CustomConsole {

    public static final String PS1 = "$ ";

    private ArrayDeque<String> array = new ArrayDeque<>();



    /**
     * Функция для исполнения скрипта
     * @param argument название файла, в котором находится скрипт
     * @return возвращает значение статуса команды
     */


    /**
     * Выводит принятый аргумент в стандартный поток вывода
     * @param toOut информация, которую необходимо вывести
     */
    public static void print(Object toOut){
        System.out.print(toOut);
    }

    /**
     * Выводит принятый аргумент на консоль
     * @param toOut информация, которую необходимо вывести
     */
    public static void printLn(Object toOut) {
        System.out.println(toOut);
    }

    /**
     * Выводит ошибку на консоль
     *@param toOut ошибка, которую необходимо вывести
     */
    public static void printError(Object toOut) {
        System.out.println("Ошибка: " + toOut);
    }

    /**
     * Функция для приёма ввода пользователя и исполнения команды (Если таковая существует)
     * @param userCommand ввод имени команды пользователем
     * @return статус команды
     */

}
