package Salon;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;

public class Main {
    public static void main(String[] args){
        Gson gson = new Gson();
        try (BufferedReader reader = new BufferedReader(new FileReader("salons.gson"))) {
            Type type = new TypeToken<Salon>(){}.getType();
            Salon salon = gson.fromJson(reader, type);
            System.out.println(salon);
        } catch (FileNotFoundException e) {
            System.err.println("File not found");
        }catch (IOException e) {
            e.printStackTrace();
        }

    }
}