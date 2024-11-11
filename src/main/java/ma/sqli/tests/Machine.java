package ma.sqli.tests;

// name, operating system, disk size, memory.
public class Machine {
    private String name;
    private String os;
    private String diskSize;
    private String memory;
    private MachineState state;

    public Machine(String name, String os, String diskSize, String memory){
        this.name = name;
        this.os = os;
        this.diskSize = diskSize;
        this.memory = memory;
        this.state = MachineState.INACTIVE;
    }

    public void run(){
        state = MachineState.RUNNING;
    }

    public void stop(){
        state = MachineState.STOPPED;
    }

    public boolean isRunning(){
        return state == MachineState.RUNNING;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getDiskSize() {
        return diskSize;
    }

    public void setDiskSize(String diskSize) {
        this.diskSize = diskSize;
    }

    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    public void setState(MachineState state) {
        this.state = state;
    }

    public MachineState getState() {
        return state;
    }

    @Override
    public String toString() {
        return name + ":" + state;
    }

    
}
