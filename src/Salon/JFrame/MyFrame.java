package Salon.JFrame;


import Salon.Class.Salon;
import Salon.ListSalon;

import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {
    public MyFrame(){
        super("Salon");
        super.setSize(400, 700);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(400,700));

        JLabel titleLabel = new JLabel("Bookly");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));

        JTextField searchField = new JTextField(20);
        JButton searchButton = new JButton("Search");

        JPanel titlePanel = new JPanel();
        JPanel searchPanel = new JPanel();
        JPanel menu = new JPanel();

        searchPanel.setLayout(new FlowLayout());
        titlePanel.setLayout(new FlowLayout());
        menu.setLayout(new FlowLayout());

        JRadioButton  searchByNameSalon = new JRadioButton("searchByNameSalon");

        JRadioButton  searchByNameServices = new JRadioButton("searchByNameServices");
        JPanel buttonGroup = new JPanel();

        buttonGroup.add(searchByNameSalon);
        buttonGroup.add(searchByNameServices);

        titlePanel.add(titleLabel);

        searchPanel.add(searchField);
        searchPanel.add(searchButton);


        menu.add(titlePanel);
        menu.add(searchPanel);
        menu.add(buttonGroup);
        // menu.add(searchPanel);
        //container.add(menu);

        ListSalon list = new ListSalon("src/Salon/salon.json");

        JList<Salon> jList = new JList<>();
        DefaultListModel model = new DefaultListModel<>();

        JScrollPane scrollPane = new JScrollPane(jList);

        for (int i = 0; i < list.getList().size(); i++) {
            model.addElement(list.getList().get(i));
        }
        jList.setModel(model);
        jList.setCellRenderer(new MyListCellRenderer());
        jList.setFixedCellHeight(80); // відстань між елементами
        JPanel listPanel = new JPanel();
        listPanel.add(scrollPane);


        menu.add(listPanel);
        add(menu);


    }
    private class MyListCellRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            Salon label = (Salon) value;
            String name = label.getName();
            String address = label.getAddress();
            String labelText = "<html>Name: " + name + "<br/>Address: " + address + "<br/>";
            setText(labelText);
            return this;
        }
    }
}
