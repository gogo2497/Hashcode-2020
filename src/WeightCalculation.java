import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


public abstract class WeightCalculation {
//    int NumberOfDays = 123;
//    double AverageScoreOfBooks =12.3;

    public static double getLibraryWeight(Library lib, int daysLeft, double averageScoreOfBooks){
        long maximumBooksTakenFromLibrary = lib.books.size() -
                (daysLeft - lib.getSignUpTime()) * lib.getBooksPerScan(); // Number of days can be the days that are currently left or the initial days
        if(maximumBooksTakenFromLibrary < 0){
            double weight = lib.getBooks().size() * averageScoreOfBooks;
            lib.setWeight(weight);
            return weight; //averageScoreOfBooks is the total score of books divided by the number of books
        } else{
            double weight =(lib.books.size() - maximumBooksTakenFromLibrary) * averageScoreOfBooks;
            lib.setWeight(weight);
            return weight; //averageScoreOfBooks is the total score of books divided by the number of books
        }
    }

    public static Double[] getLibraryQue(ArrayList<Library> libraryList,int daysLeft, double AverageScoreOfBooks){
        Double[] arrayOfWeights = new Double[libraryList.size()];
        for(int i=0; i < libraryList.size(); i++){
            arrayOfWeights[i] = getLibraryWeight(libraryList.get(i), daysLeft, AverageScoreOfBooks);
        }
        // Sort the array
        Arrays.sort(arrayOfWeights, Collections.reverseOrder());
        return arrayOfWeights;
        }



}
