package Controller;

import java.util.concurrent.Semaphore;

/**
 * Created by Robinson on 11/03/15.
 */
public class CheckPlayPause implements Runnable {

    protected volatile boolean running = true;
    private Thread thread;


    private TCPServer server;
    private SoundJLayer soundToPlay;

    public CheckPlayPause(TCPServer server, SoundJLayer soundToPlay) {
        this.server = server;
        this.soundToPlay = soundToPlay;
        thread = new Thread(this);
    }

    public void runCheck() {
        thread.start();
    }

    public void stopServer() {
        running = false;
    }

    @Override
    public void run() {
        System.out.println("Check is running");
        for (; ; ) {
            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (server.getClientSentence() != null) {
                System.out.println("\nServeur sentence : " + server.getClientSentence());
                if (server.getClientSentence().equals("play") || server.getClientSentence().equals("pause")) {
                    System.out.println("la");
                    server.setClientSentence(null);
                    soundToPlay.pauseToggle();
                }
            }

        }
    }
}
