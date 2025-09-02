package model;

import java.time.LocalDateTime;

public class Task implements Comparable<Task> {
    private String title;
    private int priority;
    private LocalDateTime deadline;

    public Task(String title, int priority, LocalDateTime deadline) {
        this.title = title;
        this.priority = priority;
        this.deadline = deadline;
    }

    public String getTitle() { return title; }
    public int getPriority() { return priority; }
    public LocalDateTime getDeadline() { return deadline; }

    public void setTitle(String title) { this.title = title; }
    public void setPriority(int priority) { this.priority = priority; }
    public void setDeadline(LocalDateTime deadline) { this.deadline = deadline; }

    @Override
    public int compareTo(Task other) {
        if (this.priority != other.priority) {
            return Integer.compare(other.priority, this.priority);
        } else {
            return this.deadline.compareTo(other.deadline);
        }
    }

    @Override
    public String toString() {
        return title + " | Priority: " + priority + " | Deadline: " + deadline.toLocalDate();
    }
}
