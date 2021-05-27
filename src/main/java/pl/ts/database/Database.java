package pl.ts.database;

import pl.ts.model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Database {
    private static Database instance = new Database();
    private List<User> users = new ArrayList<>();
    private final String pathToDbFile = "Db.csv";

    private Database() {
        this.loadDataFromFile();
        Collections.sort(users);
    }

    public static Database getInstance() {
        if (Database.instance == null) {
            Database.instance = new Database();
        }
        return Database.instance;
    }

    public List<User> getUsers() {
        Collections.sort(users);
        return this.users;
    }

    public User createUser(String yourName, String yourSurname, String yourAge, String yourPhoneNumber) {
        User user = new User(yourName, yourSurname, yourAge, yourPhoneNumber);
        return user;
    }

    public List<User> addUserToList(User user) {
        users.add(user);
        return users;
    }

    public void listUsers() {
        for (User user : this.users) {
            System.out.println(user.getName() + ", " + user.getSurname() + ", " + user.getAge() + ", " + user.getPhoneNumber());
        }
    }

    public void writeDatabaseToFile() {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(this.pathToDbFile));

            for (User user : this.users) {
                bufferedWriter.append(user.converToDbRecord());
                bufferedWriter.newLine();
            }

            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadDataFromFile() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(this.pathToDbFile));

            String record;
            while ((record = bufferedReader.readLine()) != null) {
                String[] recordArray = record.split(";");
                this.users.add(new User(recordArray[0], recordArray[1], recordArray[2], recordArray[3]));
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Database loading failed!!");
            System.exit(0);
        }
    }

    public void findUser(String surname) {
        if (users.isEmpty()) {
            System.out.println("List is empty!!");
        } else
            for (User user : this.users) {
                if (user.getSurname().equals(surname)) {
                    System.out.println(user.getName() + ", " + user.getSurname());
                    break;
                } else
                    System.out.println("User not found!!");
            }
    }

    public void enterNewUserData() {
        try {
            boolean flag = true;
            while (flag) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                System.out.println("Enter your name:");
                String yourName = reader.readLine();
                if (isTypedDataEmpty(yourName)) {
                    System.out.println("Name cannot be empty!!. Repeat!!");
                    continue;
                }
                System.out.println("Enter your surname:");
                String yourSurname = reader.readLine();
                if (isTypedDataEmpty(yourName)) {
                    System.out.println("Surname cannot be empty!!. Repeat!!");
                    continue;
                }
                System.out.println("Enter your age:");
                String yourAge = reader.readLine();
                if (isTypedDataEmpty(yourAge)) {
                    System.out.println("Age cannot be empty!!. Repeat!!");
                    continue;
                }
                System.out.println("Enter your phone number:");
                try {
                    String yourPhoneNumber = reader.readLine();
                    Integer number = Integer.parseInt(yourPhoneNumber);
                    if (isPhoneNumberExistInDb(yourPhoneNumber)) {
                        System.out.println("This phone number already exists. Repeat!!");
                        continue;
                    }
                    if (isPhoneNumberNotGoodLength(yourPhoneNumber)) {
                        System.out.println("Phone number must be 9 characters long!! Repeat!!");
                        continue;
                    }

                    addUserToList(createUser(yourName, yourSurname, yourAge, yourPhoneNumber));
                    flag = false;
                } catch (NumberFormatException e) {
                    System.out.println("You need to enter the numbers!! Repeat!!");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isPhoneNumberExistInDb(String yourPhoneNumber) {
        for (User user : this.users) {
            if (user.getPhoneNumber().equals(yourPhoneNumber)) {
                return true;
            }
        }
        return false;
    }

    public boolean isPhoneNumberNotGoodLength(String yourPhoneNumber) {
        if (yourPhoneNumber.length() != 9) {
            return true;
        }
        return false;
    }

    public boolean isTypedDataEmpty(String data) {
        if (data.equals("")) {
            return true;
        }
        return false;
    }

    public void countingUsers() {
        int counter = 0;
        for (User user : this.users) {
            counter++;
        }
        System.out.println(counter);
    }

    public List<User> removingUser(String surname) {
        Iterator<User> iterator = users.iterator();
        while (iterator.hasNext()) {
            if (surname.equals(iterator.next().getSurname())) {
                iterator.remove();
            } else {
                System.out.println("User not found!!");
            }

        }
        return users;
    }

    public List<User> removingAllUsers(String d) {
        if (d.equals("D")) {
            users.removeAll(users);
        }
        return users;
    }
}
