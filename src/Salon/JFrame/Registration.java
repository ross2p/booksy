package Salon.JFrame;

import Salon.Reservation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Registration extends JFrame{
    private boolean isRegistration = false;
    private JLabel nameProgram = new JLabel("Booksy");
    private JLabel nameLabel = new JLabel("Name: ");
    private JLabel surNameLabel = new JLabel("Surname:");
    private JLabel emailLabel = new JLabel("Email:");
    private JLabel passwordLabel = new JLabel("Password");
    private JTextField nameField = new JTextField(1);
    private JTextField surNameField = new JTextField(20);
    private JTextField emailField = new JTextField(20);
    private JPasswordField passwordField  = new JPasswordField(20);
    private JPanel panel = new JPanel();
    private JButton registration = new JButton("Registration");
    private String name,surname,email,passwordString;
    public Registration(){
        super("Reservation");
//        super.setSize(600, 700);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setBounds(new Rectangle(400,200,400,200));

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        Container container = new Container();

        container.setLayout(new GridLayout(4,2,2,2));

        passwordField.setEchoChar('*');

        nameField.setColumns(1);

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
        panel.add(registration);


        add(panel);
        registration.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                        String emailPattern = "\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,7}\\b";
                        Pattern pattern = Pattern.compile(emailPattern);
                        Matcher matcher = pattern.matcher(emailField.getText());


                if (nameField.getText().length() > 2 && nameField.getText().length() < 16 &&
                        surNameField.getText().length()>2 && surNameField.getText().length()<16 &&
                        matcher.find() &&
                        passwordField.getText().length() >8 && passwordField.getText().length() <16){

                    name = nameField.getText();
                    surname = surNameField.getText();
                    email = emailField.getText();
                    char[] password = passwordField.getPassword();
                    //password = passwordField.getText();
                    String passwordString = new String(password);
                    setVisible(false);
                    MyFrame frame = new MyFrame(name,surname);
                    frame.setVisible(true);

                }
            }
        });

    }

    @Override
    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return passwordString;
    }

    public boolean isRegistration() {
        return isRegistration;
    }
}
