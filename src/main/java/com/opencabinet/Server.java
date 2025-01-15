package com.opencabinet;

import com.opencabinet.service.Port;
import com.opencabinet.service.ServerThread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Server {
    public static Port activePort;
    public Server() throws IOException {
        System.out.println("Starting...");

        openPort(9090);
        System.out.println("End.");
    }

    private void openPort(int port) throws IOException {

        ServerSocket listener = new ServerSocket(port);
        System.out.println("Server started on "+port);


        //create new portData entry if none exist
        activePort=new Port(port);

        try {
            while(activePort!=null) {
                Socket socket=listener.accept();
                new ServerThread(socket, port).start();

            }
        }finally {
            listener.close();
            System.out.println("Program Finished");
        }
    }
    private static void deactivatePort(int port) {
        activePort=null;
    }


    public static void main(String[] args) throws IOException {
        new Server();
    }

}