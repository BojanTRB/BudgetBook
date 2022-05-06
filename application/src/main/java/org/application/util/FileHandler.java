package org.application.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * JavaDoc this file!
 * Created: 05.05.2022
 *
 * @author Bojan
 */
public class FileHandler {
    private File data;


    public FileHandler() {
        boolean existedBefore = true;

        // File where all data will be stored
        data = new File("data.csv");

        // Creating new data file if not exists
        if (!data.exists()) {
            try {
                data.createNewFile();
                existedBefore = false;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (!existedBefore) {
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


    public List<String> readTransactions() {
        List<String> lines = Collections.emptyList();
        try {
            lines = Files.readAllLines(data.toPath());

            for (String line : lines) {
                String[] data = line.split(",");
                System.out.println(line);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return lines;
    }
}
