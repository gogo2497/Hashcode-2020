import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        String[] files= {"a_example.txt", "b_read_on.txt", "c_incunabula.txt", "d_tough_choices.txt" };

        for(int i=0; i<files.length;i++) {

            Scanner fin = new Scanner(new File("Input Files" + File.separator + files[i]));
            rows = fin.nextInt();
            columns = fin.nextInt();
            vehicles = fin.nextInt();
            rides = fin.nextInt();
            bonus = fin.nextInt();
            steps = fin.nextInt();
        }

    }
}
