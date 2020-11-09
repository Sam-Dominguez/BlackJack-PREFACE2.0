import java.util.Scanner;
public class PlayerHand extends Hand {
int bet;

  public PlayerHand(int bet){
    this.bet = bet;
  }
// method to double 
  public void dub(Card card) {
     this.addCard(card);
     bet = bet*2;
     System.out.println("Your bet increased to " + bet + ".");
     System.out.println("You drew a " + card.face + ".");
  }
// method to split
  public PlayerHand split (){
     PlayerHand splitHand = new PlayerHand(this.bet);
     this.totalValue = this.totalValue - this.cards[1].value;
     splitHand.addCard(this.cards[1]);
     this.numCards = this.numCards - 1;
     return splitHand;
  }
  
}

  