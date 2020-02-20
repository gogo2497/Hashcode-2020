import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static int books;
    static int libraries;
    static int scanningDays;
    static int leftDaysScanning;
    static int daysleft;
    static double averageScoreOfBooks;

    public static void main(String[] args) throws IOException, FileNotFoundException {

//        String[] files= {"a_example.txt", "b_read_on.txt", "c_incunabula.txt", "d_tough_choices.txt" };
        String[] files= {"a_example.txt"};
//        String[] files= {"a_example.txt", "b_read_on.txt"};

        ArrayList<Library> libraryList = new ArrayList<>();
        String[] bookScores = null;


        for (int i = 0; i < files.length; i++) {


            Scanner fin = new Scanner(new File(files[i]));

            books = fin.nextInt();
            libraries = fin.nextInt();
            scanningDays = fin.nextInt();
            leftDaysScanning = scanningDays;
            fin.nextLine();

            String input = fin.nextLine();    // get the entire line after the prompt
            bookScores = input.split(" ");

            for (int x = 0; x < libraries; x++) {
                input = fin.nextLine();    // get the entire line after the prompt
                String[] libraryInfo = input.split(" ");

                // Check if library signup time is longer than days for scanning
                if (Integer.parseInt(libraryInfo[1]) <= scanningDays) {
                    Library lib = new Library(libraryInfo[0], libraryInfo[1], libraryInfo[2], x);
                    libraryList.add(lib);

                    input = fin.nextLine();    // get the entire line after the prompt
                    String[] booksInLibrary = input.split(" ");
                    for (int j = 0; j < booksInLibrary.length; j++) {
                        lib.addBook(new Book(booksInLibrary[j], bookScores[j]));
                    }
                    lib.sortBooksByScore();
                } else {
                    fin.nextLine();
                }
            }
        }

        for (int i = 0; i < libraryList.size(); i++) {
            System.out.println(libraryList.get(i));
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
        // Create the loop for the days ongoing
        boolean currentlyScanning = false;
        int daysLeftForScanning = 0;
        for (daysleft = scanningDays; daysleft > 0; daysleft--) {
            if (daysLeftForScanning > 0) {
                daysLeftForScanning--;
            } else {
                currentlyScanning = false;
            }

            // Weighting The libraries
            averageScoreOfBooks = 0;
            Long sum = null;
            int numOfBooksLeft = 0;
            for (int i = 0; i < bookScores.length; i++) {
                if (bookScores[i] != null) {
                    sum = sum + Long.parseLong(bookScores[i]);
                    numOfBooksLeft++;
                }
            }
            //Update Average score of books left
            averageScoreOfBooks = sum / averageScoreOfBooks;
            // Create library que
            Double[] arrayOfWeightedLibraries = new Double[libraryList.size()];
            arrayOfWeightedLibraries = WeightCalculation.getLibraryQue(libraryList, daysleft, averageScoreOfBooks);

            //Sign up a library from the que
            if (!currentlyScanning) {
//                arrayOfWeightedLibraries[0].sign

            }

            for (int i = 0; i < files.length; i++) {

                FileWriter myWriter = new FileWriter(i + ".txt");
                myWriter.write("Amount of libs to be signed up\n");

                //repeat
//            for (int j = 0; j < listOfLibraries; j++) {
//                myWriter.write("which lib and how many books?\n");
//                myWriter.write("scan these books from the above lib\n");
//            }
                myWriter.close();
            }
        }
    }
}
