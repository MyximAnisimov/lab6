package server.commands;

import common.utility.requests.AddCommandRequest;
import common.utility.requests.Request;
import common.utility.response.AddCommandResponse;
import common.utility.response.Response;
import server.managers.CollectionManager;
import server.managers.PersonAdder;

/**
 * Класс, содержащий команду "add". Добавляет новый элемент в коллекцию
 */
public class Add extends AbstractCommand {
    private final CollectionManager collMan;
    public Add( CollectionManager collMan){
        super("add ","Добавление нового элемента в коллекцию");
        this.collMan=collMan;
    }


        /**
         * Выполняет команду
         * @return Успешность выполнения команды.
         */
        @Override
        public Response execute(Request request) {
            var req = (AddCommandRequest) request;
            try {
                if (!req.person.validate()) {
                    return new AddCommandResponse(-1, "Поля продукта не валидны! Продукт не добавлен!");
                }
                var newId = collMan.add(req.person);
                return new AddCommandResponse(newId, null);
            } catch (Exception e) {
                return new AddCommandResponse(-1, e.toString());
            }
        }}
