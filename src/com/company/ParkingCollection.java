package com.company;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class ParkingCollection {

    private final Map<String, Parking<Transport, Adding>> parkingStages;

    private final int pictureWidth;

    private final int pictureHeight;

    private final String separator = ":";

    public ParkingCollection(int pictureWidth, int pictureHeight) {
        this.pictureWidth = pictureWidth;
        this.pictureHeight = pictureHeight;
        parkingStages = new HashMap<>();
    }

    public Set<String> keys() {
        return parkingStages.keySet();
    }

    public void addParking(String name) {
        if (parkingStages.containsKey(name)) {
            return;
        }
        parkingStages.put(name, new Parking<>(pictureWidth, pictureHeight));
    }

    public void deleteParking(String name) {
        parkingStages.remove(name);
    }

    public Parking<Transport, Adding> get(String name) {
        if (parkingStages.containsKey(name)) {
            return parkingStages.get(name);
        }
        return null;
    }

    public Transport get(String name, int index) {
        if (parkingStages.containsKey(name)) {
            return parkingStages.get(name).get(index);
        }
        return null;
    }

    public boolean saveData(String filename) {
        if (!filename.contains(".txt")) {
            filename += ".txt";
        }
        try (FileWriter fileWriter = new FileWriter(filename, false)) {
            fileWriter.write("ParkingCollection\n");
            for (Map.Entry<String, Parking<Transport, Adding>> level : parkingStages.entrySet()) {
                fileWriter.write("Parking" + separator + level.getKey() + '\n');
                Transport transport;
                for (int i = 0; (transport = level.getValue().get(i)) != null; i++) {
                    if (transport.getClass().getSimpleName().equals("BusVehicle")) {
                        fileWriter.write("BusVehicle" + separator);
                    } else if (transport.getClass().getSimpleName().equals("AccordionBus")) {
                        fileWriter.write("AccordionBus" + separator);
                    }
                    fileWriter.write(transport.toString() + '\n');
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean loadData(String filename) {
        if (!(new File(filename).exists())) {
            return false;
        }

        try (FileReader fileReader = new FileReader(filename)) {
            Scanner scanner = new Scanner(fileReader);
            if (scanner.nextLine().contains("ParkingCollection")) {
                parkingStages.clear();
            } else {
                return false;
            }

            Transport bus = null;
            String key = "";
            String line;

            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                if (line.contains("Parking")) {
                    key = line.split(separator)[1];
                    parkingStages.put(key, new Parking<>(pictureWidth, pictureHeight));
                } else if (line.contains(separator)) {
                    if (line.contains("BusVehicle")) {
                        bus = new BusVehicle(line.split(separator)[1]);
                    } else if (line.contains("AccordionBus")) {
                        bus = new AccordionBus(line.split(separator)[1]);
                    }
                    if (!(parkingStages.get(key).add(bus))) {
                        return false;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean saveParking(String filename, String key) {
        if (!filename.contains(".txt")) {
            filename += ".txt";
        }
        try (FileWriter fileWriter = new FileWriter(filename, false)) {
            if (parkingStages.containsKey(key))
                fileWriter.write("Parking" + separator + key + '\n');
            Transport bus;
            for (int i = 0; (bus = parkingStages.get(key).get(i)) != null; i++) {
                if (bus.getClass().getSimpleName().equals("BusVehicle")) {
                    fileWriter.write("BusVehicle" + separator);
                } else if (bus.getClass().getSimpleName().equals("AccordionBus")) {
                    fileWriter.write("AccordionBus" + separator);
                }
                fileWriter.write(bus.toString() + '\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean loadParking(String filename) {
        try (FileReader fileReader = new FileReader(filename)) {
            Scanner scanner = new Scanner(fileReader);
            String key;
            String line;
            line = scanner.nextLine();
            if (line.contains("Parking:")) {
                key = line.split(separator)[1];
                if (parkingStages.containsKey(key)) {
                    parkingStages.get(key).clear();
                } else {
                    parkingStages.put(key, new Parking<>(pictureWidth, pictureHeight));
                }
            } else {
                return false;
            }
            Transport bus = null;
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                if (line.contains(separator)) {
                    if (line.contains("BusVehicle")) {
                        bus = new BusVehicle(line.split(separator)[1]);
                    } else if (line.contains("AccordionBus")) {
                        bus = new AccordionBus(line.split(separator)[1]);
                    }
                    if (!(parkingStages.get(key).add(bus))) {
                        return false;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
}