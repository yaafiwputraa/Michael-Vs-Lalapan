package src.main;
import java.util.ArrayList;
import java.util.List;

import src.main.AbstractClass.Plant;

public class Deck {
    private ArrayList<Plant> deckPlants;
    public static final int MAX_DECK_SIZE = 6; // Maximum number of plants in the deck

    public Deck(){
        deckPlants = new ArrayList<>();
    }

    public List<Plant> getDeckPlants(){
        return deckPlants;
    }

    public boolean isEmpty(){
        return deckPlants.isEmpty();
    }

    public boolean isSlotEmpty(int p){
        // Ensure index is within the bounds and check if the slot is empty
        return p >= 0 && p < deckPlants.size() && deckPlants.get(p) == null;
    }

    public void showDeckPlants(){
        if (!deckPlants.isEmpty()){
            for (int i = 0; i < deckPlants.size(); i++){
                System.out.println((i+1) + ". " + deckPlants.get(i).getName());
            }
        } else {
            System.out.println("Deck saat ini masih kosong");
        }
    }

    public boolean canStartGame() {
        return deckPlants.size() == MAX_DECK_SIZE; // Check if the deck is fully populated
    }
}
