package com.luni724.sockets;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author Liq
 * @date 2020/6/4
 */
public class SocketTest {

    public static void main(String[] args) throws IOException {

        Socket socket = new Socket("time-A.timefreq.bldrdoc.gov", 13);
        InputStream inputStream = socket.getInputStream();
        Scanner scanner = new Scanner(inputStream);
        while(scanner.hasNextLine()) {
            String s = scanner.nextLine();
            System.out.println(s);
        }
        socket.close();

    }
}
