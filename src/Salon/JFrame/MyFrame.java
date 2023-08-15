package Salon.JFrame;


import Salon.Class.*;
import Salon.*;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyFrame extends JFrame {
    private int jListHeight = 80;
    private int jListWidth = 180;
    private ListSalon list = new ListSalon("src/Salon/salon.json");
    private Reservation reservation = new Reservation();
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
    private JRadioButton searchByNameSalon = new JRadioButton("searchByNameSalon", true);       //Радіо кнопка 1. пошук по назві салону
    private JRadioButton searchByNameServices = new JRadioButton("searchByNameServices");               //Радіо кнопка 2. пошук по назві салону
    private JTextField searchField = new JTextField(20);    //Поле пошуку
    private JButton searchButton = new JButton("Search");       // Кнопка пошуку
    private JList<Salon> jListSalon = new JList<>(new DefaultListModel<>());
    private JList<Employee> jListEmployee = new JList<>(new DefaultListModel<>());
    private JList<ServiceAvailability> jListServiceAvailability = new JList<>(new DefaultListModel<>());
    private JList<Days> jListDays;
    private JList<Map.Entry<String, Boolean>> jListHours;
    private Salon selectedValueSalon;
    private Employee selectedValueEmployee;
    private ServiceAvailability selectedValueServiceAvailability;
    private Days selectedValueDays;
    private Map.Entry<String, Boolean> selectedValueHours;
    private JButton ButtonReservations = new JButton("Reservations");
    private JPanel PanelButtonReservations = new JPanel();
    private JPanel accountPanel = new JPanel();
    private JButton account = new JButton(new ImageIcon("src/Salon/JFrame/account_ava.png"));
    boolean isConfirmation;

    public MyFrame() {
        super("Salon");
        super.setSize(1100, 850);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        //Назва
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


        account.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame jframe = new JFrame("Your Account");
                jframe.setSize(400, 400);


                jframe.setVisible(true);
            }
        });

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
                JListEmployee(new ArrayList<>());
                JListServiceAvailability(new ArrayList<>());
                JListDays(new HashMap<>());
                JListHours(new HashMap<>());
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
        jListSalon.setFixedCellHeight(jListHeight);
        jListSalon.setFixedCellWidth(jListWidth);

        listPanelSalon.removeAll();
        listPanelSalon.add(new JScrollPane(jListSalon));

        listPanelSalon.requestFocusInWindow();

        revalidate();
        repaint();

        jListSalon.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    selectedValueSalon = jListSalon.getSelectedValue();
                    JListEmployee(selectedValueSalon.getEmployees());

                    JListServiceAvailability(new ArrayList<>());
                    JListDays(new HashMap<>());
                    JListHours(new HashMap<>());
                }
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
                String name = label.getName();
                String labelText = name;
                setText(labelText);
                return this;
            }
        });
        jListEmployee.setFixedCellHeight(jListHeight);
        jListEmployee.setFixedCellWidth(jListWidth);

        listPanelEmployee.removeAll();
        listPanelEmployee.add(new JScrollPane(jListEmployee));

        listPanelEmployee.requestFocusInWindow();

        revalidate();
        repaint();

        jListEmployee.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    selectedValueEmployee = jListEmployee.getSelectedValue();
                    JListServiceAvailability(selectedValueEmployee.getServices());
                    JListDays(new HashMap<>());
                    JListHours(new HashMap<>());
                }
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
                String serviceName = label.getServiceName();
                String labelText = serviceName;
                setText(labelText);
                return this;
            }
        });
        jListServiceAvailability.setFixedCellHeight(jListHeight);
        jListServiceAvailability.setFixedCellWidth(jListWidth);

        listPanelServiceAvailability.removeAll();
        listPanelServiceAvailability.add(new JScrollPane(jListServiceAvailability));

        listPanelServiceAvailability.requestFocusInWindow();

        revalidate();
        repaint();

        jListServiceAvailability.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    selectedValueServiceAvailability = jListServiceAvailability.getSelectedValue();
                    JListDays(selectedValueServiceAvailability.getHoursAvailability());
                    JListHours(new HashMap<>());

                }
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
                String dayName = label.toString();
                String labelText = dayName;
                setText(labelText);
                return this;
            }
        });
        jListDays.setFixedCellHeight(jListHeight);
        jListDays.setFixedCellWidth(jListWidth);

        listPanelDays.removeAll();  // Clear the previous content from the panel
        listPanelDays.add(new JScrollPane(jListDays));  // Add the new list

        listPanelDays.requestFocusInWindow();

        revalidate();
        repaint();

        jListDays.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    selectedValueDays = jListDays.getSelectedValue();
                    JListHours(selectedValueServiceAvailability.getHoursInDay(selectedValueDays));

                }
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
                String labelText = hour;
                setFont(new Font("Arial", Font.BOLD, 15));
                setText(labelText);
                return this;
            }
        });
        jListHours.setFixedCellHeight(jListHeight);
        jListHours.setFixedCellWidth(jListWidth);

        listPanelHours.removeAll();
        listPanelHours.add(new JScrollPane(jListHours));

        listPanelHours.requestFocusInWindow();

        revalidate();
        repaint();


        jListHours.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    selectedValueHours = jListHours.getSelectedValue();

                    ButtonReservations.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {

                                JFrame confirmation = new JFrame("Confirmation");
                                confirmation.setBounds(400,400,400,150);
                                confirmation.setVisible(true);

                                JPanel panel = new JPanel();
                                JPanel label = new JPanel();
                                JPanel buttons = new JPanel();

                                JLabel question = new JLabel("Do you want to reserve?");

                                question.setFont(new Font("Arial", Font.BOLD, 24));

                                JButton yesButton = new JButton("Yes");
                                JButton noButton = new JButton("No");

                                yesButton.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        confirmation.dispose();
                                            //резервація;
                                            boolean reservationSuccess = list.makeReservation(selectedValueSalon, selectedValueEmployee, selectedValueServiceAvailability, selectedValueDays, selectedValueHours.getKey());
                                            System.out.println(reservationSuccess);
                                            if (reservationSuccess) {
                                                String reservationOutput = reservation.makeReservation(selectedValueSalon, selectedValueEmployee, selectedValueServiceAvailability, selectedValueDays, selectedValueHours.getKey());
                                                System.out.println("\u001B[32m" + reservationOutput + "\u001B[0m");
                                                JListHours(selectedValueServiceAvailability.getHoursInDay(selectedValueDays));
                                            }
                                        }

                                });

                                noButton.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        confirmation.dispose();
                                        JListHours(selectedValueServiceAvailability.getHoursInDay(selectedValueDays));

                                    }
                                });

                                label.add(question);
                                buttons.add(yesButton);
                                buttons.add(noButton);
                                panel.add(label);
                                panel.add(buttons);
                                confirmation.add(panel);
                            }

                    });
                }
            }
        });
    }
}
