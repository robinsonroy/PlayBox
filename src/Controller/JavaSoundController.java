package Controller;

import javax.sound.sampled.*;
import java.io.*;

/**
 * Created by Robinson on 22/03/15.
 */
public class JavaSoundController {
    private AudioInputStream myStream;
    private Clip clip;
    private String url;

    public JavaSoundController() {
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void initialise(){
        try {
            myStream =
                    AudioSystem.getAudioInputStream(new File(url));
            clip = AudioSystem.getClip();
            clip.open(myStream);

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public JavaSoundController(String url){
        try {
            myStream =
                    AudioSystem.getAudioInputStream(new File(url));
            clip = AudioSystem.getClip();
            clip.open(myStream);

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void play_pause(){
        if(clip.isRunning())
            clip.stop();
        else clip.start();

    }

    public void stop(){
        clip.stop();
        try {
            myStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}


