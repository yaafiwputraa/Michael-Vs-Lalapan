package src.main;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private List<PlantType> allPlantTypes;
    private Deck deck;

    public Inventory() {
        this.allPlantTypes = new ArrayList<>();
        initializePlantTypes();
        this.deck = new Deck();
    }

    private void initializePlantTypes() {
        // Initialize with all plant types
        allPlantTypes.add(PlantType.PEASHOOTER);
        allPlantTypes.add(PlantType.SUNFLOWER);
        allPlantTypes.add(PlantType.WALLNUT);
        allPlantTypes.add(PlantType.SQUASH);
        allPlantTypes.add(PlantType.REPEATER);
        allPlantTypes.add(PlantType.SNOWPEA);
        allPlantTypes.add(PlantType.CHOMPER);
        allPlantTypes.add(PlantType.LILYPAD);
        allPlantTypes.add(PlantType.TANGLEKELP);
        allPlantTypes.add(PlantType.JALAPENO);
    }

    public List<PlantType> getAllPlantTypes() {
        return allPlantTypes;
    }

    public Deck getDeck() {
        return deck;
    }

    public void addPlantToDeck(int plantIndex) {
        final String ANSI_RESET = "\u001B[0m";
        final String ANSI_GREEN = "\u001B[32m";
        final String ANSI_RED = "\u001B[31m";
        if (plantIndex >= 0 && plantIndex < allPlantTypes.size()+1) {
            PlantType plantType = allPlantTypes.get(plantIndex-1);
            if (deck.contains(plantType)) {
                System.out.println(ANSI_RED+"Plant ini sudah ada dalam deck"+ANSI_RESET);
            } else {
                deck.addPlant(plantType);
                System.out.println(ANSI_GREEN+"Plant berhasil ditambahkan."+ANSI_RESET);
            }
        } else {
            System.out.println("Invalid plant selection.");
        }
    }

    public boolean removePlantFromDeck(int deckIndex) {
        if (deckIndex >= 0 && deckIndex < deck.getPlantTypes().size()) {
            return deck.removePlant(deckIndex);
        } else {
            System.out.println("Invalid index or empty slot. Cannot remove plant.");
            return false;
        }
    }

    public void swapPlantsInDeck(int index1, int index2) {
        deck.swapPlants(index1, index2);
    }
}
