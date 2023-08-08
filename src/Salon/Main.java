package Salon;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Gson gson = new Gson();
        List<Salon> salons = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("src/Salon/salon.json"))) {
            Type type = new TypeToken<List<Salon>>(){}.getType();
            salons = gson.fromJson(reader, type);
            for (Salon salon : salons) {
                System.out.println(salon);
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found.");
        } catch (JsonSyntaxException e) {
            System.err.println("Invalid JSON syntax in the file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}