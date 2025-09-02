package manager;

import model.Task;
import java.util.PriorityQueue;
import java.util.List;
import java.util.stream.Collectors;

public class TaskManager {
    private PriorityQueue<Task> taskQueue = new PriorityQueue<>();

    public void addTask(Task task) { taskQueue.add(task); }
    public Task getNextTask() { return taskQueue.peek(); }
    public Task removeNextTask() { return taskQueue.poll(); }

    public List<Task> getAllTasks() {
        return taskQueue.stream().sorted().collect(Collectors.toList());
    }

    public List<Task> getHighPriorityTasks() {
        return taskQueue.stream().filter(t -> t.getPriority() >= 8).sorted().collect(Collectors.toList());
    }

    public List<Task> getTodayTasks() {
        return taskQueue.stream()
                .filter(t -> t.getDeadline().toLocalDate().equals(java.time.LocalDate.now()))
                .sorted()
                .collect(Collectors.toList());
    }
    
}
