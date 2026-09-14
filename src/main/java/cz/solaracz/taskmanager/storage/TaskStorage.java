package cz.solaracz.taskmanager.storage;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import cz.solaracz.taskmanager.model.Task;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TaskStorage {
    private final File file;
    private final ObjectMapper mapper;

    public TaskStorage(String filePath) {
        this.file = new File(filePath);
        this.mapper = new ObjectMapper();
        this.mapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    public List<Task> loadTasks() {
        if (!file.exists() || file.length() == 0) {
            return new ArrayList<>();
        }

        try {
            List<Task> tasks = mapper.readValue(file, new TypeReference<List<Task>>() {});

            // Nastavení počítadla ID podle nejvyššího existujícího ID
            int maxId = tasks.stream().mapToInt(Task::getId).max().orElse(0);
            Task.updateIdCounter(maxId);

            return tasks;
        } catch (IOException e) {
            System.err.println("Chyba při načítání souboru JSON: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public void saveTasks(List<Task> tasks) {
        try {
            // Vytvoření rodičovské složky, pokud neexistuje
            if (file.getParentFile() != null) {
                file.getParentFile().mkdirs();
            }
            mapper.writeValue(file, tasks);
        } catch (IOException e) {
            System.err.println("Chyba při ukládání do JSON souboru: " + e.getMessage());
        }
    }
}