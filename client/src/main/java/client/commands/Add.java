package client.commands;

import client.UDPclient;
import common.exceptions.*;
import common.utility.PersonAdder;
import common.utility.requests.AddCommandRequest;
import common.utility.requests.Request;
import common.utility.response.AddCommandResponse;

import java.io.IOException;
import java.util.Scanner;

/**
 * Класс, содержащий команду "add". Добавляет новый элемент в коллекцию
 */
public class Add extends AbstractCommand {
    private final UDPclient udPclient;
    private Scanner sc = new Scanner(System.in);
    public Add(UDPclient udPclient){
        super("add ","Добавление нового элемента в коллекцию");
        this.udPclient=udPclient;
    }

    /**
     * Функция. Добавляет новый элемент в коллекцию
     * @param commandStringArgument аргумент, введённый пользователем
     * @return Успешность выполнения команды.
     */
    @Override
    public boolean execute(String [] arguments){
         try{   Scanner sc=new Scanner(System.in);
        var newPerson = new PersonAdder(sc).build();
        var response = (AddCommandResponse) udPclient.sendAndReceiveCommand(new AddCommandRequest(newPerson));
            }catch(IncorrectInputInScriptException ignored){
            }
            catch(OutOfLimitsException ignored){}
            catch(WrongDateFormatException ignored){}
            catch(UnknownCountryException ignored){}
         catch(IOException ignored){}
        return true;
    }}
