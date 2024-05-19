package src.main.Exception;

public class InvalidCommandException extends Exception{
   
    public InvalidCommandException() {
        super("Input command yang Anda masukkan tidak valid!");
    }
}
