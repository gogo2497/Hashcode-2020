import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    static int books;
    static int libraries;
    static int scanningDays;
    static int leftDaysScanning;
    static int daysleft;
    static double averageScoreOfBooks;

    public static void main(String[] args) throws IOException, FileNotFoundException {
        FileWriter myWriter = null;

//        String[] files= {"a_example.txt", "b_read_on.txt", "c_incunabula.txt", "d_tough_choices.txt",  "e_so_many_books.txt", "f_libraries_of_the_world.txt"};
//        String[] files= {"a_example.txt"};
//        String[] files = {"b_read_on.txt"};
//        String[] files = {"c_incunabula.txt"};
//        String[] files = {"d_tough_choices.txt"};
//        String[] files = {"e_so_many_books.txt"};
        String[] files = {"f_libraries_of_the_world.txt"};

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


        // Create the loop for the days ongoing
        boolean currentlySigningUp = false;
        int daysLeftForSigningUp = 0;
        for (daysleft = scanningDays; daysleft > 0; daysleft--) {
            if (daysLeftForSigningUp > 0) {
                daysLeftForSigningUp--;
            } else {
                // let the library scan books from now on and allow a new library to sign up
                currentlySigningUp = false;
                if(scanningDays != daysleft){
                    libraryList.get(libraries-1).signed();
//                    System.out.println("Library ID = "+ libraryList.get(0).ID + " Library weight = "
//                            + libraryList.get(libraries-1).weight + " Days left: " + daysleft);
                }


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


            //Create a new weighted list. Sign up the library with the biggest weight and add it to the signing up
            if (!currentlySigningUp) {
                // Calculate weights on library list
                WeightCalculation.assignAllLibrariesWeights(libraryList, daysleft, averageScoreOfBooks);
                //SORT THE libraryList
                Collections.sort(libraryList);
//                System.out.println("Library ID = "+ libraryList.get(0).ID + " Library weight = "
//                        + libraryList.get(0).weight);
                daysLeftForSigningUp = libraryList.get(libraries-1).getSignUpTime(); // CHANGE THE LIBRARY LIST
                currentlySigningUp = true;
                System.out.println("Library ID = "+ libraryList.get(0).ID + " Library weight = "
                        + libraryList.get(libraries-1).weight + " Days left: " + daysleft);
            }

            for (int m = 0; m < libraryList.size(); m++) {
                if (libraryList.get(m).isSigned) {
                    // You can scan books here
                    for (int k = 0; k < libraryList.get(m).getBooks().size(); k++) {
                        int bookId = Math.toIntExact(libraryList.get(m).getBooks().get(k).getID());
                        if (bookScores[bookId] != null) {
                            bookScores[bookId] = null;
                            libraryList.get(m).addScannedBook(libraryList.get(m).getBooks().get(k));
                        }
                    }
                }
            }
        }
//        for (int i = 0; i < libraryList.size(); i++) {
//            if (libraryList.get(i).isSigned) {
//                System.out.println(libraryList.get(i).ID);
//            }
//
//        }
//        for (int i = 0; i < libraryList.size(); i++) {
//            System.out.println(libraryList.get(i).weight);
//        }

        libraryList.removeIf(i -> i.scannedBooks.size() == 0);


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