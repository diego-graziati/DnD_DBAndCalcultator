package com.sersapessi.views.dictionary;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.IOException;

public class CloseWeaponsDictView extends  LongWeaponsDictView{

    public CloseWeaponsDictView(JPanel mainP) throws FileNotFoundException {
        super(mainP);
    }

    public void run(int index) throws IOException {
        System.out.println("(CloseWpView)Si è sull'Event Dispatch Thread? "+SwingUtilities.isEventDispatchThread());
        super.runClose(index);
    }
}
