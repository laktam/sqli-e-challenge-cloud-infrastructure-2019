package ma.sqli.tests;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CloudInfrastructure {
    private Map<String,List<String>> stores;
    private Map<String, Machine> machines;


    public CloudInfrastructure(){
        // linked hash map to have keys by insertion order
        stores = new LinkedHashMap<>();
        machines = new LinkedHashMap<>();
    }

    // add a key (create a store) with an empty list if it doesn't exist 
    public void createStore(String storeName) throws CreateStoreException{
        if(!stores.containsKey(storeName)) stores.put(storeName, new ArrayList<>());
        else throw new CreateStoreException();
    }

    public void uploadDocument(String storeName, String... documents) {
        List<String> store = stores.get(storeName);
        for(String doc: documents){
            store.add(doc);
        }
    }

    // "myFiles:empty||myImages:picture.jpeg, profile.png"
    public String listStores() {
        List<String> storesList = new ArrayList<>();
        for(Map.Entry<String, List<String>> entry : stores.entrySet()){
            String store = "";
            store += entry.getKey() + ":";
            if(entry.getValue().isEmpty()){
                store += "empty";
            }else{
                store += String.join(", ", entry.getValue());
            }
            storesList.add(store);
        }

        // add machines strings to the stores list (using a stream from the values of the map 
        // (a set of machines) to a list of trings)
        storesList.addAll(machines.values().stream().map(m -> m.toString()).collect(Collectors.toList()));
        
        return String.join("||", storesList);
    }

    public void deleteStore(String storeName) {
        stores.remove(storeName);
    }

    public void emptyStore(String storeName) {
        stores.put(storeName, new ArrayList<>());
    }

    public void createMachine(String name, String os, String disckSize, String memory) {
        machines.put(name, new Machine(name, os, disckSize, memory));
    }

    public void startMachine(String name) {
        if(machines.get(name).isRunning()) throw new MachineStateException();
        else machines.get(name).run();
    }

    public void stopMachine(String name) {
        machines.get(name).stop();
    }

    public double usedMemory(String string) {
        if(machines.get(string).isRunning()) {
            String machineMemory = machines.get(string).getMemory();
            return Double.parseDouble(machineMemory.substring(0, machineMemory.length() - 2));
        }
        return 0d;
    }

    public double usedDisk(String name) {
        if(stores.containsKey(name)){
            return stores.get(name).size() * 0.1d;
        }else{
            double disckSize = 0d;
            String machineDiskSize = machines.get(name).getDiskSize();
            disckSize += Double.parseDouble(machineDiskSize.substring(0, machineDiskSize.length() - 2));
            return disckSize;
        }
    }

    public double globalUsedDisk() {
        double globalUsedDisk = 0d;
        for(Machine machine : machines.values()){
            String machineDiskSize = machine.getDiskSize();
            globalUsedDisk += Double.parseDouble(machineDiskSize.substring(0, machineDiskSize.length() - 2));
            
        }
        for(List<String> storeFiles : stores.values()){
            globalUsedDisk += storeFiles.size() * 0.1d;
        }
        return globalUsedDisk;
    }

    public double globalUsedMemory() {
        double globalUsedMemory = 0d;
        for(Machine machine : machines.values()){
            if(machine.isRunning()){
                String machineMemory = machine.getMemory();
                globalUsedMemory += Double.parseDouble(machineMemory.substring(0, machineMemory.length() - 2));
            }
        }
        return globalUsedMemory;
    }

    public void empty() {
        machines.clear();
        stores.clear();
    }
}
