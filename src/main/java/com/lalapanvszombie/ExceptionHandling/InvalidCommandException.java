package main.java.com.lalapanvszombie.ExceptionHandling;

public class InvalidCommandException extends java.lang.Exception {
    String message;
    
    public InvalidCommandException(String message) {
        this.message = message;
    }

    public String getMessage(){
        return String.format("Input command yang kamu masukkan tidak valid, nih!\n%s", message);
    }
}