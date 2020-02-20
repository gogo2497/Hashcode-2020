import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static int books;
    static int libraries;
    static int scanningDays;
    static int leftDaysScanning;

    public static void main(String[] args) throws FileNotFoundException {

//        String[] files= {"a_example.txt", "b_read_on.txt", "c_incunabula.txt", "d_tough_choices.txt" };
        String[] files= {"a_example.txt"};
//        String[] files= {"a_example.txt", "b_read_on.txt"};

        ArrayList<Library> libraryList = new ArrayList<>();

        String[] bookScores = null;

        for(int i=0; i<files.length;i++) {

            Scanner fin = new Scanner(new File(files[i]));

            books = fin.nextInt();
            libraries = fin.nextInt();
            scanningDays = fin.nextInt();
            leftDaysScanning = scanningDays;
            fin.nextLine();

            String input = fin.nextLine();    // get the entire line after the prompt
            bookScores = input.split(" ");

            for(int x=0; x < libraries; x++) {
                input = fin.nextLine();    // get the entire line after the prompt
                String[] libraryInfo = input.split(" ");
                Library lib = new Library(libraryInfo[0], libraryInfo[1], libraryInfo[2]);
                libraryList.add(lib);

                input = fin.nextLine();    // get the entire line after the prompt
                String[] booksInLibrary = input.split(" ");
                for (int j = 0; j < booksInLibrary.length; j++) {
                    lib.addBook(new Book(booksInLibrary[j], bookScores[j]));
                }
            }
        }

        for (int i=0; i<libraryList.size(); i++) {
            System.out.println(libraryList.get(1).getBooks().get(3));

//            System.out.println(bookScores);

//            System.out.println(libraryList.get(i));
        }

        /*
            We need to sort the arraylist of libraries
         */
        for (int j = 0; j < libraryList.size(); j++) {
            if (leftDaysScanning < libraryList.get(j).getSignUpTime()) {
                continue;
            } else {
                leftDaysScanning = leftDaysScanning - libraryList.get(j).getSignUpTime();
                libraryList.get(j).signed();
            }
        }

        for (int m = 0; m < libraryList.size(); m++) {
            if (libraryList.get(m).isSigned) {
                // You can scan books here
                for (int k = 0; k < libraryList.get(m).getBooks().size(); k++) {
                    int bookId = Math.toIntExact(libraryList.get(m).getBooks().get(k).getID());
                    if (bookScores[bookId] != null) {
                        bookScores[bookId] = null;
                        libraryList.get(m).addScannedBook(libraryList.get(m).getBooks().get(k));
                    } else {
                        continue;
                    }
                }
            }
        }
    }
}
