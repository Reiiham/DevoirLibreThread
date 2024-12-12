import java.io.*;
import com.fasterxml.jackson.databind.*;
import java.util.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileProcessor {
    private static final Object lock = new Object(); // Object for synchronization
    // Méthode pour vider le contenu du fichier
    public static void clearFile(String filePath) {
        File file = new File(filePath);
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(""); // Écrire une chaîne vide pour vider le fichier
            System.out.println("Le fichier " + filePath + " a été vidé avec succès.");
        } catch (IOException e) {
            System.err.println("Erreur lors de la vidange du fichier " + filePath);
            e.printStackTrace();
        }
    }

    public static void writeToFile(String filePath, Order order) {
        synchronized (lock) {
            try {
                if (order == null) {
                    System.out.println("Order is null, not writing to file.");
                    return;
                }

                System.out.println("Preparing to write order to file: " + order);
                ObjectMapper mapper = new ObjectMapper();
                mapper.registerModule(new JavaTimeModule()); // Register JavaTimeModule for date/time handling
                mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

                File file = new File(filePath);
                List<Order> orders;

                // Check if the file exists and is not empty
                if (file.exists() && file.length() > 0) {
                    // Read existing orders from the file
                    //orders = Arrays.asList(mapper.readValue(file, Order[].class));
                    orders = new ArrayList<>(Arrays.asList(mapper.readValue(file, Order[].class)));
                } else {
                    // Initialize an empty list if the file is new or empty
                    orders = new ArrayList<>();
                }

                // Add the new order and write to the file
                orders.add(order);
                mapper.writerWithDefaultPrettyPrinter().writeValue(file, orders);
                System.out.println("Order written successfully to " + filePath);
            } catch (IOException e) {
                System.err.println("Error writing to file: " + filePath);
                e.printStackTrace();
            }
        }
    }
}