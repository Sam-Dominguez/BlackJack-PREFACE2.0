
import java.util.Scanner;
import java.util.HashMap;

class Main {

  static int userChipValue = 500;
  static int curBet;
  static PlayerHand[] hands = new PlayerHand[5];
  static int numHands = 0;
  static int handsInPlay = 0;

  public static void main(String[] args) {
    //  creates a new deck
    Deck newDeck = new Deck();
    startGame();
    // loop to play game until told "n"
    while (Repeat.repeat(userChipValue)) {
      // create a new player hand
      PlayerHand playerHand = new PlayerHand(placeBet());
      hands[numHands] = playerHand;
      numHands++;
      // create the dealers hand
      Hand dealerHand = new Hand();
      // generate a card and assign it to a certain hand
      Card nextCard = newDeck.getCard();
      playerHand.addCard(nextCard);
      System.out.println("You drew a " + nextCard.face + ".");
      nextCard = newDeck.getCard();
      dealerHand.addCard(nextCard);
      System.out.println("The dealer drew a " + nextCard.face + ".");
      // present the user (hit/stand/double/split) options
      userChoose(newDeck, playerHand);

      if (playerHand.totalValue < 22){
        handsInPlay++;
      }

    if (handsInPlay > 0){
      dealerChoose(newDeck,dealerHand);
    }
      for (int i = 0; i<numHands; i++){
        // compare deck values from number of hands (numHands)
        whoWon(hands[i],dealerHand);
      }
      numHands = 0;
    }

  }

      public static void whoWon(PlayerHand playerHand, Hand dealerHand){
      // check if player bust
      if (playerHand.totalValue > 21) {
        System.out.println("You busted. Dealer wins.");
      } else {
        // check if dealer bust
        if (dealerHand.totalValue > 21) {
         System.out.println("Your total is " + playerHand.totalValue + ".");
          System.out.println("Dealer Busted. You win!");
          userWins(playerHand.totalValue,playerHand.bet);
        } 
        // check who has greater hand value
        else if (playerHand.totalValue < dealerHand.totalValue) {
          System.out.println("Your total is " + playerHand.totalValue + ".");
          System.out.println("Dealer closer to 21. You lose.");

        } else if (playerHand.totalValue > dealerHand.totalValue) {
          System.out.println("Your total is " + playerHand.totalValue + ".");
          System.out.println("You are closer to 21. You win!");
           userWins(playerHand.totalValue,playerHand.bet);
        } 
        // else a tie
        else {
          System.out.println("Your total is " + playerHand.totalValue + ".");
          System.out.println("Its a tie.");
          userChipValue = userChipValue + curBet;
          System.out.println("You get your original bet back.");
          System.out.println("Your chip total is " + userChipValue);
        }

      }
      }
     
  


  public static void startGame() {
    System.out.println("Welcome to the Blackjack Table");
    System.out.println("You have been given $" + userChipValue + " to start your game");

  }
   // asks user to place bet and checks if your bet is valid (you have enough chips to bid)
  public static int placeBet() {
    boolean validBet = false;
    while (!validBet){
      Scanner in = new Scanner(System.in);
      System.out.print("Place your bet: ");
      curBet = in.nextInt();
      if (userChipValue - curBet >= 0){
        userChipValue = userChipValue - curBet;
        System.out.println("Your bet: " + curBet);
        System.out.println("Chips Remaining: " + userChipValue);
        validBet = true;
      }
      else {
        System.out.println("Invalid bet, not enough chips. Place a lower bet.");
      }
    }
    return curBet;
  }
// method that handles all user options of play (hit,stand,double, or split)
  public static void userChoose(Deck newDeck, PlayerHand playerHand) {
    boolean isSplitHand = false;
    String userChoice = "h";
    PlayerHand splitHand;

    while (userChoice.equals("h")) {
      Card card = newDeck.getCard();
      playerHand.addCard(card);
      System.out.println("You drew a " + card.face + ".");
      System.out.println("Your total is: " + playerHand.totalValue);
      if (playerHand.totalValue > 21) {
        break;
      } else {
        // what if you give an invalid input
        Scanner in = new Scanner(System.in);
        if (playerHand.canSplit()){
          System.out.print("Would you like to hit (h), stand (s), double (d), or split (sp): ");
        }
        else if (playerHand.canDub() && (playerHand.bet * 2 <= userChipValue)) {
          System.out.print("Would you like to hit (h), stand (s), or double (d): ");
        } else {
          System.out.print("Would you like to hit (h) or stand (s): ");
        }
        userChoice = in.nextLine();
        userChoice = userChoice.toLowerCase();
        if (userChoice.equals("d")) {
          // code to double
          card = newDeck.getCard();
          userChipValue = userChipValue - playerHand.bet;
          playerHand.dub(card);
        }
        if (userChoice.equals("sp")) {
          //code to split
          splitHand = playerHand.split();
          userChoose(newDeck,splitHand);
          hands[numHands] = splitHand;
          numHands++;
          userChipValue = userChipValue - curBet;
          if (splitHand.totalValue < 22){
            handsInPlay++;
          }
          userChoice = "h";
        }
      }
    }
  }

  public static void dealerChoose(Deck deck, Hand dealerHand) {
    while (dealerHand.totalValue < 17) {
      Card card = deck.getCard();
      dealerHand.addCard(card);
      System.out.println("The dealer drew a " + card.face + ".");
      System.out.println("The dealer's total is " + dealerHand.totalValue + ".");
    }
  }

  public static void userWins(int userTotal, int bet) {
    int bonus;
    if (userTotal == 21) {
      bonus = (int) ((bet) * 2.5);
      userChipValue = userChipValue + bonus;
    } else {
      bonus = (bet) * 2;
      userChipValue = userChipValue + ((bet) * 2);
    }
    System.out.println("You earned " + bonus + " chips. Your chip total is " + userChipValue + ".");
  }
}