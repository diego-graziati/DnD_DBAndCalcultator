package com.sersapessi.utilities;

import com.sersapessi.MainSingleton;

import javax.swing.*;
import java.io.IOException;

/**
 * <p>
 * Handles all the graphical loading screen's types.
 * It uses a JFrame loadingScreenFrame as base frame to work on.
 * This class must be called before you start a heavy burden task on a thread, so that the user
 * can understand whether the program is actually running or if it got stuck.
 * </p>
 *<p>
 *     For example, you should call this class and its methods inside a SwingWorker as first and last instructions (before the return).
 *     This way, if chosen the right methods, the loading screen should work correctly.<br>
 *     The fact that the screen can sometimes not appear doesn't mean it's not working, but the Swing Worker Thread is simply too fast
 *     and doesn't let the loading screen thread show the loading screen graphics.
 * </p>
 *
 *
 */
public class LoadingScreensFacility {
    private final IndefiniteLoadingScreen indefiniteLoadingThread;

    public LoadingScreensFacility(JFrame loadingScreenFrame) throws IOException {
        indefiniteLoadingThread = new IndefiniteLoadingScreen(loadingScreenFrame);
    }

    /**
     * <p>
     * This method displays an indefinite loading screen running on a separate thread.
     *  <p>
     *  It checks whether the thread is already running:
     *  if it finds out it is dead, then it starts it. If it finds out otherwise, it prints an error, without throwing any kind of
     *  exception
     *  </p>
     * </p>
     * @param isApplicationFrameRunning tells whether the Application Frame has already been created. This is set to "false" only at the
     *                                  start of the application, when that frame has not been created.
     *
     * @throws IOException thrown if the icon file is missing and, therefore, can't be displayed.
     */
    public void startIndefinite(boolean isApplicationFrameRunning) throws IOException {
        System.out.println("Indefinite created");
        if(!indefiniteLoadingThread.isAlive()){
            indefiniteLoadingThread.start();
            if(isApplicationFrameRunning && MainSingleton.getInstance().applicationFrame.isEnabled()){
                MainSingleton.getInstance().applicationFrame.setEnabled(false);
            }
        }else{
            System.err.println("Thread must be dead before it can be started");
        }
    }
    /**
     * <p>
     * This method joins the indefinite loading screen thread.
     *  <p>
     *  It checks whether the thread is dead or not:
     *  if it founds out it is dead it prints an error, without throwing any kind of exception. It otherwise proceeds to stop
     *  the thread.
     *  </p>
     * </p>
     * @param isApplicationFrameRunning tells whether the Application Frame has already been created. This is set to "false" only at the
     *                                  start of the application, when that frame has not been created.
     *
     * @throws IOException thrown if it is unable to get the MainSingleton instance.
     */
    public void controlledStopIndefinite(boolean isApplicationFrameRunning) throws IOException {
        System.out.println("Indefinite joined - start");
        if(indefiniteLoadingThread.isAlive()){
            indefiniteLoadingThread.setStop();
            if(isApplicationFrameRunning && !MainSingleton.getInstance().applicationFrame.isEnabled()){
                MainSingleton.getInstance().applicationFrame.setEnabled(true);
            }
        }else{
            System.err.println("Thread must be alive before it can be joined");
        }
        System.out.println("Indefinite joined - end");
    }
}
