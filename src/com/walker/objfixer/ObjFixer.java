package com.walker.objfixer;

import javax.swing.*;

public class ObjFixer {

    public static JFrame window;

    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                window = new ObjFixerWindow();
            }
        });
    }
}
