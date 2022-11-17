package com.sersapessi.utilities;

import com.sersapessi.MainSingleton;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * <p>It defines the Loading Screen Thread (LST). This thread is completely separated by the Main Application Thread and the EDT (Event
 * Dispatch Thread) and only handles the graphics that go into making a loading screen.
 * </p>
 * */
public class IndefiniteLoadingScreen extends Thread {
    private JPanel basicPanel;
    private JFrame baseFrame;
    private volatile boolean stop;
    private JLabel label;
    ArrayList<BufferedImage> images;            //A BufferedImage ArrayList containing all the loading GIF's pngs. They will be displayed sequentially inside the run() method.

    /**
     * <p>
     *     The constructor instantiates what must be instantiated.
     *
     * @param baseFrame it is the frame where it will be displayed the loading animation
     * </p>
     * */
    public IndefiniteLoadingScreen(JFrame baseFrame) throws IOException {
        basicPanel = new JPanel();

        images = new ArrayList<>();
        File[] tempFiles = new File("resource/loadingGifs/").listFiles();
        if(tempFiles!=null){
            for (File tempFile : tempFiles) {
                images.add(ImageIO.read(tempFile));
            }
        }else{
            System.err.println("No image loaded");
        }

        stop=false;

        label = new JLabel();
        basicPanel.add(label);
        baseFrame.add(basicPanel);
        baseFrame.setUndecorated(true);
        baseFrame.pack();
        baseFrame.setAlwaysOnTop(true);
        this.baseFrame = baseFrame;
    }

    /**
     * <p>
     * Runned everytime the Thread is started through a start() call, this method runs until an external flag called "stop" is set to "true".
     * It also sets the base frame visible or not visible.
     * Before ending its tasks, this method resets the "stop" flag to false.
     * </p>
     * */
    @Override
    public void run() {
        //TODO: the frame must appear at the center of the application frame.
        baseFrame.setVisible(true);
        /*The "Thread.onSpinWait()" method indicates to the JVM that at each loop iteration the thread could be influenced by
            an externally set flag, in this case that variable is "stop". Otherwise, the thread won't be updated and the window
            won't be closed. It is a mess, I know.
        */
        int i=0;
        while (!stop) {                                     /*If the program is too fast, then stop==true before the run() methods comes to the while(). Therefore, nothing would be displayed*/
            label.setIcon(new ImageIcon(images.get(i)));
            if(i == images.size()){
                i=0;
            }
            Thread.onSpinWait();
        }
        stop=false;
        baseFrame.setVisible(false);
    }

    /**
     * It sets the "stop" flag to "true" in order to stop the thread.
     */
    public void setStop(){
        stop=true;
    }
}
