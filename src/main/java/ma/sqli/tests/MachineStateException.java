package ma.sqli.tests;

public class MachineStateException extends RuntimeException{
    public MachineStateException(){
        super("Machine already running");
    }
}
