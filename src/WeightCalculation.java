import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;


public class WeightCalculation {
    int NumberOfDays = 123;
    double AverageScoreOfBooks =12.3;

    public double getLibraryWeight(Library lib){
        long maximumBooksTakenFromLibrary = lib.books.size() -
                (NumberOfDays - lib.getSignUpTime()) * lib.getBooksPerScan(); // Number of days can be the days that are currently left or the initial days
        if(maximumBooksTakenFromLibrary < 0){
            return lib.getBooks().size() * AverageScoreOfBooks; //AverageScoreOfBooks is the total score of books divided by the number of books
        } else{
            return maximumBooksTakenFromLibrary * AverageScoreOfBooks; //AverageScoreOfBooks is the total score of books divided by the number of books
        }
    }

    public double[] getLibraryQue(ArrayList<Library> libraryList){
        double[] arrayOfWeights = new double[libraryList.size()];
        for(int i=0; i < libraryList.size(); i++){
            arrayOfWeights[i] = getLibraryWeight(libraryList.get(i));
        }
        // Sort the array
        Arrays.sort(arrayOfWeights);
        return arrayOfWeights;
        }

}
