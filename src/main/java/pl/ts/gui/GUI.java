package pl.ts.gui;

import pl.ts.database.Database;
import pl.ts.model.User;

import java.util.Collections;
import java.util.List;

public class GUI {
    private static GUI instance = new GUI();

    private GUI() {
    }

    public static GUI getInstance() {
        if (GUI.instance == null) {
            GUI.instance = new GUI();
        }
        return GUI.instance;
    }

    public void showMenu() {
        System.out.println("--------------------------------*");
        System.out.println("Hello!! Choose a number:");
        System.out.println("--------------------------------*");
        System.out.println("1.Add new user");
        System.out.println("2.Show user list");
        System.out.println("3.Find user");
        System.out.println("4.Number of users");
        System.out.println("5.Delete user");
        System.out.println("6.Delete all users");
        System.out.println("7.Exit");
        System.out.println("--------------------------------*");
        System.out.println("Enter the number in the terminal:");
        System.out.println("--------------------------------*");
    }

    public void showAllUsers(List<User> list) {
        if (Database.getInstance().getUsers().isEmpty()) {
            System.out.println("List is empty!!");
        } else
            for (User user : list) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("name:")
                        .append(user.getName() + ",")
                        .append(" surname:")
                        .append(user.getSurname() + ",")
                        .append(" age:")
                        .append(user.getAge() + ",")
                        .append(" phone number:")
                        .append(user.getPhoneNumber());

                System.out.println(stringBuilder.toString());
            }
        System.out.println("---------------------");
    }

}
