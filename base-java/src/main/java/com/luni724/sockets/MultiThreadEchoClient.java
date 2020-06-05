package com.luni724.sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * @author Liq
 * @date 2020/6/5
 */
public class MultiThreadEchoClient {




    public static void main(String[] args) throws IOException {


        for (int i = 0; i < 10; i++) {
            Socket client = null;
            PrintWriter writer = null;
            BufferedReader br = null;

            client = new Socket();
            client.connect(new InetSocketAddress("localhost",9080));
            writer = new PrintWriter(client.getOutputStream(),true);
            writer.println("hello" + i);
            writer.flush();

            br = new BufferedReader(new InputStreamReader(client.getInputStream()));
            System.out.println("from server:" + br.readLine());


            writer.close();
            br.close();
            client.close();
        }



    }
}
