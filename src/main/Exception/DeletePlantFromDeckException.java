package src.main.Exception;

public class DeletePlantFromDeckException extends Exception{
    public DeletePlantFromDeckException(){
        super("Tidak dapat menghapus tanaman dari deck karena deck kosong!");

    }
}
