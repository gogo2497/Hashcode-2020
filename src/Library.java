import com.sun.jdi.IntegerValue;

import java.util.ArrayList;
import java.util.Collections;

public class Library implements Comparable{

    int signUpTime;
    int booksPerScan;
    int amountOfBooks;
    boolean isSigned = false;
    int ID;
    double weight;
    ArrayList<Book> books = new ArrayList<>();
    ArrayList<Book> scannedBooks = new ArrayList<>();

    public Library(String amountOfBooks, String signUpTime, String booksPerScan, int ID) {
        this.signUpTime = Integer.parseInt(signUpTime);
        this.booksPerScan = Integer.parseInt(booksPerScan);
        this.amountOfBooks = Integer.parseInt(amountOfBooks);
        this.ID = ID;
    }

    public void signed() {
        isSigned = true;
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

    public double getWeight() {
        return this.weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
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

    public void addScannedBook(Book book) {
        scannedBooks.add(book);
    }

    public void sortBooksByScore() {
        Collections.sort(books);
    }

    @Override
    public int compareTo(Object comparestu) {
        Double compareage=((Library)comparestu).getWeight();
        return (int) (compareage-this.weight);
    }



    @Override
    public String toString() {
        return "Library{" +
                "signUpTime=" + signUpTime +
                ", booksPerScan=" + booksPerScan +
                ", amountOfBooks=" + amountOfBooks +
                ", books=" + books +
                '}';
    }
}
