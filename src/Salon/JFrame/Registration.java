package Salon.JFrame;

import Salon.Reservation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Registration extends JFrame{
    private JLabel nameProgram = new JLabel("Booksy");
    private JLabel nameLabel = new JLabel("Name: ");
    private JLabel surNameLabel = new JLabel("Surname:");
    private JLabel emailLabel = new JLabel("Email:");
    private JLabel passwordLabel = new JLabel("password");
    private JTextField nameField = new JTextField(20);
    private JTextField surNameField = new JTextField(20);
    private JTextField emailField = new JTextField(20);
    private JTextField passwordField  = new JTextField(20);
    private JPanel panel = new JPanel();
    private JButton registration = new JButton("registration");
    public Registration(){
        super("Reservation");
        super.setSize(600, 600);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        Container container = new Container();

        container.setLayout(new GridLayout(4,2,2,2));


        container.add(nameLabel);
        container.add(nameField);
        container.add(surNameLabel);
        container.add(surNameField);
        container.add(emailLabel);
        container.add(emailField);
        container.add(passwordLabel);
        container.add(passwordField);
        nameProgram.setFont(new Font("Arial", Font.BOLD, 24));

        panel.add(nameProgram);
        panel.add(container);


        add(panel);
        registration.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });

    }
}
