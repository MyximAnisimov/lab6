package server;

import common.utility.CustomConsole;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import server.commands.*;
import server.managers.CollectionManager;
import server.managers.CommandManager;
import server.managers.FileManager;
import server.managers.PersonAdder;
import common.utility.Commands;

import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Main {
    public static final int port = 1233;
    public static Logger logger = LogManager.getLogger("ServerLogger");

    public static void main(String[] args) {
        String fileName="../data/" + System.getenv("VAR");
        var dumpManager = new FileManager("data/JSON.json");
        var repository = new CollectionManager(dumpManager);
        if(!repository.validateAll()) {
            CustomConsole.printError("Невалидные продукты в загруженном файле!");
            System.exit(2);
        }

        Runtime.getRuntime().addShutdownHook(new Thread(repository::save));

        var commandManager = new CommandManager() {{
            register("help", new Help(this));
            register("info", new Info(repository));
            register("show", new Show(repository));
            register("add", new Add(repository));
            register("remove_by_id", new Remove_by_id(repository));
            register("clear", new Clear(repository));
            register("head", new Head(repository));
            register("count_less_than_location", new Count_less_than_location(repository));
                register("heightSum", new HeightSum(repository));
            register("print_field_descending_passport_id", new Print_field_descending_passport_id(repository));
            register("remove_first", new Remove_first(repository));
            register("remove_greater", new Remove_greater(repository));
            register("update_by_id", new Update_by_id(repository));
        }};

        try {
            var server = new UDPDatagramServer(InetAddress.getLocalHost(), port, new CommandExecutor(commandManager));
            server.setAfterHook(repository::save);
            server.run();
        } catch (SocketException e) {
            CustomConsole.printError("Случилась ошибка сокета");
        } catch (UnknownHostException e) {
            CustomConsole.printError("Неизвестный хост");
        }
    }
}