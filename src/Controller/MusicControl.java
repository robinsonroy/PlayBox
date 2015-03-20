package Controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by Robinson on 18/03/15.
 */
public class MusicControl {
    private String url;
    private SoundJLayer soundToPlay;

    public MusicControl() {
        BufferedReader consoleReader = new BufferedReader
                (
                        new InputStreamReader(System.in)
                );
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void playMusic(){
        if(soundToPlay != null) {
            soundToPlay.stopMusic();
        }
        soundToPlay = new SoundJLayer(this.url);
        soundToPlay.play();
    }

    public void playPause(){
        if(soundToPlay != null)
        soundToPlay.pauseToggle();
    }
}
