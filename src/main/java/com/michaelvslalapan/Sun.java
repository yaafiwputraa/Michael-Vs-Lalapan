package main.java.com.michaelvslalapan;

import java.util.Random;

abstract class Sun{
    private static int totalSun = 0;

    public static void generateSun(){
        Random rand = new Random();
        int interval = rand.nextInt(6) + 5;
        new Thread(() -> {
            while (true){
                try {
                    Thread.sleep(interval * 1000);
                    totalSun += 25;
                    System.out.println("Generated 25 Sun! Total sun: " + totalSun);
                } catch (InterruptedException e){
                    //e.printStackTrace();
                }
            }
        }).start();
    }

    public static int getSun(){
        return totalSun;
    }

    public static void decreaseSun(int amount){
        totalSun -= amount;
        System.out.println("Decreased " + amount + " Sun! Total sun: " + totalSun);
    }

    public static void increaseSun(int amount){
        totalSun += amount;
        System.out.println("Increased " + amount + " Sun! Total sun: "+ totalSun);
    }
}