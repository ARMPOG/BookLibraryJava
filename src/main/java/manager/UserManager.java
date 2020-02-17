package manager;


import models.UserModel;
import storage.UserStorage;
import utils.Util;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class UserManager {

    private static UserManager userManager;

    private UserManager(){}

    public synchronized static UserManager getInstance(){
        synchronized (UserManager.class) {
            if (userManager == null) {
                userManager = new UserManager();
            }
            return userManager;
        }
    }

    private UserStorage userStorage = UserStorage.getInstance();
    private  Util util;

    private UserModel currentUser;

    public static UserManager getInstance(Util util) {
        synchronized (UserManager.class) {
            if (userManager == null) {
                userManager = new UserManager();
                userManager.util = util;
            }
            return userManager;
        }
    }


    public UserModel getCurrentUser(){
        return currentUser;
    }

    public void logInUser() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please fill the information:");
        String email, password;

        try {
            System.out.print("Email:  ");
            email = scan.nextLine();
            System.out.print("Password:  ");
            password = scan.nextLine();

           boolean isExist = userStorage.checkUser(email,password);
           if (isExist){
               util.bookOptions();
               currentUser = UserModel.builder()
                       .email(email)
                       .password(password)
                       .build();
           }else {
               System.out.println("Incorrect email or password ....");
              util.appStart();
           }
        } catch (InputMismatchException n) {
            n.printStackTrace();
        }
    }

    public void registerUser() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please fill the information:");
        String name, email, password;
        try {
            System.out.print("Name:  ");
            name = scan.nextLine();
            System.out.print("Email:  ");
            email = scan.nextLine();
            System.out.print("Password:  ");
            password = scan.nextLine();
            UserModel newUser = UserModel.builder()
                    .name(name)
                    .email(email)
                    .password(password)
                    .build();
            boolean isSaved = userStorage.saveUser(newUser);
            if (isSaved) {
                System.out.println("Saved");
                currentUser = newUser;
            } else {
                System.out.println("Already registered email");
                util.appStart();
            }
        } catch (InputMismatchException e) {
            e.printStackTrace();

        }
    }
}
