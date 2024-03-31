package com.client;

import com.common.TaskService;
import java.rmi.Naming;
import java.util.List;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class RMIClient {
    public static void main(String[] args) {
        try {
            TaskService taskService = (TaskService) Naming.lookup("rmi://localhost/TaskService");

            // Afficher un message lorsque le client est connecté au serveur
            System.out.println("Connecté au serveur RMI.");

            // Boucle pour permettre à l'utilisateur de saisir des commandes
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter output = new PrintWriter(System.out, true);

            String userInputLine;
            while ((userInputLine = userInput.readLine()) != null) {
                if ("1".equals(userInputLine)) {
                    // Demander à l'utilisateur de saisir la tâche à supprimer
                    System.out.println("Entrez la tâche à supprimer : ");
                    String taskToRemove = userInput.readLine();
                    taskService.removeTask(taskToRemove);
                } else if ("2".equals(userInputLine)) {
                    // Demander à l'utilisateur de saisir la tâche à ajouter
                    System.out.println("Entrez la nouvelle tâche : ");
                    String newTask = userInput.readLine();
                    taskService.addTask(newTask);
                } else if ("3".equals(userInputLine)) {
                    // Afficher la liste complète des tâches
                    List<String> tasks = taskService.getAllTasks();
                    if (tasks.isEmpty()) {
                        System.out.println("Aucune tâche n'a été trouvée.");
                    } else {
                        System.out.println("Liste des tâches : ");
                        for (String task : tasks) {
                            System.out.println(task);
                        }
                    }
                } else {
                    System.out.println("Commande invalide. Utilisez '1' pour supprimer une tâche, '2' pour ajouter une tâche, ou '3' pour afficher la liste des tâches.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
