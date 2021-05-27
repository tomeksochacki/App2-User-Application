package pl.ts;

import pl.ts.database.Database;
import pl.ts.gui.GUI;
import pl.ts.model.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {
    private static final Logger log = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        log.info("Example log from {}" , App.class.getSimpleName());

        Database database = Database.getInstance();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        boolean flag = true;
        while (flag){
            try {
                GUI.getInstance().showMenu();
                switch (bufferedReader.readLine()) {
                    case "1":
                        Database.getInstance().enterNewUserData();
                        break;
                    case "2":
                        GUI.getInstance().showAllUsers(Database.getInstance().getUsers());
                        break;
                    case "3":
                        System.out.println("Enter the user surname:");
                        Database.getInstance().findUser(bufferedReader.readLine());
                        break;
                    case "4":
                        System.out.println("The number of users is: ");
                        Database.getInstance().countingUsers();
                        break;
                    case "5":
                        System.out.println("Enter the user surname:");
                        Database.getInstance().removingUser(bufferedReader.readLine());
                        break;
                    case "6":
                        System.out.println("If you delete all users click: D");
                        Database.getInstance().removingAllUsers(bufferedReader.readLine());
                        break;
                    case "7":
                        flag = false;
                        Database.getInstance().writeDatabaseToFile();
                        break;
                    default:
                        System.out.println("Wrong choice!!");
                }
            } catch (IOException e) {
                System.out.println("Data download failed!!");
            }

        }

    }
}
