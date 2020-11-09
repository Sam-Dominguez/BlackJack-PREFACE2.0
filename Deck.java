/* 
generates a card using a math.random method and looks 
up that value in a HashMap. The method getCard() will return a card for a hand to use.
*/

import java.util.HashMap;
public class Deck {
   HashMap<Integer, String> cardDict = new HashMap<Integer, String>();
   HashMap<String, Integer> cardDictReverse = new HashMap<String, Integer>();
   String[] tens = { "10", "Jack", "Queen", "King" };

  public Deck(){
    cardSetUp();
  }

  public Card getCard(){
    String face;                  
    int cardValue = (int) ((Math.random() * (12 - 2)) + 2);
    if (cardValue == 10) {
      face = tens[((int) ((Math.random() * (4 - 0)) + 0))];
    } else {
      face = cardDict.get(cardValue);
    }
    Card card = new Card(cardValue, face,cardValue == 11);
    return card;
  }

   private void cardSetUp() {
    cardDict.put(2, "two");
    cardDict.put(3, "three");
    cardDict.put(4, "four");
    cardDict.put(5, "five");
    cardDict.put(6, "six");
    cardDict.put(7, "seven");
    cardDict.put(8, "eight");
    cardDict.put(9, "nine");
    cardDict.put(11, "Ace");

    cardDictReverse.put("two", 2);
    cardDictReverse.put("three", 3);
    cardDictReverse.put("four", 4);
    cardDictReverse.put("five", 5);
    cardDictReverse.put("six", 6);
    cardDictReverse.put("seven", 7);
    cardDictReverse.put("eight", 8);
    cardDictReverse.put("nine", 9);
    cardDictReverse.put("10", 10);
    cardDictReverse.put("Jack", 10);
    cardDictReverse.put("Queen", 10);
    cardDictReverse.put("King", 10);
    cardDictReverse.put("Ace", 11);

  }



}