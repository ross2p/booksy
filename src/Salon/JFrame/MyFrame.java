package Salon.JFrame;


import Salon.Class.*;
import Salon.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyFrame extends JFrame {
    final private int JLISTHEIGHT = 80;
    final private int JLISTWIDTH = 180;
    private ListSalon list = new ListSalon("src/Salon/salon.json");
    private Reservation reservation = new Reservation();
    private JPanel titlePanel = new JPanel();            //панель для назви
    private JPanel searchPanel = new JPanel();          //панель для пошуку
    private JPanel menu = new JPanel();                 //панель для елементів списку
    private JPanel buttonGroup = new JPanel();          //панель для груп радіо кнопки
    private JPanel listPanel = new JPanel();            //панель для списків
    private JPanel listPanelSalon = new JPanel();       //панель для списку салону
    private JPanel listPanelEmployee = new JPanel();    //панель для списку працівників
    private JPanel listPanelServiceAvailability = new JPanel(); //панель для списку послуг
    private JPanel listPanelDays = new JPanel();        //панель для списку днів
    private JPanel listPanelHours = new JPanel();       //панель для списку годин
    private JPanel PanelButtonReservations = new JPanel();  //панель для кнопки резервацій
    private JPanel accountPanel = new JPanel();         //панель для акаунта
    private JRadioButton searchByNameSalon = new JRadioButton("searchByNameSalon", true);       //Радіо кнопка 1. пошук по назві салону
    private JRadioButton searchByNameServices = new JRadioButton("searchByNameServices");               //Радіо кнопка 2. пошук по назві салону
    private JTextField searchField = new JTextField(20);    //Поле пошуку
    private JButton searchButton = new JButton("Search");       // Кнопка пошуку
    private JButton ButtonReservations = new JButton("Reservations");   //кнопка резервації
    private JButton account = new JButton(new ImageIcon("src/Salon/JFrame/account_ava.png"));  //кнопка акаунта

    //списки
    private JList<Salon> jListSalon = new JList<>(new DefaultListModel<>());
    private JList<Employee> jListEmployee = new JList<>(new DefaultListModel<>());
    private JList<ServiceAvailability> jListServiceAvailability = new JList<>(new DefaultListModel<>());
    private JList<Days> jListDays;
    private JList<Map.Entry<String, Boolean>> jListHours;

    public MyFrame() {
        super("Salon");
        super.setSize(1100, 850);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        JLabel titleLabel = new JLabel("Booksy");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));

        ButtonGroup group = new ButtonGroup();
        group.add(searchByNameSalon);
        group.add(searchByNameServices);


        ButtonReservations.setPreferredSize(new Dimension(100, 50));

        //салон
        JListSalon(list);

        List<Employee> employees = new ArrayList<>();
        JListEmployee(employees);

        List<ServiceAvailability> serviceAvailabilities = new ArrayList<>();
        JListServiceAvailability(serviceAvailabilities);

        Map<Days, Map<String, Boolean>> days = new HashMap<>();
        JListDays(days);

        Map<String, Boolean> hours = new HashMap<>();
        JListHours(hours);

        setButtonAccount();

        searchButton.addActionListener(e -> {
            ListSalon changedList;
            if (searchByNameSalon.isSelected()) {
                changedList = list.searchByNameSalon(searchField.getText());
            } else {
                changedList = list.searchByService(searchField.getText());
            }
            JListSalon(changedList);
            JListEmployee(new ArrayList<>());
            JListServiceAvailability(new ArrayList<>());
            JListDays(new HashMap<>());
            JListHours(new HashMap<>());
        });

        ButtonReservations.addActionListener(e -> {
            if (jListSalon.getSelectedValue() != null && jListServiceAvailability.getSelectedValue() != null && jListEmployee.getSelectedValue() != null && jListDays.getSelectedValue() != null && jListHours.getSelectedValue() != null && jListHours.getSelectedValue().getValue()) {

                JFrame confirmation = new JFrame("Confirmation");
                confirmation.setBounds(400, 400, 400, 150);
                confirmation.setVisible(true);

                JPanel panel = new JPanel();
                JPanel label = new JPanel();
                JPanel buttons = new JPanel();

                JLabel question = new JLabel("Do you want to reserve?");

                question.setFont(new Font("Arial", Font.BOLD, 24));

                JButton yesButton = new JButton("Yes");
                JButton noButton = new JButton("No");

                yesButton.addActionListener(e1 -> {
                    confirmation.dispose();
                    //резервація;
                    boolean reservationSuccess = list.makeReservation(jListSalon.getSelectedValue(), jListEmployee.getSelectedValue(), jListServiceAvailability.getSelectedValue(), jListDays.getSelectedValue(), jListHours.getSelectedValue().getKey());
                    System.out.println(reservationSuccess);
                    if (reservationSuccess) {
                        String reservationOutput = reservation.makeReservation(jListSalon.getSelectedValue(), jListEmployee.getSelectedValue(), jListServiceAvailability.getSelectedValue(), jListDays.getSelectedValue(), jListHours.getSelectedValue().getKey());
                        System.out.println("\u001B[32m" + reservationOutput + "\u001B[0m");

                    }
                });

                noButton.addActionListener(e12 -> confirmation.dispose());

                label.add(question);
                buttons.add(yesButton);
                buttons.add(noButton);
                panel.add(label);
                panel.add(buttons);
                confirmation.add(panel);
            }
        });

        titlePanel.add(titleLabel);

        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        buttonGroup.add(searchByNameSalon);
        buttonGroup.add(searchByNameServices);

        accountPanel.add(account);

        listPanel.add(listPanelSalon);
        listPanel.add(listPanelEmployee);
        listPanel.add(listPanelServiceAvailability);
        listPanel.add(listPanelDays);
        listPanel.add(listPanelHours);

        PanelButtonReservations.add(ButtonReservations);

        menu.add(titlePanel);
        menu.add(searchPanel);
        menu.add(buttonGroup);
        menu.add(accountPanel);
        menu.add(listPanel);
        menu.add(ButtonReservations);

        add(menu);
    }

    public void JListSalon(ListSalon listSalon) {

        DefaultListModel<Salon> model = new DefaultListModel<>();

        for (Salon salon : listSalon.getList()) {
            model.addElement(salon);
        }

        jListSalon = new JList<>(model);
        jListSalon.setCellRenderer(new DefaultListCellRenderer() {
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
        });
        jListSalon.setFixedCellHeight(JLISTHEIGHT);
        jListSalon.setFixedCellWidth(JLISTWIDTH);

        listPanelSalon.removeAll();
        listPanelSalon.add(new JScrollPane(jListSalon));

        listPanelSalon.requestFocusInWindow();

        revalidate();
        repaint();

        jListSalon.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                JListEmployee(jListSalon.getSelectedValue().getEmployees());

                JListServiceAvailability(new ArrayList<>());
                JListDays(new HashMap<>());
                JListHours(new HashMap<>());
            }
        });

    }

    public void JListEmployee(java.util.List<Employee> listEmployee) {
        DefaultListModel<Employee> model = new DefaultListModel<>();

        for (Employee employee : listEmployee) {
            model.addElement(employee);
        }

        jListEmployee = new JList<>(model);
        jListEmployee.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                Employee label = (Employee) value;
                setText(label.getName());
                return this;
            }
        });
        jListEmployee.setFixedCellHeight(JLISTHEIGHT);
        jListEmployee.setFixedCellWidth(JLISTWIDTH);

        listPanelEmployee.removeAll();
        listPanelEmployee.add(new JScrollPane(jListEmployee));

        listPanelEmployee.requestFocusInWindow();

        revalidate();
        repaint();

        jListEmployee.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                JListServiceAvailability(jListEmployee.getSelectedValue().getServices());
                JListDays(new HashMap<>());
                JListHours(new HashMap<>());
            }
        });
    }
    public void JListServiceAvailability(java.util.List<ServiceAvailability> listServiceAvailability) {
        DefaultListModel<ServiceAvailability> model = new DefaultListModel<>();

        for (ServiceAvailability serviceAvailability : listServiceAvailability) {
            model.addElement(serviceAvailability);
        }

        jListServiceAvailability = new JList<>(model);
        jListServiceAvailability.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                ServiceAvailability label = (ServiceAvailability) value;
                setText(label.getServiceName());
                return this;
            }
        });
        jListServiceAvailability.setFixedCellHeight(JLISTHEIGHT);
        jListServiceAvailability.setFixedCellWidth(JLISTWIDTH);

        listPanelServiceAvailability.removeAll();
        listPanelServiceAvailability.add(new JScrollPane(jListServiceAvailability));

        listPanelServiceAvailability.requestFocusInWindow();

        revalidate();
        repaint();

        jListServiceAvailability.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {

                JListDays(jListServiceAvailability.getSelectedValue().getHoursAvailability());
                JListHours(new HashMap<>());

            }
        });
    }

    public void JListDays(Map<Days, Map<String, Boolean>> mapHoursAvailability) {
        DefaultListModel<Days> model = new DefaultListModel<>();

        for (Map.Entry<Days, Map<String, Boolean>> mapDay : mapHoursAvailability.entrySet()) {
            Days day = mapDay.getKey();
            model.addElement(day);
        }

        jListDays = new JList<>(model);
        jListDays.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                Days label = (Days) value;
                setText(label.toString());
                return this;
            }
        });
        jListDays.setFixedCellHeight(JLISTHEIGHT);
        jListDays.setFixedCellWidth(JLISTWIDTH);

        listPanelDays.removeAll();
        listPanelDays.add(new JScrollPane(jListDays));

        listPanelDays.requestFocusInWindow();

        revalidate();
        repaint();

        jListDays.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                JListHours(jListServiceAvailability.getSelectedValue().getHoursInDay(jListDays.getSelectedValue()));

            }
        });
    }

    public void JListHours(Map<String, Boolean> HoursList) {
        DefaultListModel<Map.Entry<String, Boolean>> model = new DefaultListModel<>();

        for (Map.Entry<String, Boolean> mapHour : HoursList.entrySet()) {
            model.addElement(mapHour);
        }

        jListHours = new JList<>(model);
        jListHours.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                Map.Entry<String, Boolean> label = (Map.Entry<String, Boolean>) value;
                String hour = label.getKey();
                Boolean isReserved = label.getValue();
                if (isReserved) {
//                    setForeground(Color.GREEN);
                    setForeground(new Color(73, 200, 83));
                } else {
                    setForeground(Color.RED);
                }
                setFont(new Font("Arial", Font.BOLD, 15));
                setText(hour);
                return this;
            }
        });
        jListHours.setFixedCellHeight(JLISTHEIGHT);
        jListHours.setFixedCellWidth(JLISTWIDTH);

        listPanelHours.removeAll();
        listPanelHours.add(new JScrollPane(jListHours));

        listPanelHours.requestFocusInWindow();

        revalidate();
        repaint();


    }

    public void setButtonAccount(){

        account.addActionListener(e -> {
            JFrame jframe = new JFrame("Your Account");
            jframe.setSize(400, 400);
            // Container containerAllReservation = new Container();
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            panel.setBorder(new EmptyBorder(10, 10, 10, 10));
            for (Salon s : reservation.getReservationTables()) {
                for (Employee employee : s.getEmployees()) {
                    for (ServiceAvailability ser : employee.getServices()) {
                        for (Map.Entry<Days, Map<String, Boolean>> newEntry : ser.getHoursAvailability().entrySet()) {
                            Days day = newEntry.getKey();
                            Map<String, Boolean> hoursMap = newEntry.getValue();
                            for (Map.Entry<String, Boolean> hoursMapEntry : hoursMap.entrySet()) {
                                String hour = hoursMapEntry.getKey();

                                Container containerElement = new Container();
                                containerElement.setLayout(new GridLayout(8,2,0,0));
                                containerElement.add(new Label("Salon name: "));
                                containerElement.add(new Label("<html><font color='blue'>" + s.getName()+"</html>"));
                                containerElement.add(new Label("Address: "));
                                containerElement.add(new Label("<html><font color='blue'>" + s.getAddress()+"</html>"));
                                containerElement.add(new Label("Master: "));
                                containerElement.add(new Label("<html><font color='blue'>" + employee.getName()+"</html>"));
                                containerElement.add(new Label("Service: "));
                                containerElement.add(new Label("<html><font color='blue'>" + ser.getServiceName()+"</html>"));
                                containerElement.add(new Label("Day: "));
                                containerElement.add(new Label("<html><font color='blue'>" + day+"</html>"));
                                containerElement.add(new Label("Time: "));
                                containerElement.add(new Label("<html><font color='blue'>" + hour+"</html>"));
                                GridBagConstraints gbc = new GridBagConstraints();

                                JLabel label = new JLabel("");
                                containerElement.add(label, gbc);

                                panel.add(new JLabel("<---+--->"));
                                panel.add(containerElement);
                            }
                        }
                    }
                }
            }
            jframe.add(new JScrollPane(panel));
            jframe.setVisible(true);
        });
    }
}
