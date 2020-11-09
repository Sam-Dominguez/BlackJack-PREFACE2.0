public class Hand {
  Card[] cards;
  int totalValue = 0;
  int numCards;
  boolean hasAce;

  public Hand(){
   cards = new Card[10];
   totalValue = 0;
   numCards = 0;
   hasAce = false;
  }
  // adds the card from deck into a hand, checks if ace, updates total value
  public void addCard(Card card) {
    cards[numCards] = card;
    if (card.value == 11){
      hasAce = true;
    }
    numCards++;
    totalValue = totalValue + card.value;
    if (totalValue>21 && hasAce){
      totalValue = totalValue - 10;
      hasAce = false;
    }
  }
  // checks if the user can double (if first move)
  public boolean canDub (){
    return (numCards == 2);
  }
  // checks if user can split (first 2 cards are the same and first move of the hand)
  public boolean canSplit(){
    return ((numCards == 2) && (cards[0] == cards[1]));
    
  }
}