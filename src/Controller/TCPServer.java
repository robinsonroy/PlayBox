package Controller;

/**
 * Created by Robinson on 16/02/15.
 */

import java.io.*;
import java.net.*;

class TCPServer implements Runnable{
    protected volatile boolean running = true;
    private Thread thread;

    public TCPServer(){
        thread = new Thread(this);
    }

    public void runServer(){
        thread.start();
    }

    public void stopServer(){
        running = false;
    }

    public void run() {
        System.out.println("Server TCP lance");

        String clientSentence;
        String capitalizedSentence;
        ServerSocket welcomeSocket = null;

        try {
            welcomeSocket = new ServerSocket(6790);
            while (running) {
                Socket connectionSocket = welcomeSocket.accept();
                BufferedReader inFromClient =
                        new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
                DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
                clientSentence = inFromClient.readLine();
                System.out.println("Received: " + clientSentence);
                capitalizedSentence = clientSentence.toUpperCase() + '\n';
                outToClient.writeBytes(capitalizedSentence);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}