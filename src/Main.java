import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
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
//        String[] files= {"a_example.txt"};
        String[] files= {"b_read_on.txt"};

        ArrayList<Library> libraryList = new ArrayList<>();
        ArrayList<Library> libraryListEndingOrder = new ArrayList<>();
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

//        for (int m = 0; m < libraryList.size(); m++) {
//            if (libraryList.get(m).isSigned) {
//                // You can scan books here
//                for (int k = 0; k < libraryList.get(m).getBooks().size(); k++) {
//                    int bookId = Math.toIntExact(libraryList.get(m).getBooks().get(k).getID());
//                    if (bookScores[bookId] != null) {
//                        bookScores[bookId] = null;
//
//
//                        libraryList.get(m).addScannedBook(libraryList.get(m).getBooks().get(k));
//                    } else {
//                        continue;
//                    }
//                }
//            }
//        }
        // Create the loop for the days ongoing
        boolean currentlySigningUp = false;
        int daysLeftForSigningUp = 0;
        for (daysleft = scanningDays; daysleft > 0; daysleft--) {
            if (daysLeftForSigningUp > 0) {
                daysLeftForSigningUp--;

            } else {
                currentlySigningUp = false;
            }

            // Weighting The libraries
            averageScoreOfBooks = 0;
            Long sum = 0L;
            int numOfBooksLeft = 0;
            for (int i = 0; i < bookScores.length; i++) {
                if (bookScores[i] != null) {
                    sum = sum + Long.parseLong(bookScores[i]);
                    numOfBooksLeft++;
                }
            }
            //Update Average score of books left
            averageScoreOfBooks = sum / averageScoreOfBooks;


            //Sign up a library from the que
            if (!currentlySigningUp) {
                currentlySigningUp = true;
                // Create library que
                WeightCalculation.assignAllLibrariesWeights(libraryList, daysleft, averageScoreOfBooks);
//                arrayOfWeightedLibraries[0].sign
                //SIGN UP LIBRARY
                Collections.sort(libraryList);
                daysLeftForSigningUp = libraryList.get(0).getSignUpTime(); // CHANGE THE LIBRARY LIST
                libraryList.get(0).signed();
                libraryListEndingOrder.add(libraryList.get(0));
            }

            for (int m = 0; m < libraryListEndingOrder.size(); m++) {
                // You can scan books here
                for (int k = 0; k < libraryListEndingOrder.get(m).getBooksPerScan(); k++) {
                    int bookId = Math.toIntExact(libraryListEndingOrder.get(m).getBooks().get(k).getID());
                    if (bookScores[bookId] != null) {
                        bookScores[bookId] = null;
                        libraryList.get(m).addScannedBook(libraryList.get(m).getBooks().get(k));
                    }
                }
            }
        }

//        for (int i = 0; i < files.length; i++) {
//
//            FileWriter myWriter = new FileWriter(i + ".txt");
//            myWriter.write(libraryList.size() + "\n");
//
//            //repeat
//            for (int j = 0; j < libraryList.size(); j++) {
//                myWriter.write(""+(libraryList.get(j).ID+1)+" ");
//                myWriter.write(""+libraryList.get(j).scannedBooks.size()+"\n");
//                for (int k = 0; k < libraryList.get(j).scannedBooks.size(); k++) {
//                    myWriter.write(""+Math.toIntExact(libraryList.get(j).scannedBooks.get(k).ID)+" ");
//                }
//                myWriter.write("\n");
//            }
//            myWriter.close();
//        }
        for (int i = 0; i < files.length; i++) {

            PrintWriter writer = new PrintWriter(i + ".txt", StandardCharsets.UTF_8);
            writer.println(libraryList.size());
            int bob = 0;
            //repeat
            for (int j = 0; j < libraryList.size(); j++) {
                if (libraryList.get(j).scannedBooks.size() != 0) {
                    writer.print((libraryList.get(j).ID) + " ");
                    writer.println(libraryList.get(j).scannedBooks.size());
                    for (int k = 0; k < libraryList.get(j).scannedBooks.size(); k++) {
                        writer.print(Math.toIntExact(libraryList.get(j).scannedBooks.get(k).ID) + " ");
                    }
                } else {
                    bob += 1;
                }
                if (j < libraryList.size() - 1) {
                    writer.println();
                }

            }
            writer.close();
            System.out.println(bob);
        }
    }
}
