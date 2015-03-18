//Source https://thiscouldbebetter.wordpress.com/2011/07/04/pausing-an-mp3-file-using-jlayer/

package Controller;

import java.io.*;
import java.util.Scanner;

public class Main
{

    public static void main(String[] args)
    {
        SoundJLayer soundToPlay = new SoundJLayer("/home/pi/Desktop/PlayBox/Music/Chrisophe\\ Mae/Sous\\ Le\\ Charme.mp3");

        BufferedReader consoleReader = new BufferedReader
                (
                        new InputStreamReader(System.in)
                );
        soundToPlay.play();

        TCPClient client = new TCPClient();

        TCPServer server = new TCPServer();
        server.runServer();

        CheckPlayPause checkPlayPause = new CheckPlayPause(server, soundToPlay);
        checkPlayPause.runCheck();

        boolean finish = false;
        while (!finish)
        {
            System.out.println("1 : Play/Pause , 2 : Message TCP1");
            Scanner sc = new Scanner(System.in);
            String choice = sc.nextLine();

            switch (choice){
                case "1" :
                    soundToPlay.pauseToggle();
                    break;
                case "2" :
                    client.sendMessage();
                    break;
                case "3" :
                    System.out.println("Serveur sentence : " +  server.getClientSentence());
                    break;
                default:
                    System.out.println("Entrée non reconnu");
                    break;
            }
        }

    }
}