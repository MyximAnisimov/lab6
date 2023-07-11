package client;

import client.utility.Runner;
import common.utility.CustomConsole;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.io.IOException;
import java.net.InetAddress;

public class Main {
    public static final Logger logger = LogManager.getLogger("ClientLogger");
    private static final int port = 1233;
    public static void main(String[] args) {

            try {
                var client = new UDPclient(InetAddress.getLocalHost(), port);
                var runner = new Runner(client);
                runner.interactiveMode();
            } catch (IOException e) {
                CustomConsole.printLn("Невозможно подключиться к серверу.");
            }
}}