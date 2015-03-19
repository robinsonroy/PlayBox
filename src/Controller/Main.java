//Source https://thiscouldbebetter.wordpress.com/2011/07/04/pausing-an-mp3-file-using-jlayer/

package Controller;

import java.io.File;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        MusicControl musicControl = new MusicControl();
        //"/home/pi/Desktop/PlayBox/Music/David_Guetta/Titanium.mp3"
        //TCPClient client = new TCPClient();

        TCPServer server = new TCPServer();

        server.runServer();

        //musicControl.setUrl("/Users/Robinson/Desktop/CHARLIE.mp3");
        //musicControl.playMusic();

        //CheckMessageFromSmartBox checkPlayPause = new CheckMessageFromSmartBox(server);
        //checkPlayPause.runCheck();

        boolean finish = false;
        while (!finish) {
            Pattern pattern = Pattern.compile("^(\\d+):(.+)$");
            Matcher matcher;
            System.out.println("Check is running");
            for (; ; ) {
                try {
                    Thread.sleep(1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (server.getClientSentence() != null) {
                    matcher = pattern.matcher(server.getClientSentence());
                    if (matcher.find()) {
                        if (matcher.group(1).equals("1")) {
                            String url = matcher.group(2);
                            File f = new File(url);
                            if(f.exists()) {
                                musicControl.setUrl(url);
                                musicControl.playMusic();
                            }
                            server.setClientSentence(null);
                        } else if (matcher.group(1).equals("2")) {
                            musicControl.playPause();
                            server.setClientSentence(null);
                        }
                    }
                    server.setClientSentence(null);
                }
            }
        }
    }
}