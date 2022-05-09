package org.application.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.*;

/**
 * JavaDoc this file!
 * Created: 05.05.2022
 *
 * @author Bojan
 */
public class FileHandler {
    private File data;
    private File config;


    public FileHandler() {
        boolean dataExistedBefore = true;

        // File where all data will be stored
        data = new File("data.csv");

        // Creating new data file if not exists
        if (!data.exists()) {
            try {
                data.createNewFile();
                dataExistedBefore = false;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Config where all configurations will be stored
        config = new File("config.txt");

        // Create new config file if not exists
            try {
                config.createNewFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        if (!dataExistedBefore) {
            // If it didn't exist before, add lines
            List<String> row = Arrays.asList("Type", "Company", "Money");

            try {
                BufferedWriter write = new BufferedWriter(new FileWriter(data));

                write.append(String.join(",", row));
                write.append("\n");
                write.flush();
                write.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        // Check config and add new configuration options

    }

    public boolean writeTransactions(String type, String company, double money) {

        if (!data.exists()) {
            System.out.println("Data File does not exists.");
            return false;
        }

        if (Double.isNaN(money)) {
            System.out.println("Money is not a Double.");
            return false;
        }

        List<String> row = Arrays.asList(type, company, Double.toString(money));

        try (BufferedWriter write = new BufferedWriter(new FileWriter(data, true))) {

            write.append(String.join(",", row));
            write.append("\n");
            write.flush();
            write.close();

        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean writeConfig(String key, String value) {

        // TODO (Add replace function)

        if (!config.exists()) {
            System.out.println("Config File does not exists.");
            return false;
        }

        List<String> row = Arrays.asList(key, value);

        try (BufferedWriter write = new BufferedWriter(new FileWriter(config, true))) {

            write.append(String.join(":", row));
            write.append("\n");
            write.flush();
            write.close();

        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    public List<String> readTransactions() {
        List<String> lines = Collections.emptyList();
        try {
            lines = Files.readAllLines(data.toPath());

            for (String line : lines) {
                String[] data = line.split(",");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return lines;
    }

    public HashMap<String, String> readConfig() {
        List<String> lines = Collections.emptyList();
        HashMap<String, String> config = new HashMap<>();

        try {
            lines = Files.readAllLines(this.config.toPath());

            for (String line : lines) {
                String[] map = line.split(":");

                config.put(map[0], map[1]);

            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return config;
    }
}
