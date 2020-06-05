package com.luni724.sockets;

import com.sun.xml.internal.messaging.saaj.soap.SOAPVersionMismatchException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Liq
 * @date 2020/6/5
 */
public class MultiThreadEchoServer {

    private static ExecutorService executorService = Executors.newCachedThreadPool();


    static class HandleMsg implements  Runnable{
        Socket clientSocket;

        public HandleMsg(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }
        @Override
        public void run() {
            BufferedReader is = null;
            PrintWriter os = null;

            try {
                is = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                os = new PrintWriter(clientSocket.getOutputStream(),true);
                String inputLine = null;
                long l = System.currentTimeMillis();
                while ((inputLine = is.readLine()) != null) {
                    os.println(inputLine);
                }
                long l1 = System.currentTimeMillis();
                System.out.println("耗时:" + (l1 - l));

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if(is!=null) {
                        is.close();
                    }
                    if(os !=null) {
                        os.close();
                    }
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


        }
    }

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = null;
        Socket clientSocket = null;
        serverSocket = new ServerSocket(9080);

        while(true) {
            clientSocket = serverSocket.accept();
            System.out.println(clientSocket.getRemoteSocketAddress() + "connected");
            executorService.execute(new HandleMsg(clientSocket));

        }
    }
}
