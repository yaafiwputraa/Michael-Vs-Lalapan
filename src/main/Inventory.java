package src.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import src.main.AbstractClass.Plant;
import src.main.Plantchild.*;

public class Inventory {
    private Map<String, Plant> plants;

    public Inventory() {
        plants = new HashMap<>();
        plants.put("Peashooter", new Peashooter());
        plants.put("Sunflower", new Sunflower());
        plants.put("Repeater", new Repeater());
        plants.put("TangleKelp", new TangleKelp());
        plants.put("Lilypad", new Lilypad());
        plants.put("Squash", new Squash());
        plants.put("Wallnut", new Wallnut());
        plants.put("Chomper", new Chomper());
        plants.put("Snowpea", new Snowpea());
        plants.put("Jalapeno", new Jalapeno());
        // Add more plants as needed
    }

    public boolean hasPlant(String plantName) {
        return plants.containsKey(plantName);
    }

    public Plant getPlant(String plantName) {
        return plants.get(plantName);
    }

    public List<String> getPlantNames() {
        return new ArrayList<>(plants.keySet());
    }
}
