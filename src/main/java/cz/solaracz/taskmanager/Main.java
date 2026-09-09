package cz.solaracz.taskmanager;

import cz.solaracz.taskmanager.model.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final List<Task> tasks = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;

        System.out.println("=== Správce úkolů (CLI) ===");

        while (running) {
            printMenu();
            System.out.print("Vyber možnost: ");
            String input = scanner.nextLine();

            switch (input) {
                case "1" -> listTasks();
                case "2" -> addTask();
                case "3" -> markTaskAsCompleted();
                case "0" -> {
                    running = false;
                    System.out.println("Aplikace ukončena.");
                }
                default -> System.out.println("Neplatná volba, zkus to znovu.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n--- MENU ---");
        System.out.println("1. Zobrazit všechny úkoly");
        System.out.println("2. Přidat nový úkol");
        System.out.println("3. Označit úkol jako hotový");
        System.out.println("0. Konec");
    }

    private static void listTasks() {
        System.out.println("\n--- Seznam úkolů ---");
        if (tasks.isEmpty()) {
            System.out.println("Zatím nemáš žádné úkoly.");
            return;
        }
        for (Task task : tasks) {
            System.out.println(task);
        }
    }

    private static void addTask() {
        System.out.print("Zadej název úkolu: ");
        String title = scanner.nextLine();
        System.out.print("Zadej popis úkolu: ");
        String description = scanner.nextLine();

        tasks.add(new Task(title, description));
        System.out.println("Úkol byl úspěšně přidán!");
    }

    private static void markTaskAsCompleted() {
        listTasks();
        if (tasks.isEmpty()) return;

        System.out.print("Zadej ID úkolu k dokončení: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            for (Task task : tasks) {
                if (task.getId() == id) {
                    task.setCompleted(true);
                    System.out.println("Úkol byl označen jako hotový!");
                    return;
                }
            }
            System.out.println("Úkol s tímto ID nebyl nalezen.");
        } catch (NumberFormatException e) {
            System.out.println("Zadaný vstup není platné číslo.");
        }
    }
}