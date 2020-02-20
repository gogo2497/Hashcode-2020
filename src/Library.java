import java.util.ArrayList;

public class Library {

    int signUpTime;
    int booksPerScan;
    ArrayList<Book> books = new ArrayList<>();

    public Library(int signUpTime, int booksPerScan, ArrayList<Book> books) {
        this.signUpTime = signUpTime;
        this.booksPerScan = booksPerScan;
        this.books = books;
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
}
