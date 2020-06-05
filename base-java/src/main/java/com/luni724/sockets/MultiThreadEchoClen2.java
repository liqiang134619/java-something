package com.luni724.sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.LockSupport;

import static com.luni724.thread.ReenterLock.i;

/**
 * @author Liq
 * @date 2020/6/5
 */
public class MultiThreadEchoClen2 {

    private static ExecutorService executorService = Executors.newCachedThreadPool();

    private static final int SLEEP_TIME = 1000 * 1000 * 1000;

    static class EchoClient implements  Runnable {

        @Override
        public void run() {

            Socket client = null;
            PrintWriter writer = null;
            BufferedReader br = null;

            client = new Socket();
            try {
                client.connect(new InetSocketAddress("localhost",9080));
                writer = new PrintWriter(client.getOutputStream(),true);
                writer.print("h");
                LockSupport.parkNanos(SLEEP_TIME);
                writer.print("e");
                LockSupport.parkNanos(SLEEP_TIME);
                writer.print("l");
                LockSupport.parkNanos(SLEEP_TIME);
                writer.print("l");
                LockSupport.parkNanos(SLEEP_TIME);
                writer.print("0");
                LockSupport.parkNanos(SLEEP_TIME);
                writer.println();
                writer.flush();


                br = new BufferedReader(new InputStreamReader(client.getInputStream()));
                System.out.println("from server:" + br.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            } finally {

                try {
                    if (writer != null) {
                        writer.close();
                    }
                    if (br != null) {
                        br.close();
                    }
                    client.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }



        }
    }

    public static void main(String[] args) {
        EchoClient client = new EchoClient();
        for (int i1 = 0; i1 < 10; i1++) {
            executorService.execute(client);
        }
    }

}
