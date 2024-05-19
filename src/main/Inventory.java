package src.main;

import java.util.ArrayList;
import java.util.List;

import src.main.AbstractClass.Plant;

public class Inventory {
    private ArrayList<Plant> inventory;
    private Deck deck;

    public Inventory() {
        inventory = new ArrayList<>();
        deck = new Deck();
    }

    public ArrayList<Plant> getInventory(){
        return inventory;
    }

    public Deck getDeck(){
        return deck;
    }

    public void addPlantToInventory (Plant plant){
        inventory.add(plant);
    }

    public void swapInventory (int a, int b){
        if (a >= 0 && a < inventory.size() && b >= 0 && b < inventory.size()) {
            Plant buffer = inventory.get(a);
            inventory.set(a, inventory.get(b));
            inventory.set(b, buffer);
        }
    }

    public void swapDeck (int a, int b){
        List<Plant> deckPlants = deck.getDeckPlants();
        if (a >= 0 && a < deckPlants.size() && b >= 0 && b < deckPlants.size()) {
            Plant buffer = deckPlants.get(a);
            deckPlants.set(a, deckPlants.get(b));
            deckPlants.set(b, buffer);
        }
    }

    public void addPlantToDeck (int i){
        if (i >= 0 && i < inventory.size() && deck.getDeckPlants().size() < Deck.MAX_DECK_SIZE) {
            deck.getDeckPlants().add(inventory.get(i));
        }
    }

    public void deletePlantFromDeck (int i){
        if (i >= 0 && i < deck.getDeckPlants().size()) {
            deck.getDeckPlants().remove(i);
        }
    }

    public void showInventory (){
        for (int i = 0; i < inventory.size(); i++){
            System.out.println((i+1) + ". " + inventory.get(i).getName());
        }
    }

    public boolean addPlantToDeck2(int index) {
        if (index >= 0 && index < inventory.size() && !deck.getDeckPlants().contains(inventory.get(index)) && deck.getDeckPlants().size() < Deck.MAX_DECK_SIZE) {
            deck.getDeckPlants().add(inventory.get(index));
            return true; // Plant was successfully added to the deck
        }
        return false; // Failed to add the plant to the deck
    }
}

