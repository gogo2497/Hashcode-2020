import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


public abstract class WeightCalculation {
//    int NumberOfDays = 123;
//    double AverageScoreOfBooks =12.3;

    public static void assignLibraryWeight(Library lib, int daysLeft, double averageScoreOfBooks){
        long maximumBooksTakenFromLibrary = lib.books.size() -
                (daysLeft - lib.getSignUpTime()) * lib.getBooksPerScan(); // Number of days can be the days that are currently left or the initial days
        if(maximumBooksTakenFromLibrary < 0){
            double weight = lib.getBooks().size() * averageScoreOfBooks;
            lib.setWeight(weight);
        } else{
            double weight =(lib.books.size() - maximumBooksTakenFromLibrary) * averageScoreOfBooks;
            lib.setWeight(weight);
        }
    }

    public static void assignAllLibrariesWeights(ArrayList<Library> libraryList, int daysLeft, double AverageScoreOfBooks){
        Double[] arrayOfWeights = new Double[libraryList.size()];
        for(int i=0; i < libraryList.size(); i++){
            assignLibraryWeight(libraryList.get(i), daysLeft, AverageScoreOfBooks);
        }
        // Sort the array
        Arrays.sort(arrayOfWeights, Collections.reverseOrder());
        }



}
