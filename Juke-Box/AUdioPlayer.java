// Java program to play an Audio
// file using Clip Object
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class AUdioPlayer {

    // to store current position
    Long currentFrame = 0L;
    Clip clip;

    // current status of clip
    String status;

    AudioInputStream audioInputStream;
    String filePath;


    // constructor to initialize streams and clip
   /* public  AUdioPlayer()
             {
        // create AudioInputStream object
     try{

         clip.loop(Clip.LOOP_CONTINUOUSLY);
     }catch (Exception e)
     {

     }
    }*/

    public void playMusic(String path) {
        try {
            filePath = path;


            play();
            Scanner sc = new Scanner(System.in);

            while (true) {
                System.out.println("1. pause");
                System.out.println("2. resume");
                System.out.println("3. restart");
                System.out.println("4. stop");
                System.out.println("5. Jump to specific time");
                int c = sc.nextInt();
                gotoChoice(c);
                if (c == 4)
                    break;
            }

        } catch (Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();

        }
    }

    // Work as the user enters his choice

    private void gotoChoice(int c)
            throws IOException, LineUnavailableException, UnsupportedAudioFileException {
        switch (c) {
            case 1:
                pause();
                break;
            case 2:
                resumeAudio();
                break;
            case 3:
                restart();
                break;
            case 4:
                stop();
                break;
            case 5:
                System.out.println("Enter time in second");
                Scanner sc = new Scanner(System.in);
                int c1 = sc.nextInt();
                jump(c1);
                break;

        }

    }

    // Method to play the audio
    public void play() {
        try {
            audioInputStream =
                    AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());

            // create clip reference
            clip = AudioSystem.getClip();


            // open audioInputStream to the clip
            clip.open(audioInputStream);
            //start the clip
            clip.setMicrosecondPosition(currentFrame);
            clip.start();
            getRemainingTime();

            status = "play";

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            throw new RuntimeException(e);
        }

        //start the clip

    }

    // Method to pause the audio
    public void pause() {
        if (status.equals("paused")) {
            System.out.println("audio is already paused");
            return;
        }
        this.currentFrame =
                this.clip.getMicrosecondPosition();
        clip.stop();
        getRemainingTime();
        status = "paused";
    }

    // Method to resume the audio
    public void resumeAudio() throws UnsupportedAudioFileException,
            IOException, LineUnavailableException {
        if (status.equals("play")) {
            System.out.println("Audio is already " +
                    "being played   ");
            return;
        }
        getRemainingTime();
        clip.close();
        //resetAudioStream();
        //clip.setMicrosecondPosition(currentFrame);
        this.play();
    }

    // Method to restart the audio
    public void restart() throws IOException, LineUnavailableException,
            UnsupportedAudioFileException {
        clip.stop();
        clip.close();
        //resetAudioStream();
        currentFrame = 0L;
        //clip.setMicrosecondPosition(0);
        this.play();
        getRemainingTime();
    }

    // Method to stop the audio
    public void stop() throws UnsupportedAudioFileException,
            IOException, LineUnavailableException {
        currentFrame = 0L;
        clip.stop();
        clip.close();
        //getRemainingTime();

    }

    // Method to jump over a specific part
    public void jump(int t) throws UnsupportedAudioFileException, IOException,
            LineUnavailableException {
        long c = clip.getMicrosecondPosition() + t * 1000000;
        if (c > 0 && c < clip.getMicrosecondLength()) {
            clip.stop();
            clip.close();
            // resetAudioStream();
            currentFrame = c;
            //clip.setMicrosecondPosition(c);
            this.play();
        }
    }

    // Method to reset audio stream
    public void resetAudioStream() throws UnsupportedAudioFileException, IOException,
            LineUnavailableException {
        audioInputStream = AudioSystem.getAudioInputStream(
                new File(filePath).getAbsoluteFile());
        clip.open(audioInputStream);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void getRemainingTime() {
        long timeInSecs = (clip.getMicrosecondLength() - (clip.getMicrosecondPosition()) % clip.getMicrosecondLength()) / 1000000;
        long minutes = timeInSecs / 60;
        long secs = timeInSecs % 60;
        System.out.println("Remaining Time:" + minutes + ":" + secs);


    }
}

