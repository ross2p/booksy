package Salon.JFrame;


import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MyFrame frame = new MyFrame();
                frame.setVisible(true);
            }
        });
    }
}
