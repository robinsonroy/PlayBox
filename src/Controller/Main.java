//Source https://thiscouldbebetter.wordpress.com/2011/07/04/pausing-an-mp3-file-using-jlayer/

package Controller;

import java.io.File;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        JavaSoundController javaSound = null;
        //"1:/home/pi/Desktop/PlayBox/Music/David_Guetta/Titanium.mp3"
        //TCPClient client = new TCPClient();

        TCPServer server = new TCPServer();

        server.runServer();

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
                        switch (matcher.group(1)) {
                            case "1":

                                String url = matcher.group(2);
                                File f = new File(url);
                                System.out.println("Load Url ... ");
                                if (f.exists()) {

                                    System.out.println("Music Playing ...");
                                    if (javaSound != null)
                                        javaSound.stop();

                                    javaSound = new JavaSoundController(url);
                                    javaSound.play_pause();

                                } else System.out.println("Bad url!");
                                break;

                            case "2":
                                System.out.println("Music play pause");
                                if (javaSound != null)
                                    javaSound.play_pause();
                                break;
                            default:
                                System.out.println("Bad request");
                                break;
                        }
                        server.setClientSentence(null);
                    }
                }
            }
        }
    }
}