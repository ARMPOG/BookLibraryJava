package storage;

import manager.UserManager;
import models.Book;
import models.UserModel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BookStorage {

    private static BookStorage bookStorage;

    private BookStorage() {
    }

    UserManager userManager = UserManager.getInstance();

    public synchronized static BookStorage getInstance() {
        synchronized (UserStorage.class) {
            if (bookStorage == null) {
                bookStorage = new BookStorage();
            }
        }
        return bookStorage;
    }


    List<Book> books = new ArrayList<>();

    public boolean saveBook(Book book) {
        boolean isNotExist = true;
        if (!books.isEmpty() && books.stream().anyMatch(bookModel -> bookModel.getName().equalsIgnoreCase(book.getName()))) {
            isNotExist = false;
        } else {
            books.add(book);
        }
        return isNotExist;
    }

    public List<Book> getMyBooks() {
        List<Book> result  = new ArrayList<>();
        UserModel currentUser = userManager.getCurrentUser();
        result.addAll(books.stream().filter(book -> book.getAuthor().getEmail().equals(currentUser.getEmail())).collect(Collectors.toList()));
        return result;
    }

    public List<Book> getAllBooks(){
        List<Book> result = new ArrayList<>();
        result.addAll(books);
        return result;
    }
}
