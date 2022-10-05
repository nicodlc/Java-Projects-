package JavaAssignments;
import java.util.*;
public class changeCombinations {

    public static int coinValues[] = {25, 10, 5, 1}; //these are the currencies
    

    public static int change(int coinIndex, int remainingCents){
        
        if(remainingCents == 0){
            return 1;
        }

        if(remainingCents < 0){
            return 0;
        }

        int numCombinations = 0;
        for(int i = coinIndex; i < coinValues.length; i++){
            numCombinations += change(i, remainingCents-coinValues[i]);
        }

        return numCombinations;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int totalCents = input.nextInt();

        System.out.println(change(0, totalCents));
    }
}
