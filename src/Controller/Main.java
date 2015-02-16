//Source https://thiscouldbebetter.wordpress.com/2011/07/04/pausing-an-mp3-file-using-jlayer/

package Controller;

import java.io.*;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        SoundJLayer soundToPlay = new SoundJLayer("/Users/Robinson/Desktop/CHARLIE.mp3");

        BufferedReader consoleReader = new BufferedReader
                (
                        new InputStreamReader(System.in)
                );

        soundToPlay.play();

        TCPClient client = new TCPClient();

        System.out.println("Hello");
        TCPServer server = new TCPServer();
        server.runServer();
        System.out.println("Hello&");

        boolean finish = false;
        while (!finish)
        {
            System.out.print("1 : Play/Pause , 2 : Message TCP : ");
            Scanner sc = new Scanner(System.in);
            String choice = sc.nextLine();

            switch (choice){
                case "1" :
                    soundToPlay.pauseToggle();
                    break;
                case "2" :
                    client.sendMessage();
                    break;
                default:
                    System.out.println("Entrée non reconnu");
                    break;
            }


        }
    }
}