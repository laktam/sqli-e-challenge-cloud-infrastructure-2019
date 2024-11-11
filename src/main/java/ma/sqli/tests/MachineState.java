package ma.sqli.tests;

public enum MachineState {
    INACTIVE, RUNNING, STOPPED;

    public String toString(){
        return name().toLowerCase();
    }
}
