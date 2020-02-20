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
            fin.nextLine();

            String input = fin.nextLine();    // get the entire line after the prompt
            bookScores = input.split(" ");

            for(int x=0; x < libraries; x++) {
                input = fin.nextLine();    // get the entire line after the prompt
                String[] libraryInfo = input.split(" ");

                // Check if library signup time is longer than days for scanning
                if (Integer.parseInt(libraryInfo[1]) <= scanningDays) {
                    Library lib = new Library(libraryInfo[0], libraryInfo[1], libraryInfo[2]);
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

        // Create the loop for the days ongoing
        for(daysleft = scanningDays; daysleft > 0; daysleft--){
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
            averageScoreOfBooks =  sum /averageScoreOfBooks;
            // Create library que
            double[] arrayOfWeights = new double[libraryList.size()];
            arrayOfWeights = WeightCalculation.getLibraryQue(libraryList,daysleft,averageScoreOfBooks);


            //Sign up a library from the que

        //File writer

        for(int i=0; i<files.length;i++) {

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
