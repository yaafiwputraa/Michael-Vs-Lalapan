package main.java.com.michaelvslalapan;

import java.util.Scanner;

public class MainMenu {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("");
            System.out.println("========== Plants vs. Zombies ==========");
            System.out.println("Main Menu:");
            System.out.println("1. Start");
            System.out.println("2. Help");
            System.out.println("3. Plants List");
            System.out.println("4. Zombies List");
            System.out.println("5. Exit");
            System.out.print("Masukkan input: ");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    startGame();
                    break;
                case 2:
                    displayHelp();
                    break;
                case 3:
                    displayPlantsList();
                    break;
                case 4:
                    displayZombiesList();
                    break;
                case 5:
                    isRunning = false;
                    break;
                default:
                    System.out.println("Input yang Anda masukkan tidak valid. Masukkan input baru.");
            }
        }

        scanner.close();
    }

    private static void startGame() {
        System.out.println("");
        System.out.println("Starting the game...");
        // Implementasi logic untuk memulai game
    }

    private static void displayHelp() {
        System.out.println("");
        System.out.println("Menampilkan help...");
        System.out.println("1. Start \n Menu ini digunakan untuk memulai game. Permainan dimulai dengan pemain mengatur deck tanaman yang akan dipakai.");
        System.out.println("2. Help \n Menu ini akan memberikan deskripsi dari permainan, arahan cara bermain untuk pemain, dan daftar command yang dapat dipakai.");
        System.out.println("3. Plants List \n Menu ini untuk menampilkan list dari informasi tanaman yang dapat digunakan dalam permainan.");
        System.out.println("4. Zombies List \n Menu ini untuk menampilkan list dari informasi zombie yang dapat muncul dalam permainan.");
        System.out.println("5. Exit \n Menu untuk keluar dari permainan.");
        // Implementasi logic untuk menampilkan help
    }

    private static void displayPlantsList() {
        System.out.println("");
        System.out.println("Menampilkan plants list...");
        // Implementasi logic untuk menampilkan plants list
    }

    private static void displayZombiesList() {
        System.out.println("");
        System.out.println("Menampilkan zombies list...");
        // Implementasi logic untuk menampilkan plants list
    }
}

