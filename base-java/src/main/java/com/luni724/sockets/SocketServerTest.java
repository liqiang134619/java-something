package com.luni724.sockets;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author Liq
 * @date 2020/6/4
 */
public class SocketServerTest {

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(8189);
        Socket incoming = serverSocket.accept();

        PrintWriter printWriter = new PrintWriter(incoming.getOutputStream(), true);
        InputStream inputStream = incoming.getInputStream();
        Scanner in = new Scanner(inputStream);
        String s = in.nextLine();

        printWriter.println("hello ~~~" + s);
        incoming.close();


    }
}
