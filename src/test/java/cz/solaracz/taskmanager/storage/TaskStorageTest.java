package cz.solaracz.taskmanager.storage;

import cz.solaracz.taskmanager.model.Task;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TaskStorageTest {
    // Použijeme jiný soubor než ostrá data, ať si nesmažeš reálné úkoly
    private static final String TEST_FILE = "test_tasks.json";
    private TaskStorage storage;

    // Spustí se VŽDY PŘED každým testem
    @BeforeEach
    void setUp() {
        Task.resetCounterForTests();
        storage = new TaskStorage(TEST_FILE);
    }

    // Spustí se VŽDY PO každém testu (uklidí po sobě dočasný soubor)
    @AfterEach
    void tearDown() {
        File file = new File(TEST_FILE);
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    void testSaveAndLoadTasks() {
        // 1. Arrange (Příprava dat)
        List<Task> originalTasks = new ArrayList<>();
        originalTasks.add(new Task("Nákup", "Koupit mléko a chleba"));
        originalTasks.add(new Task("Kódování", "Dopsat JUnit testy"));
        originalTasks.get(0).setCompleted(true);

        // 2. Act (Provedení akce – uložení a znovu načtení ze souboru)
        storage.saveTasks(originalTasks);
        List<Task> loadedTasks = storage.loadTasks();

        // 3. Assert (Ověření, zda data odpovídají)
        assertEquals(2, loadedTasks.size(), "Měly by být načteny 2 úkoly");
        assertEquals("Nákup", loadedTasks.get(0).getTitle());
        assertTrue(loadedTasks.get(0).isCompleted(), "První úkol má být hotový");
        assertEquals("Kódování", loadedTasks.get(1).getTitle());
        assertFalse(loadedTasks.get(1).isCompleted(), "Druhý úkol nemá být hotový");
    }

    @Test
    void testLoadEmptyFileReturnsEmptyList() {
        // Když soubor neexistuje, nesmí spadnout chyba, ale vrátit prázdný seznam
        List<Task> tasks = storage.loadTasks();
        assertNotNull(tasks);
        assertTrue(tasks.isEmpty(), "Teď by měl být prázdný seznam");
    }
}