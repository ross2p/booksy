package Salon;

import java.util.Scanner;


public class Start {
    public static Scanner sc = new Scanner(System.in);
    public static void start(){

        String start = "\u001B[34m"+"""
                Search engine for salons                                               \s
                                                                                       \s
                ██████╗  ██████╗  ██████╗ ██╗  ██╗███████╗██╗   ██╗                    \s
                ██╔══██╗██╔═══██╗██╔═══██╗██║ ██╔╝██╔════╝╚██╗ ██╔╝                    \s
                ██████╔╝██║   ██║██║   ██║█████╔╝ ███████╗ ╚████╔╝                     \s
                ██╔══██╗██║   ██║██║   ██║██╔═██╗ ╚════██║  ╚██╔╝                      \s
                ██████╔╝╚██████╔╝╚██████╔╝██║  ██╗███████║   ██║                       \s
                ╚═════╝  ╚═════╝  ╚═════╝ ╚═╝  ╚═╝╚══════╝   ╚═╝                       \s
                Lev&Ross project                                                       \s
                """+"\u001B[0m";
        System.out.println(start);
        sc.nextLine();

    }
}
