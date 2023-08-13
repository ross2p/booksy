package Salon.JFrame;


import Salon.Class.*;
import Salon.ListSalon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyFrame extends JFrame {
    int jListHeight = 80;
    int jListWidth = 180;
    private ListSalon list = new ListSalon("src/Salon/salon.json");

    private JPanel titlePanel = new JPanel();            //панель для назви
    private JPanel searchPanel = new JPanel();          //панель для пошуку
    private JPanel menu = new JPanel();                 //панель для елементів списку
    private JPanel buttonGroup = new JPanel();          //панель для груп радіо кнопки
    private JPanel listPanel = new JPanel();
    private JPanel listPanelSalon = new JPanel();
    private JPanel listPanelEmployee = new JPanel();
    private JPanel listPanelServiceAvailability = new JPanel();
    private JPanel listPanelDays = new JPanel();
    private JPanel listPanelHours = new JPanel();
    private JRadioButton  searchByNameSalon = new JRadioButton("searchByNameSalon",true);       //Радіо кнопка 1. пошук по назві салону
    private JRadioButton  searchByNameServices = new JRadioButton("searchByNameServices");               //Радіо кнопка 2. пошук по назві салону
    private JTextField searchField = new JTextField(20);    //Поле пошуку
    private JButton searchButton = new JButton("Search");       // Кнопка пошуку

    public MyFrame(){
        super("Salon");
        super.setSize(1200, 1000);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // setLayout(new BorderLayout(400,700));

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

        List<Employee> employees = new ArrayList<>();
        JListEmployee(employees);

        List<ServiceAvailability> serviceAvailabilities = new ArrayList<>();
        JListServiceAvailability(serviceAvailabilities);

        Map<Days,Map<String,Boolean>> days = new HashMap<>();
        JListDays(days);

        Map<String, Boolean> hours = new HashMap<>();
        JListHours(hours);



        titlePanel.add(titleLabel);

        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        buttonGroup.add(searchByNameSalon);
        buttonGroup.add(searchByNameServices);

        listPanel.add(listPanelSalon);
        listPanel.add(listPanelEmployee);
        listPanel.add(listPanelServiceAvailability);
        listPanel.add(listPanelDays);
        listPanel.add(listPanelHours);

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
        jList.setFixedCellHeight(jListHeight);
        jList.setFixedCellWidth(jListWidth);

        listPanelSalon.removeAll();
        listPanelSalon.add(new JScrollPane(jList));

        listPanelSalon.requestFocusInWindow();

        revalidate();
        repaint();
    }

    public void JListEmployee(java.util.List<Employee> listEmployee) {
        List<String> employeeList = new ArrayList<>();

        for (Employee employee : listEmployee) {
            employeeList.add(employee.getName());
        }
        JList<String> jListEmployee = new JList<>(employeeList.toArray(new String[0]));

        listPanelEmployee.removeAll();
        jListEmployee.setFixedCellHeight(jListHeight);
        jListEmployee.setFixedCellWidth(jListWidth);
        listPanelEmployee.add(new JScrollPane(jListEmployee));

        listPanelEmployee.requestFocusInWindow();

        revalidate();
        repaint();
    }
    public void JListServiceAvailability(java.util.List<ServiceAvailability> listServiceAvailability) {
        List<String> serviceAvailabilityList = new ArrayList<>();

        for (ServiceAvailability employee : listServiceAvailability) {
            serviceAvailabilityList.add(employee.getServiceName());
        }
        JList<String> jListServiceAvailability = new JList<>(serviceAvailabilityList.toArray(new String[0]));

        listPanelServiceAvailability.removeAll();
        jListServiceAvailability.setFixedCellHeight(jListHeight);
        jListServiceAvailability.setFixedCellWidth(jListWidth);
        listPanelServiceAvailability.add(new JScrollPane(jListServiceAvailability));

        listPanelServiceAvailability.requestFocusInWindow();

        revalidate();
        repaint();
    }
    public void JListDays(Map<Days,Map<String, Boolean>> mapHoursAvailability){
        List<String> daysList = new ArrayList<>();

        for (Map.Entry<Days, Map<String, Boolean>> newEntry : mapHoursAvailability.entrySet()) {
            Days newDay = newEntry.getKey();
            daysList.add(newDay.toString());
        }
        JList<String> jListDays = new JList<>(daysList.toArray(new String[0]));

        listPanelDays.removeAll();
        jListDays.setFixedCellHeight(jListHeight);
        jListDays.setFixedCellWidth(jListWidth);
        listPanelDays.add(new JScrollPane(jListDays));

        listPanelDays.requestFocusInWindow();

        revalidate();
        repaint();

    }
    public void JListHours(Map<String, Boolean> HoursList){
        List<String> hoursList = new ArrayList<>();

        for (Map.Entry<String, Boolean> newInnerEntry : HoursList.entrySet()) {
            String key = newInnerEntry.getKey();
//            Boolean value = newInnerEntry.getValue();
            hoursList.add(key);
        }
        JList<String> jListHours = new JList<>(hoursList.toArray(new String[0]));

        listPanelHours.removeAll();
        jListHours.setFixedCellHeight(jListHeight);
        jListHours.setFixedCellWidth(jListWidth);
        listPanelHours.add(new JScrollPane(jListHours));

        listPanelHours.requestFocusInWindow();

        revalidate();
        repaint();
    }
}
