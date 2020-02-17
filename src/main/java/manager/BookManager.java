package manager;

import models.Book;
import models.UserModel;
import storage.BookStorage;
import utils.Util;

import java.util.InputMismatchException;
import java.util.Scanner;

public class BookManager {

    private static BookManager bookManager;

    private BookManager() {
    }

    public synchronized static BookManager getInstance() {
        synchronized (BookManager.class) {
            if (bookManager == null) {
                bookManager = new BookManager();
            }
        }
        return bookManager;
    }

    BookStorage bookStorage = BookStorage.getInstance();
    private UserManager userManager = UserManager.getInstance();
    private Util util;

    public static BookManager getInstance(Util util) {
        synchronized (BookManager.class) {
            if (bookManager == null) {
                bookManager = new BookManager();
                bookManager.util = util;
            }
        }
        return bookManager;
    }


    public void creatBook() {

        Scanner scan = new Scanner(System.in);
        System.out.println("Please fill the book information:");
        String author, name, genre;

        try {
            System.out.print("Book name:  ");
            name = scan.nextLine();
            System.out.print("Genre:  ");
            genre = scan.nextLine();

            Book newBook = Book.builder()
                    .author(new UserModel(userManager.getCurrentUser()))
                    .name(name)
                    .genre(genre)
                    .build();

            boolean isSaved = bookStorage.saveBook(newBook);
            if (isSaved) {
                System.out.println("Saved");
            } else {
                System.out.println("Already existing book");
                util.bookOptions();
            }

        } catch (InputMismatchException e) {
            e.printStackTrace();

        }
    }


    public void getByBook() {
        bookStorage.getMyBooks();
    }
}
