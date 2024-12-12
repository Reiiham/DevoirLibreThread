import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonParser implements Runnable {
    @Override
    public void run() {
        System.out.println("Parsing started at: " + System.currentTimeMillis());

        try {
            // Lire le fichier input.json
            String inputData = new String(Files.readAllBytes(Paths.get(Main.input)));

            // Initialiser ObjectMapper et enregistrer le module pour les types Java 8
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            mapper.disable(com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS); // Désactiver timestamps pour LocalDate

            // Désérialiser JSON vers un tableau d'objets Order
            Order[] orders = mapper.readValue(inputData, Order[].class);

            // Parcourir chaque commande
            for (Order order : orders) {
                if (DatabaseHandler.isCustomerExists(order.getCustomerId())) {
                    // Ajouter dans la base de données
                    DatabaseHandler.insertOrder(order);

                    // Écrire dans le fichier output.json

                    FileProcessor.writeToFile(Main.output, order);
                } else {
                    // Écrire dans le fichier error.json
                    FileProcessor.writeToFile(Main.error, order);
                }
            }

            System.out.println("Parsing finished at: " + System.currentTimeMillis());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

