import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static int books;
    static int libraries;
    static int scanningDays;

    public static void main(String[] args) throws FileNotFoundException {


//        String[] files= {"a_example.txt", "b_read_on.txt", "c_incunabula.txt", "d_tough_choices.txt" };
//        String[] files= {"a_example.txt"};
        String[] files= {"a_example.txt", "b_read_on.txt"};

        for(int i=0; i<files.length;i++) {


            String[] bookScores;

            Scanner fin = new Scanner(new File("Input Files" + File.separator + files[i]));

            books = fin.nextInt();
            libraries = fin.nextInt();
            scanningDays = fin.nextInt();

            String input = fin.nextLine();    // get the entire line after the prompt
            bookScores = input.split(" ");

            System.out.println(bookScores);

            for (int j = 0; j < libraries; j++) {
                si
            }
        }

    }



}
