package cz.solaracz.taskmanager.model;

public class Task {
    private static int idCounter = 1;

    private final int id;
    private String title;
    private String description;
    private boolean completed;

    public Task(String title, String description) {
        this.id = idCounter++;
        this.title = title;
        this.description = description;
        this.completed = false;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public boolean isCompleted() { return completed; }
    public void setCompleted(boolean completed) { this.completed = completed; }

    @Override
    public String toString() {
        String status = completed ? "[✓]" : "[ ]";
        return String.format("%s ID %d: %s - %s", status, id, title, description);
    }
}