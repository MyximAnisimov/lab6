package client;

import com.google.common.primitives.Bytes;
import common.utility.requests.Request;
import common.utility.response.Response;

import common.utility.CustomConsole;
import org.apache.commons.lang3.SerializationUtils;

import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.Arrays;

public class UDPclient {
    private final int PACKET_SIZE = 1024;
    private final int DATA_SIZE = PACKET_SIZE - 1;

    private final DatagramChannel client;
    private final InetSocketAddress addr;
    public UDPclient(InetAddress address, int port) throws IOException {
        this.addr = new InetSocketAddress(address, port);
        this.client = DatagramChannel.open().bind(null).connect(addr);
        this.client.configureBlocking(false);
        CustomConsole.printLn("DatagramChannel подключен к " + addr);
    }

    public Response sendAndReceiveCommand(Request request) throws IOException {
        var data = SerializationUtils.serialize(request);
        var responseBytes = sendAndReceiveData(data);

        Response response = SerializationUtils.deserialize(responseBytes);
        CustomConsole.printLn("Получен ответ от сервера:  " + response);
        return response;
    }

    private void sendData(byte[] data) throws IOException {
        byte[][] ret = new byte[(int)Math.ceil(data.length / (double)DATA_SIZE)][DATA_SIZE];

        int start = 0;
        for(int i = 0; i < ret.length; i++) {
            ret[i] = Arrays.copyOfRange(data, start, start + DATA_SIZE);
            start += DATA_SIZE;
        }

        CustomConsole.printLn("Отправляется " + ret.length + " чанков...");

        for(int i = 0; i < ret.length; i++) {
            var chunk = ret[i];
            if (i == ret.length - 1) {
                var lastChunk = Bytes.concat(chunk, new byte[]{1});
                client.send(ByteBuffer.wrap(lastChunk), addr);
                CustomConsole.printLn("Последний чанк размером " + lastChunk.length + " отправлен на сервер.");
            } else {
                var answer = Bytes.concat(chunk, new byte[]{0});
                client.send(ByteBuffer.wrap(answer), addr);
                CustomConsole.printLn("Чанк размером " + answer.length + " отправлен на сервер.");
            }
        }

        CustomConsole.printLn("Отправка данных завершена.");
    }

    private byte[] receiveData() throws IOException {
        var received = false;
        var result = new byte[0];

        while(!received) {
            var data = receiveData(PACKET_SIZE);

            if (data[data.length - 1] == 1) {
                received = true;
                CustomConsole.printLn("Получение данных окончено");
            }
            result = Bytes.concat(result, Arrays.copyOf(data, data.length - 1));
        }

        return result;
    }

    private byte[] receiveData(int bufferSize) throws IOException {
        var buffer = ByteBuffer.allocate(bufferSize);
        SocketAddress address = null;
        while(address == null) {
            address = client.receive(buffer);
        }
        return buffer.array();
    }

    private byte[] sendAndReceiveData(byte[] data) throws IOException {
        sendData(data);
        return receiveData();
    }
    }
