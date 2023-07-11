package server;

import common.utility.CustomConsole;
import common.utility.requests.Request;
import common.utility.response.Response;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.SerializationException;
import org.apache.commons.lang3.SerializationUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.*;

public abstract class UDPserver {
    private final InetSocketAddress addr;
    private final CommandExecutor commandExecutor;
    private Runnable afterHook;
    private final Logger logger = Main.logger;
    private boolean running = true;

    public UDPserver(InetSocketAddress addr, CommandExecutor commandExecutor) {
        this.addr = addr;
        this.commandExecutor = commandExecutor;
    }

    public InetSocketAddress getAddr() {
        return addr;
    }

    /**
     * Получает данные с клиента.
     * Возвращает пару из данных и адреса клиента
     */
    public abstract Pair<Byte[], SocketAddress> receiveData() throws IOException;

    /**
     * Отправляет данные клиенту
     */
    public abstract void sendData(byte[] data, SocketAddress addr) throws IOException;

    public abstract void connectToClient(SocketAddress addr) throws SocketException;

    public abstract void disconnectFromClient();
    public abstract void close();


    public void run(){
        System.out.println("Сервак запущен");
        while (running) {
            Pair<Byte[], SocketAddress> dataPair;
            try {
                dataPair = receiveData();
            } catch (Exception e) {
                CustomConsole.printLn("Ошибка получения данных");
                disconnectFromClient();
                continue;
            }

            var dataFromClient = dataPair.getKey();
            var clientAddr = dataPair.getValue();

            try {
                connectToClient(clientAddr);
                CustomConsole.printLn("Соединено с " + clientAddr);
            } catch (Exception e) {
                CustomConsole.printLn("Ошибка соединения с клиентом"+ e.toString());
            }

            Request request;
            try {
                request = SerializationUtils.deserialize(ArrayUtils.toPrimitive(dataFromClient));
                CustomConsole.printLn("Обработка " + request + " из " + clientAddr);
            }
            catch (SerializationException e){
                CustomConsole.printError("Невозможно десериализовать объект");
                disconnectFromClient();
                continue;
            }
            Response response = null;
            try {
                response = commandExecutor.handle(request);
                if(afterHook !=null) afterHook.run();
            } catch (Exception e) {
                CustomConsole.printError("Ошибка выполнения команды");
            }

            var data = SerializationUtils.serialize(response);
            CustomConsole.printLn("Ответ: " + response);

            try {
                sendData(data, clientAddr);
               CustomConsole.printLn("Отправлен ответ клиенту " + clientAddr);
            } catch (Exception e) {
                CustomConsole.printLn("Ошибка ввода-вывода");
            }

            disconnectFromClient();
            CustomConsole.printLn("Отключение от клиента " + clientAddr);
        }
        close();}
    public void setAfterHook(Runnable afterHook) {
        this.afterHook = afterHook;
    }

    public void stop() {
        running = false;
    }

}
