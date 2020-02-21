import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


public abstract class WeightCalculation {
//    int NumberOfDays = 123;
//    double AverageScoreOfBooks =12.3;

    public static void assignLibraryWeight(Library lib, int daysLeft, double averageScoreOfBooks){
//        averageScoreOfBooks = averageScoreOfBooks / 100;
        double maximumBooksTakenFromLibrary = lib.books.size() -
                (daysLeft - lib.getSignUpTime()) * lib.getBooksPerScan(); // Number of days can be the days that are currently left or the initial days


        if(!lib.isSigned){
//                System.out.println("FIRST TIME HERE ");
//            System.out.println("Library ID = "+ lib.ID + " Library weight = "
//                    + lib.weight);
//                System.out.println("READ HERE ");
        }

        if(lib.isSigned){
//            if(lib.ID == 0){
//                System.out.println("READ HERE ");
//            }
            lib.setWeight(0);
            return;
        }
        if(maximumBooksTakenFromLibrary < 0){
//            double weight = lib.getBooks().size() * averageScoreOfBooks;
            double weight = lib.getBooks().size();
//            if(lib.ID == 0){
//                System.out.println("FIRST TIME " + weight);
//            }
            lib.setWeight(weight);
        } else{
//            double weight = (lib.books.size() - maximumBooksTakenFromLibrary) * averageScoreOfBooks;
            double weight = (lib.books.size() - maximumBooksTakenFromLibrary);
//            if(lib.ID == 0){
//                System.out.println("FIRST TIME " + weight);
//            }
            lib.setWeight(weight);
        }
    }

    public static void assignAllLibrariesWeights(ArrayList<Library> libraryList, int daysLeft, double AverageScoreOfBooks){
        Double[] arrayOfWeights = new Double[libraryList.size()];
        for(int i=0; i < libraryList.size(); i++){
            assignLibraryWeight(libraryList.get(i), daysLeft, AverageScoreOfBooks);
        }
        // Sort the array
        }
}
