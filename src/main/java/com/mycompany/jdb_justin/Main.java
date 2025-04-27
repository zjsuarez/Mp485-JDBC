
package com.mycompany.jdb_justin;

import Frames.*;
import com.mycompany.jdb_justin.libraries.GestorBBD;


public class Main {
    


    public static void main(String[] args) {
        
        GestorBBD gestor = new GestorBBD();
        MainFrame mainframe = new MainFrame();
        mainframe.setVisible(true);
    }
}
