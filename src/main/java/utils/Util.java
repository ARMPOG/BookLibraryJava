package utils;

import manager.BookManager;
import manager.UserManager;
import models.Book;
import storage.BookStorage;
import storage.UserStorage;


import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Util {

//    private static Util util;

    public Util() {}

//    public synchronized static Util getInstance() {
//        synchronized (Util.class) {
//            if (util == null) {
//                util = new Util();
//            }
//            return util;
//        }
//    }
        private UserManager userManager = UserManager.getInstance(this);
        private BookManager bookManager = BookManager.getInstance(this);
        private BookStorage bookStorage = BookStorage.getInstance();



        public void appStart () {
            Scanner scan = new Scanner(System.in);
            System.out.println("Please choose from below options \n1.Log In \n2.Register \n3.Quit ");
            int choice;

            try {
                choice = scan.nextInt();
                switch (choice) {
                    case 1:
                        userManager.logInUser();
                        bookOptions();
                        break;
                    case 2:
                        userManager.registerUser();
                        bookOptions();
                        break;
                    case 3:
                        System.out.println("Quiting ......");
                        System.exit(0);
                        break;
                    default:
                        throw new InputMismatchException();
                }

            } catch (InputMismatchException e) {
                System.out.println("Please enter from 1 to 3 !!!");
                appStart();
            }
        }

        public void bookOptions () {

            Scanner scan = new Scanner(System.in);
            System.out.println("Please choose from below options \n1.Add book \n2.My books \n3.All books \n4.Log out ");
            int choice;

            try {
                choice = scan.nextInt();
                switch (choice) {
                    case 1:
                        bookManager.creatBook();
                        bookOptions();
                        break;
                    case 2:
                       List<Book> currentUserBooks = bookStorage.getMyBooks();
                        System.out.println(currentUserBooks);
                        bookOptions();
                        break;
                    case 3:
                        List<Book> allUsersBooks = bookStorage.getAllBooks();
                        System.out.println(allUsersBooks);
                        bookOptions();
                        break;
                    case 4:
                        appStart();
                        break;
                    default:
                        throw new InputMismatchException();
                }

            } catch (InputMismatchException e) {
                System.out.println("Please enter from 1 to 3 !!!");
                appStart();
            }
        }
    }

