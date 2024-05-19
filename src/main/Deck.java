package src.main;
import java.util.ArrayList;
import java.util.List;

import src.main.AbstractClass.Plant;
public class Deck {
    private List<Plant> plants;
    private static final int MAX_SIZE = 6;

    public Deck() {
        plants = new ArrayList<>();
    }

    public boolean addPlant(Plant plant) {
        if (plants.size() < MAX_SIZE && !plants.contains(plant)) {
            plants.add(plant);
            return true;
        }
        return false;
    }

    public void swapPlant(Plant newPlant, String oldPlantName) {
        for (int i = 0; i < plants.size(); i++) {
            if (plants.get(i).getName().equals(oldPlantName)) {
                plants.set(i, newPlant);
                break;
            }
        }
    }

    public boolean isFull() {
        return plants.size() == MAX_SIZE;
    }

    public boolean hasPlant(String plantName) {
        return plants.stream().anyMatch(p -> p.getName().equals(plantName));
    }

    public List<String> getPlantNames() {
        List<String> names = new ArrayList<>();
        for (Plant plant : plants) {
            names.add(plant.getName());
        }
        return names;
    }

    public Plant getPlant(String plantName) {
        return plants.stream().filter(p -> p.getName().equals(plantName)).findFirst().orElse(null);
    }

   
    public List<Plant> getDeckPlants(){
            return plants;
    };
}


