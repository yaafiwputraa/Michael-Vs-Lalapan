package main.java.com.michaelvslalapan.ExceptionHandling;

public class InvalidCommandException extends java.lang.Exception{
    String message;

    public InvalidCommandException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return String.format("Input command yang Anda masukkan tidak valid!\n%s", message);
    }
}
