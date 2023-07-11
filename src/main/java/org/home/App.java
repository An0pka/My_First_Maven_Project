package org.home;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("file.txt"));
             BufferedWriter writer = new BufferedWriter(new FileWriter("user.json"))) {
            String save;
            List<String> list = new ArrayList<>();
            while ((save = reader.readLine()) != null) {
                String[] splitedSave = save.split(" ");
                String name = splitedSave[0];
                int age;
                try {
                    age = Integer.parseInt(splitedSave[1]);
                } catch (NumberFormatException e) {
                    continue;
                }
                User user = new User(name, age);
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                String json = gson.toJson(user);
                list.add(json);
            }
            writer.write(list.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}