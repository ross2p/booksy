package Salon.JFrame;


import Salon.Class.*;
import Salon.ListSalon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class MyFrame extends JFrame {
    private ListSalon list = new ListSalon("src/Salon/salon.json");

    private JPanel titlePanel = new JPanel();            //панель для назви
    private JPanel searchPanel = new JPanel();          //панель для пошуку
    private JPanel menu = new JPanel();                 //панель для елементів списку
    private JPanel buttonGroup = new JPanel();          //панель для груп радіо кнопки
    private JPanel listPanel = new JPanel();
    private JPanel listPanelSalon = new JPanel();
    private JPanel listPanelEmployee = new JPanel();
    private JRadioButton  searchByNameSalon = new JRadioButton("searchByNameSalon",true);       //Радіо кнопка 1. пошук по назві салону
    private JRadioButton  searchByNameServices = new JRadioButton("searchByNameServices");               //Радіо кнопка 2. пошук по назві салону
    private JTextField searchField = new JTextField(20);    //Поле пошуку
    private JButton searchButton = new JButton("Search");       // Кнопка пошуку

    public MyFrame(){
        super("Salon");
        super.setSize(400, 700);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(400,700));

        //панелі
         titlePanel = new JPanel();   //панель для назви
         searchPanel = new JPanel();  //панель для пошуку
         menu = new JPanel();         //панель для елементів списку
         buttonGroup = new JPanel();  //панель для груп радіо кнопки

        //Назва
        JLabel titleLabel = new JLabel("Bookly");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));




        searchPanel.setLayout(new FlowLayout());
        titlePanel.setLayout(new FlowLayout());
        menu.setLayout(new FlowLayout());

        //салон
        JListSalon(list);


        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ListSalon changedList;
                if (searchByNameSalon.isSelected()) {
                    changedList = list.searchByNameSalon(searchField.getText());
                } else {
                    changedList = list.searchByService(searchField.getText());
                }
                JListSalon(changedList);
            }
        });

        JListEmployee(list.getList().get(0).getEmployees());

        titlePanel.add(titleLabel);

        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        buttonGroup.add(searchByNameSalon);
        buttonGroup.add(searchByNameServices);

        listPanel.add(listPanelSalon);
        listPanel.add(listPanelEmployee);

        menu.add(titlePanel);
        menu.add(searchPanel);
        menu.add(buttonGroup);
        menu.add(listPanel);
        add(menu);


    }
    private class MyListCellRendererSalon extends DefaultListCellRenderer {
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
    public void JListSalon(ListSalon listSalon ){

        DefaultListModel<Salon> model = new DefaultListModel<>();

        for (Salon salon : listSalon.getList()) {
            model.addElement(salon);
        }

        JList<Salon> jList = new JList<>(model);
        jList.setCellRenderer(new MyListCellRendererSalon());
        jList.setFixedCellHeight(80);

        listPanelSalon.removeAll();
        listPanelSalon.add(new JScrollPane(jList));

        listPanelSalon.requestFocusInWindow();

        revalidate();
        repaint();
    }

    public void JListEmployee(java.util.List<Employee> listEmployee) {
        DefaultListModel<Employee> modelEmploee = new DefaultListModel<>();
        List<String> employeeList = new ArrayList<>();

        for (Employee employee : listEmployee) {
            employeeList.add(employee.getName());
        }
        JList<String> jListEmployee = new JList<>(employeeList.toArray(new String[0]));

        listPanelEmployee.removeAll();
        listPanelEmployee.add(new JScrollPane(jListEmployee));

        listPanelEmployee.requestFocusInWindow();

        revalidate();
        repaint();


    }
}
