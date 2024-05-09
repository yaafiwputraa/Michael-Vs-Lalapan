package main.java.com.michaelvslalapan;


import java.util.ArrayList;
import main.java.com.michaelvslalapan.AbstractClass.Plant;
// import main.java.com.michaelvslalapan.Deck;

public class Inventory {
    private ArrayList<Plant> inventory;
    private Deck deckPlants;
 
    public Inventory() {
        inventory = new ArrayList<>();
        deckPlants = new Deck();
    }

    public ArrayList<Plant> getInventory(){
        return inventory;
    }

    public Deck getDeck(){
        return deckPlants;
    }

    public void swapInventory (int a, int b){
        Plant buffer = inventory.get(a);
        inventory.set(a, inventory.get(b));
        inventory.set(b, buffer);
    }

    public void swapDeck (int a, int b){
        Plant buffer = deckPlants.getDeckPlants().get(a);
        deckPlants.getDeckPlants().set(a, deckPlants.getDeckPlants().get(b));
        deckPlants.getDeckPlants().set(b, buffer);
    }

    public void addPlantToDeck (int i){
        deckPlants.getDeckPlants().add(inventory.get(i));
    }

    public void deletePlantFromDeck (int i){
        deckPlants.getDeckPlants().remove(i);
    }

   public void addPlantToDeck (Plant p){
        deckPlants.getDeckPlants().add(p);
    }

    public void showInventory (){
        for (int i = 0; i < inventory.size(); i++){
            System.out.println((i+1)+ " ." + inventory.get(i).getName());
           // i++;
        }
    }

    
    // public void deletePlant (Plant plant){
    //     deck.getDeck().equals()
    //     for (Plant p: deck.getDeck()){
    //         if (deck.getDeck().equals(p))
    //     }
    // }


}