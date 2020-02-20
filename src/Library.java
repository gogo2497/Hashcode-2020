import com.sun.jdi.IntegerValue;

import java.util.ArrayList;

public class Library {

    int signUpTime;
    int booksPerScan;
    int amountOfBooks;
    ArrayList<Book> books = new ArrayList<>();

    public Library(String amountOfBooks, String signUpTime, String booksPerScan) {
        this.signUpTime = Integer.parseInt(signUpTime);
        this.booksPerScan = Integer.parseInt(booksPerScan);
        this.amountOfBooks = Integer.parseInt(amountOfBooks);
    }

    public void signup(Library library, int scanningDays) {
        scanningDays -= library.signUpTime;
    }

    public int getSignUpTime() {
        return signUpTime;
    }

    public void setSignUpTime(int signUpTime) {
        this.signUpTime = signUpTime;
    }

    public int getBooksPerScan() {
        return booksPerScan;
    }

    public void setBooksPerScan(int booksPerScan) {
        this.booksPerScan = booksPerScan;
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }

    public void addBook(Book book) {
        books.add(book);
    }
}
