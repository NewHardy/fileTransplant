package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class fileTransplanter
{
    public File reciever= new File("src/main/java/org/example/user.json");
    public File doner= new File("src/main/java/org/example/file.txt");
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();
    public void fileTransplant()
    {
        ArrayList<User> database= new ArrayList<>();
        String name;
        int age;
        String line;
        String [] lineSplit;
        try (Scanner scanner= new Scanner(doner))
        {
            while (scanner.hasNext())
            {
                line=scanner.nextLine();
                lineSplit=line.split(" ");
                if (!lineSplit[0].equals("Name")&&!lineSplit[1].equals("Age"))
                {
                    name=lineSplit[0];
                    age=Integer.parseInt(lineSplit[1]);
                    User user = new User(name,age);
                    database.add(user);
                }
            }
            fileSender(database);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }
    private void fileSender(ArrayList<User> database)
    {
        String json=gson.toJson(database);
        try (FileWriter fw = new FileWriter(reciever))
        {
            fw.write(json);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
