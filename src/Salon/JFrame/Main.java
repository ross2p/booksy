package Salon.JFrame;


import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        Registration registration = new Registration();
        registration.setVisible(true);


        MyFrame frame = new MyFrame();
        frame.setVisible(false);
    }
}
