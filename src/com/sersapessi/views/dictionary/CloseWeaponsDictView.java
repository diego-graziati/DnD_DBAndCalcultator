package com.sersapessi.views.dictionary;

import javax.swing.*;
import java.io.FileNotFoundException;

public class CloseWeaponsDictView extends  LongWeaponsDictView{

    public CloseWeaponsDictView(JPanel mainP) throws FileNotFoundException {
        super(mainP);
    }

    public void run(int index) throws FileNotFoundException {
        super.runClose(index);
    }
}
