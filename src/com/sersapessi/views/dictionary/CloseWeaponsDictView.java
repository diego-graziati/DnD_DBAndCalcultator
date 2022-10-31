package com.sersapessi.views.dictionary;

import javax.swing.*;
import java.io.FileNotFoundException;

public class CloseWeaponsDictView extends  LongWeaponsDictView{

    public CloseWeaponsDictView(JPanel mainP, JComboBox<String> weaponsList) throws FileNotFoundException {
        super(mainP,weaponsList);
    }

    public void run() throws FileNotFoundException {
        super.runClose();
    }
}
