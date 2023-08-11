package Salon;

import Salon.Class.Salon;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static Scanner sc = new Scanner(System.in);
    public static String menu = """
            1. Print all Salon
            2. Search by salon name
            3. Search by service name
            4. Sorted by name of Salon
            0. Finish
            """;
    public static void main(String[] args) {
        Start.start();
        ListSalon list = new ListSalon("src/Salon/salon.json");
        int choice;
        do{

            System.out.println(menu);
            choice = sc.nextInt();
            switch (choice){
                case 1:{                            //Друк всого List
                    System.out.println(list);
                    break;
                }
                case 2: {                           //Пошук по назві салону
                    System.out.print("Search: ");
                    String searchNameSalon = sc.nextLine();
                    list.searchByNameSalon(searchNameSalon);

                    break;
                }
                case 3:{                            //Пошук по послугам
                    System.out.print("Search: ");
                    String searchNameServices = sc.nextLine();
                    list.searchByNameServices(searchNameServices);
                    break;
                }
                case 4:{                             //Сортування
                    list.sortBySalonName();
                    System.out.println(list);
                }
                default:
                    choice = 0;
                    break;
            }
        }while(choice != 0);
    }
}