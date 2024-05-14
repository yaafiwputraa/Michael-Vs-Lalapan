package main.java.com.michaelvslalapan;
import main.java.com.michaelvslalapan.AbstractClass.Plant;

import java.util.ArrayList;
import java.util.List;

public class Deck {
    private ArrayList<Plant> deckPlants;

    public Deck(){
        deckPlants = new ArrayList<>();
    }

    public List<Plant> getDeckPlants(){
        return deckPlants;
    }

    public boolean isEmpty(){
        return deckPlants.isEmpty();
    }

    public boolean isFull() {
       return deckPlants.size() == 6;
    }

    public boolean isSlotEmpty(int p){
        return deckPlants.get(p) == null;
    }

    public void showDeckPlants(){
        if (!deckPlants.isEmpty()){
            for (int i = 0; i < deckPlants.size(); i++){
                System.out.println((i+1) + " ." + deckPlants.get(i).getName());
                i++;
            }
        } 
        else {
            System.out.println("Deck saat ini masih kosong");
        }
    }
        
    public boolean canStartGame() {
        return isFull(); // cek jika deck full mentok --> 6 biji di dlmny
    }
    /* utk di main nnti */
    public void startGameDeck(Deck deck) {
        if (deck.canStartGame()) {
            System.out.println("Permainan dimulai!");
        } 
        else {
            System.out.println("Tidak dapat memulai permainan karena deck belum penuh!");
        }
    }


    
}

