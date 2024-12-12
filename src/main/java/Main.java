public class Main {
        private static final long INTERVAL_MS = 30 * 60 * 1000; // 30 minutes in milliseconds
        public static String input = "src/main/resources/data/input.json"; // Relative paths
        public static String output = "src/main/resources/data/output.json";
        public static String error = "src/main/resources/data/error.json";

        public static void main(String[] args) {
            Thread taskThread = new Thread(() -> {
                while (!Thread.currentThread().isInterrupted()) {
                    try {
                        System.out.println("Le processus commence...");

                        // Execute task
                        JsonParser parseJsonTask = new JsonParser();
                        parseJsonTask.run();

                        // Clear input file after task
                        FileProcessor.clearFile(input);

                        System.out.println("Tache complete. En attente");

                        // Sleep for the interval
                        Thread.sleep(INTERVAL_MS);
                    } catch (InterruptedException e) {
                        System.out.println("Tache interropue. Exiting.");
                        Thread.currentThread().interrupt();
                        break;
                    } catch (Exception e) {
                        System.err.println("Erreur.");
                        e.printStackTrace();
                    }
                }
            });

            // Start the thread
            taskThread.start();
            System.out.println("Thread commence. Chaque tache s execute chaque 30min");
        }
    }

   /*
   public class Main {
    // for loop == test
    private static final long INTERVAL_MS = 1 * 60 * 1000; // 3 minutes en millisecondes
    private static final int NUM_EXECUTIONS = 3; // Nombre d'exécutions
    public static String input = "src/main/resources/data/input.json";
    public static String output = "src/main/resources/data/output.json";
    public static String error = "src/main/resources/data/error.json";
    public static void main(String[] args) {

        //while(true){
        for (int i = 0; i < NUM_EXECUTIONS; i++) {
            try {
                System.out.println("Exécution " + (i + 1) + " démarrée.");

                // Exécuter la tâche de traitement
                JsonParser parseJsonTask = new JsonParser();
                parseJsonTask.run();

                // Vider le fichier input.json après l'exécution

                //FileProcessor.clearFile("C://Users//Moi//IdeaProjects//DevoirLibreThread//src//main//java//data//input.json");
                FileProcessor.clearFile(input);

                System.out.println("Exécution " + (i + 1) + " terminée.");

                // Attendre 3 minutes avant la prochaine exécution
                if (i < NUM_EXECUTIONS - 1) {
                    Thread.sleep(INTERVAL_MS);
                }
            } catch (InterruptedException e) {
                System.out.println("Le programme a été interrompu.");
                break;
            } catch (Exception e) {
                System.err.println("Erreur lors de l'exécution de la tâche.");
                e.printStackTrace();
            }
        }
        System.out.println("Le programme s'est terminé après " + NUM_EXECUTIONS + " exécutions.");
    }
}

*/

