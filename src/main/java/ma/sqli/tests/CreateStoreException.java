package ma.sqli.tests;

public class CreateStoreException extends RuntimeException{

    public CreateStoreException() {
        super("Store Exists");
    }

}
