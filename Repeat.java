import java.util.Scanner;

public class Repeat{

  static boolean firstTime = true;

    public static boolean repeat(int userChipTotal){
      if (!firstTime){
        // check if the user has any chips left to play again
        if (userChipTotal == 0){
          System.out.println("You are out of chips. Thanks for playing!");
          return false; 
        }
        // else ask if user wants to play again
        Scanner in = new Scanner(System.in);
        System.out.println("Chip total: " + userChipTotal);
        System.out.println("Would you like to play again?");
        System.out.println ("Y for Yes. N for No.");
        String name = in.nextLine();
        name = name.toUpperCase();
        if (name.equals("Y")){
          return true;
        }
      System.out.println("Thanks for playing!");
      return false; 
    }
    else {
      firstTime = false;
      return true;
    }
  }
}
