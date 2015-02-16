package Controller;

/**
 * Created by Robinson on 16/02/15.
 */
import java.io.*;
import java.net.*;

class TCPClient
{
    private String message;

    public TCPClient(){
    }


    public void sendMessage() {
        String sentence;
        String modifiedSentence;

            BufferedReader inFromUser = new BufferedReader( new InputStreamReader(System.in));
            Socket clientSocket = null;
        try {

            clientSocket = new Socket("127.0.0.1", 6789);
            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            sentence = inFromUser.readLine();
            outToServer.writeBytes(sentence + '\n');
            modifiedSentence = inFromServer.readLine();
            System.out.println("FROM SERVER: " + modifiedSentence);
            clientSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }



    }
}
