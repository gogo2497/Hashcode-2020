import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;


public abstract class WeightCalculation {
//    int NumberOfDays = 123;
//    double AverageScoreOfBooks =12.3;

    public double getLibraryWeight(Library lib, int daysLeft, double averageScoreOfBooks){
        long maximumBooksTakenFromLibrary = lib.books.size() -
                (daysLeft - lib.getSignUpTime()) * lib.getBooksPerScan(); // Number of days can be the days that are currently left or the initial days
        if(maximumBooksTakenFromLibrary < 0){
            return lib.getBooks().size() * averageScoreOfBooks; //averageScoreOfBooks is the total score of books divided by the number of books
        } else{
            return (lib.books.size() - maximumBooksTakenFromLibrary) * averageScoreOfBooks; //averageScoreOfBooks is the total score of books divided by the number of books
        }
    }

    public static double[] getLibraryQue(ArrayList<Library> libraryList,int daysLeft, double AverageScoreOfBooks){
        double[] arrayOfWeights = new double[libraryList.size()];
        for(int i=0; i < libraryList.size(); i++){
            arrayOfWeights[i] = getLibraryWeight(libraryList.get(i), daysLeft, AverageScoreOfBooks);
        }
        // Sort the array
        Arrays.sort(arrayOfWeights);
        return arrayOfWeights;
        }

}
