package src.main;

import java.util.ArrayList;
import java.util.List;

import src.main.AbstractClass.Plant;
import src.main.AbstractClass.Zombie;
import src.main.Plantchild.*;

public class GameMap {
    private List<Plant> plants = new ArrayList<>();
    private List<Zombie> zombies = new ArrayList<>();
    private boolean[][] poolArea;
    private boolean[][] baseArea;

    private int currentSuns = 50;
    private int currentTime = 0; // Total time in seconds for the game
    private int width;
    private int height;

    public GameMap(int width, int height) {
        this.width = width;
        this.height = height;
        initializePoolArea();
        initializeBaseArea();
    }

    private void initializePoolArea() {
        poolArea = new boolean[width][height];
        // Setting the pool area at y = 2 and y = 3 for all x positions
        for (int x = 0; x < width; x++) {
            poolArea[x][2] = true;
            poolArea[x][3] = true;
        }
    }

    private void initializeBaseArea(){
        baseArea = new boolean[width][height];
        for (int y = 0; y < height; y++) {
            baseArea[0][y] = true;
        }
    }

    public boolean isBase(int x, int y){
        return baseArea[x][y];
    }

    public boolean isPool(int x, int y) {
        return poolArea[x][y];
    }


    private boolean hasLilypad(int x, int y) {
        return plants.stream().anyMatch(p -> p instanceof Lilypad && p.getX() == x && p.getY() == y);
    }

    public boolean addPlant(Plant plant) {
        if (currentSuns >= plant.getCost() && plant.getX() >= 0 && plant.getX() < width && plant.getY() >= 0 && plant.getY() < height) {
            plants.add(plant);
            currentSuns -= plant.getCost();  // Deduct the cost of the plant from the current suns
            return true;
        }
        System.out.println("Not enough suns or invalid position.");
        return false;
    }

    public boolean addPlant(Plant plant, int x, int y) {
        // Check if coordinates are within the map bounds
        if (x < 0 || x >= width || y < 0 || y >= height) {
            System.out.println("Invalid position: Outside of game map boundaries.");
            return false;
        }

        if (isBase(x, y)){
            System.out.println("Cannot plant on the base area.");
            return false;
        }
    
        // Check if there are enough suns to plant
        if (currentSuns < plant.getCost()) {
            System.out.println("Not enough suns to plant " + plant.getClass().getSimpleName());
            return false;
        }
    
        // Handle planting in the pool area
        if (isPool(x, y)) {
            if (plant instanceof Lilypad || plant instanceof TangleKelp) {
                if (isPlantPresent(x, y)) {
                    System.out.println("Cannot plant here: another plant is already present in this pool area.");
                    return false;
                }
            } else if (!hasLilypad(x, y)) {
                System.out.println("Non-aquatic plants can only be added to the pool on a Lilypad.");
                return false;
            }
        } else { // Non-pool area
            if (plant instanceof Lilypad || plant instanceof TangleKelp) {
                System.out.println("Lilypads and TangleKelp can only be planted in the pool area.");
                return false;
            }
            if (isPlantPresent(x, y)) {
                System.out.println("Cannot plant here: another plant is already present.");
                return false;
            }
        }
    
        // Add the plant and deduct suns
        plants.add(plant);
        currentSuns -= plant.getCost();
        System.out.println(plant.getClass().getSimpleName() + " added at (" + x + ", " + y + ")");
        return true;
    }

    public boolean isPlantPresent(int x, int y) {
        return plants.stream().anyMatch(p -> p.getX() == x && p.getY() == y);
    }

    public void removePlant(Plant plant) {
        plants.remove(plant);
        System.out.println("Plant removed from (" + plant.getX() + ", " + plant.getY() + ")");
    }
    
    public void removePlant(int x, int y) {
        plants.removeIf(plant -> plant.getX() == x && plant.getY() == y);
        System.out.println("Plant removed from (" + x + ", " + y + ")");
    }

    public void addZombie(Zombie zombie) {
        zombies.add(zombie);
    }

    public Zombie getZombieAt(int x, int y) {
        for (Zombie zombie : zombies) {
            if (zombie.getX() == x && zombie.getY() == y) {
                return zombie;
            }
        }
        return null;
    }

    public void removeDeadZombie(Zombie zombie) {
        zombies.removeIf(z -> !z.isAlive());
    }

    public void removeZombie(Zombie zombie) {
        zombies.remove(zombie);
    }

    public List<Zombie> getZombies() {
        return zombies;  // Return the list of zombies
    }

    public List<Zombie> getZombiesInColumn(int column) {
        List<Zombie> columnZombies = new ArrayList<>();
        for (Zombie zombie : zombies) {
            if (zombie.getY() == column) {
                columnZombies.add(zombie);
            }
        }
        return columnZombies;
    }

    public void addSuns(int suns) {
        currentSuns += suns;
    }

    public boolean isMorning() {
        return currentTime < 100; // Morning is the first 100 seconds of the 200-second cycle
    }

    public boolean zombiesReachedFirstColumn() {
        return zombies.stream().anyMatch(z -> z.getX() == 0);
    }

    public void updateTime() {
        currentTime ++;
        if (currentTime >= 200) {
            currentTime = 0; // Reset the cycle every 200 seconds
        }
    }

    public int getCurrentTime() {
        return currentTime;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getCurrentSuns() {
        return currentSuns;
    }

    public List<Plant> getAllPlants() {
        return new ArrayList<>(plants);  // Return a copy of the plant list
    }

    public void printMap() {
        // Initialize a grid representation
        String[][] grid = new String[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (i == 0){
                    grid[i][j] = "";
                }else{
                    grid[i][j] = "-";  // Empty space
                }
                
            }
        }

        // Place plants on the grid
        for (Plant plant : plants) {
            String plantSymbol = "P";  // Generic plant symbol
            if (plant instanceof Sunflower) {
                plantSymbol = "SF";  // Specific symbol for Sunflowers
            } else if (plant instanceof Peashooter) {
                plantSymbol = "P";  // Specific symbol for Peashooters
            } else if (plant instanceof Repeater) {
                plantSymbol = "R";  // Specific symbol for Repeaters
            } else if (plant instanceof Wallnut) {
                plantSymbol = "W";  // Specific symbol for Wallnuts
            } else if (plant instanceof Lilypad) {
                plantSymbol = "L";  // Specific symbol for Lilypads
            } else if (plant instanceof TangleKelp) {
                plantSymbol = "T";  // Specific symbol for TangleKelp
            } else if (plant instanceof Squash) {
                plantSymbol = "SQ";  // Specific symbol for Squash
            } else if (plant instanceof Chomper) {
                plantSymbol = "C";  // Specific symbol for Chomper
            } else if (plant instanceof Snowpea) {
                plantSymbol = "N";  // Specific symbol for Snowpea
            }
            grid[plant.getX()][plant.getY()] = plantSymbol;
        }

        // Place zombies on the grid
        for (Zombie zombie : zombies) {
            grid[zombie.getX()][zombie.getY()] = "Z";  // Zombie symbol
        }

        // Print the grid to console
        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                System.out.print(grid[i][j] + "");
            }
            System.out.println();  // New line at the end of each row
        }
        System.out.println("Current Suns: " + currentSuns);  // Display current suns
    }

    public Plant getPlant(int x, int y) {
        for (Plant plant : plants) {
            if (plant.getX() == x && plant.getY() == y) {
                return plant;
            }
        }
        return null;
    }

    
}
