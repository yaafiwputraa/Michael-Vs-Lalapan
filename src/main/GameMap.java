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
    private int currentTime = 0; 
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
        if (currentSuns >= plant.cost && plant.x >= 0 && plant.x < width && plant.y >= 0 && plant.y < height) {
            plants.add(plant);
            currentSuns -= plant.cost;  // Deduct the cost of the plant from the current suns
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
    
        
        if (currentSuns < plant.cost) {
            System.out.println("Not enough suns to plant " + plant.getClass().getSimpleName());
            return false;
        }
    

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
    
        
        plants.add(plant);
        currentSuns -= plant.cost;
        return true;
    }

    public boolean isPlantPresent(int x, int y) {
        return plants.stream().anyMatch(p -> p.getX() == x && p.getY() == y);
    }

    public void removePlant(Plant plant) {
        plants.remove(plant);
    }
    
    public void removePlant(int x, int y) {
        plants.removeIf(plant -> plant.getX() == x && plant.getY() == y);
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
        return zombies;  
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
        return currentTime < 100; 
    }

    public boolean zombiesReachedFirstColumn() {
        return zombies.stream().anyMatch(z -> z.getX() == 0);
    }

    public void updateTime() {
        currentTime ++;
        if (currentTime >= 200) {
            currentTime = 0; 
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
        return new ArrayList<>(plants);  
    }

    public void printMap() {
        
        final String RESET = "\u001B[0m";
        final String GREEN_BACKGROUND = "\u001B[42m";
        final String BLUE_BACKGROUND = "\u001B[44m";
        final String PINK_BACKGROUND = "\u001B[45m";
        final String ZOMBIE = "\u001B[31mZ" + RESET;  
        final String SUNFLOWER = "\u001B[33mS" + RESET;  
        final String PEASHOOTER = "\u001B[32mP" + RESET;  
        final String REPEATER = "\u001B[32mR" + RESET;  
        final String WALLNUT = "\u001B[33mW" + RESET;  
        final String LILYPAD = "\u001B[36mL" + RESET;  
        final String TANGLEKELP = "\u001B[36mT" + RESET;  
        final String SQUASH = "\u001B[35mQ" + RESET;  
        final String CHOMPER = "\u001B[35mC" + RESET;  
        final String SNOWPEA = "\u001B[34mN" + RESET;  
        final String JALAPENO = "\u001B[31mJ" + RESET;
    
        
        String[][] grid = new String[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (isBase(i, j)) {
                    grid[i][j] = PINK_BACKGROUND + " " + RESET;  
                } else if (isPool(i, j)) {
                    grid[i][j] = BLUE_BACKGROUND + " " + RESET;  
                } else {
                    grid[i][j] = GREEN_BACKGROUND + " " + RESET;  
                }
            }
        }
    
        
        for (Plant plant : plants) {
            String plantSymbol = "P";  
            if (plant instanceof Sunflower) {
                plantSymbol = SUNFLOWER;
            } else if (plant instanceof Peashooter) {
                plantSymbol = PEASHOOTER;
            } else if (plant instanceof Repeater) {
                plantSymbol = REPEATER;
            } else if (plant instanceof Wallnut) {
                plantSymbol = WALLNUT;
            } else if (plant instanceof Lilypad) {
                plantSymbol = LILYPAD;
            } else if (plant instanceof TangleKelp) {
                plantSymbol = TANGLEKELP;
            } else if (plant instanceof Squash) {
                plantSymbol = SQUASH;
            } else if (plant instanceof Chomper) {
                plantSymbol = CHOMPER;
            } else if (plant instanceof Snowpea) {
                plantSymbol = SNOWPEA;
            } else if (plant instanceof Jalapeno) {
                plantSymbol = JALAPENO;
            }
            grid[plant.x][plant.y] = plantSymbol;
        }
    
        // Place zombies on the grid
        for (Zombie zombie : zombies) {
            grid[zombie.getX()][zombie.getY()] = ZOMBIE;  
        }
    
        // Print the grid to console
        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                System.out.print(grid[i][j]);
            }
            System.out.println();  
        }
        
    }

    public Plant getPlant(int x, int y) {
        for (Plant plant : plants) {
            if (plant.x == x && plant.y == y) {
                return plant;
            }
        }
        return null;
    }

    
}

