package main.java.com.michaelvslalapan;

import java.util.ArrayList;
import java.util.List;

import main.java.com.michaelvslalapan.AbstractClass.Plant;
import main.java.com.michaelvslalapan.AbstractClass.Zombie;
import main.java.com.michaelvslalapan.child_plant.Peashooter;
import main.java.com.michaelvslalapan.child_plant.Sunflower;
import main.java.com.michaelvslalapan.TileType;

public class GameMap {
    private List<Plant> plants = new ArrayList<>();
    private List<Zombie> zombies = new ArrayList<>();
    private int currentSuns = 50;
    private int currentTime = 0; // Total time in seconds for the game
    private int width;
    private int height;
    private TileType[][] tile_type;

    public GameMap(int width, int height) {
        this.width = width;
        this.height = height;
        this.tile_type = new TileType[width][height];
        initializeTileType();
    }

    public boolean addPlant(Plant plant) {
        if ((tile_type[plant.getX()][plant.getY()] == TileType.UMUM || tile_type[plant.getX()][plant.getY()] == TileType.POOL) && currentSuns >= plant.getCost() && plant.getX() >= 0 && plant.getX() < width && plant.getY() >= 0 && plant.getY() < height) {
            plants.add(plant);
            currentSuns -= plant.getCost();  // Deduct the cost of the plant from the current suns
            System.out.println("Plant added at (" + plant.getX() + ", " + plant.getY() + ")");
            return true;
        }
        System.out.println("Not enough suns or invalid position.");
        return false;
    }

    public void removePlant(int x, int y) {
        plants.removeIf(plant -> plant.getX() == x && plant.getY() == y);
        System.out.println("Plant removed from (" + x + ", " + y + ")");
    }

    public void addZombie(Zombie zombie) {
        
        TileType target = getTileType(zombie.getX(), zombie.getY());

        if ((zombie.isAquatic() && target == TileType.POOL) || (!zombie.isAquatic()))
        zombies.add(zombie);
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
                grid[i][j] = "_";  // Empty space
            }
        }

        // Place plants on the grid
        for (Plant plant : plants) {
            String plantSymbol = "P";  // Generic plant symbol
            if (plant instanceof Sunflower) {
                plantSymbol = "S";  // Specific symbol for Sunflowers
            } else if (plant instanceof Peashooter) {
                plantSymbol = "P";  // Specific symbol for Peashooters
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

    public void initializeTileType() {
        for (int i = 0; i < width; i++) { // default
            for (int j = 0; j < height; j++) {
                    tile_type[i][j] = TileType.UMUM;
            }
        }
        
        for (int i = 1; i <= 9; i++) {
            for (int j=2; j <= 3; j++) {
                tile_type[i][j] = TileType.POOL;
            }
        }

        for (int j = 0; j < height; j++) {
            tile_type[0][j] = TileType.PROTECTED;
        }

        for (int j = 0; j < height; j++) {
            tile_type[10][j] = TileType.SPAWN;
        }
        
    }
    
    public TileType getTileType(int x, int y) {
        if (x >= 0 && x < width && y >= 0 && y < height) {
            return tile_type[x][y];
        }
        else {
            return null;
        }
    }

    
}
