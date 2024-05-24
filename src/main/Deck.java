package src.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Deck {
    private List<PlantType> plantTypes;
    private static final int MAX_DECK_SIZE = 6;
    private Map<PlantType, Long> cooldowns;

    public Deck() {
        this.plantTypes = new ArrayList<>(MAX_DECK_SIZE);
        for (int i = 0; i < MAX_DECK_SIZE; i++) {
            plantTypes.add(null);
        }
    }

    public List<PlantType> getPlantTypes() {
        return plantTypes;
    }

    public boolean addPlant(PlantType plantType) {
        final String ANSI_RED = "\u001B[31m";
        final String ANSI_RESET = "\u001B[0m";
        for (int i = 0; i < plantTypes.size(); i++) {
            if (plantTypes.get(i) == null) {
                plantTypes.set(i, plantType);
                return true;
            }
        }
        System.out.println(ANSI_RED+"Deck sudah penuh, tidak bisa menambahkan tanaman lagi" + ANSI_RESET);
        return false;
    }

    public boolean removePlant(int index) {
        final String ANSI_RED = "\u001B[31m";
        final String ANSI_RESET = "\u001B[0m";
        if (index >= 0 && index < plantTypes.size() && plantTypes.get(index) != null) {
            plantTypes.set(index, null);
            return true;
        } else {
            System.out.println(ANSI_RED+"Indeks invalid atau tanaman pada indeks ini tidak ada."+ANSI_RESET);
            return false;
        }
    }

    public void swapPlants(int index1, int index2) {
        final String ANSI_RED = "\u001B[31m";
        final String ANSI_RESET = "\u001B[0m";
        if (index1 >= 0 && index1 < plantTypes.size() && index2 >= 0 && index2 < plantTypes.size() && index1 != index2) {
            PlantType temp = plantTypes.get(index1);
            plantTypes.set(index1, plantTypes.get(index2));
            plantTypes.set(index2, temp);
        } else {
            System.out.println(ANSI_RED+"Indeks invalid atau indeks sama. Tidak bisa menukar tanaman."+ANSI_RESET);
        }
    }

    public boolean isFull() {
        for (PlantType plantType : plantTypes) {
            if (plantType == null) {
                return false;
            }
        }
        return true;
    }

    public void showDeck() {
        final String ANSI_RESET = "\u001B[0m";
        final String ANSI_YELLOW = "\u001B[33m";
        final String ANSI_RED = "\u001B[31m";
        final String ANSI_CYAN = "\u001B[36m";
    
        System.out.println(ANSI_CYAN + "╔════╦════════════════════════╗" + ANSI_RESET);
        System.out.printf(ANSI_CYAN + "║ %-2s ║ %-22s ║\n" + ANSI_RESET, "No", "Plant Type");
        System.out.println(ANSI_CYAN + "╠════╬════════════════════════╣" + ANSI_RESET);
    
        for (int i = 0; i < plantTypes.size(); i++) {
            PlantType plantType = plantTypes.get(i);
            if (plantType != null) {
                System.out.printf(ANSI_YELLOW + "║ %-2d ║ %-22s ║\n" + ANSI_RESET, (i + 1), plantType);
            } else {
                System.out.printf(ANSI_RED + "║ %-2d ║ %-22s ║\n" + ANSI_RESET, (i + 1), "[KOSONG]");
            }
        }
    
        System.out.println(ANSI_CYAN + "╚════╩════════════════════════╝" + ANSI_RESET);
    }

    public boolean contains(PlantType plantType) {
        return plantTypes.contains(plantType);
    }

    public void setCooldown(PlantType plantType, long cooldownEndTime) {
        cooldowns.put(plantType, cooldownEndTime);
    }

    public boolean isOnCooldown(PlantType plantType) {
        if (cooldowns.containsKey(plantType)) {
            return System.currentTimeMillis() < cooldowns.get(plantType);
        }
        return false;
    }

}
