package cards;

import java.util.*;

public class Cards {

    private int[] cards1;
    private int[] cards2;
    
    public Cards() {
        int[] cards = new int[36];
        for (int i = 0; i < 36; i++) cards[i] = i%6;
        cards = Cards.shuffleArray(cards);  
        this.cards1 = Arrays.copyOfRange(cards, 0, cards.length/2);
        this.cards2 = Arrays.copyOfRange(cards, cards.length/2, cards.length);
        
    }
    
    public String getCards1ToString() {
        return Arrays.toString(this.cards1);
    }

    public static int[] shuffleArray(int[] array) {
        int index, temp;
        Random random = new Random();
        for (int i = array.length - 1; i > 0; i--) {
            index = random.nextInt(i + 1);
            temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }
        return array;
    }
}
