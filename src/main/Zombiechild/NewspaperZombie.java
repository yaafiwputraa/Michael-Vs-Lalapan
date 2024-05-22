package src.main.Zombiechild;

import src.main.AbstractClass.Plant;
import src.main.AbstractClass.Zombie;

public class NewspaperZombie extends Zombie {
    public NewspaperZombie(int x, int y) {
        super( 150, 100, 1, x, y, false);
    }
    
    // @Override
    // public boolean isSlowed() {
    //     return super.isSlowed();
    // }
    // public static void main(String[] args) {
    //     GameMap gameMap = new GameMap(11, 6);
    //     BucketheadZombie mybucketheadzombie = new BucketheadZombie(3, 4, gameMap);
    //     mybucketheadzombie.displayZombie();
    // }
    
    // @Override
    // public void excecute() {
       
    //     if (isZombieBergerak()) {
    //         super.bergerak();
    //     }
        
    //     Plant target = gameMap.getPlant(getX(), getY());
        
    //     if (target != null) {
    //         attackPlant(target);
    //     }
    // }
    
    public void rage(){
        if (health < 100){
            movementSpeedFactor = 1.2;
        }
    }

    @Override
    public void attack(Plant plant) {
        if (plant != null) {
            plant.reduceHealth(attackDamage);

        }
    }
    
}
