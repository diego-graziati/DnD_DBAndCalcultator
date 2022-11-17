package com.sersapessi;

import com.sersapessi.views.DictionaryView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;

public class MainFrame extends JFrame implements ActionListener {

    private  JPanel rootP;

    private final static float HZ_MILLIS = 1.6f;
    private Timer timer;

    public void run() throws IOException {
        rootP = new JPanel();
        rootP.setLayout(new GridBagLayout());

        DictionaryView dictView = new DictionaryView(rootP);
        dictView.run();

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    MainSingleton.getInstance().db.close();
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        MainSingleton.getInstance().applicationFrame = this;            /*This way everywhere in the program the main Application
                                                                        Frame can be called*/

        this.add(rootP);
        this.pack();
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setRefreshRate();
        this.setVisible(true);
    }

    /*Had to fix a problem: it fixed nothing*/
    public void setRefreshRate(){
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gs = ge.getDefaultScreenDevice();

        DisplayMode dm = gs.getDisplayMode();

        int refreshRate = dm.getRefreshRate();
        if (refreshRate == DisplayMode.REFRESH_RATE_UNKNOWN) {
            System.out.println("Unknown rate");
        }

        timer = new Timer((int)(HZ_MILLIS*refreshRate), this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.repaint();
    }
}
