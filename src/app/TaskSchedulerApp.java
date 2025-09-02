package app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import manager.TaskManager;
import model.Task;
import util.JsonUtil;

import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;

public class TaskSchedulerApp extends Application {
    private TaskManager manager = new TaskManager();
    private ListView<String> taskListView = new ListView<>();
    private final String DATA_FILE = "tasks.json";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        // Load tasks from file
        try { JsonUtil.loadTasks(manager, DATA_FILE); } catch (Exception e) { e.printStackTrace(); }

        TextField titleField = new TextField();
        titleField.setPromptText("Task Title");

        Spinner<Integer> prioritySpinner = new Spinner<>(1, 10, 1);
        DatePicker datePicker = new DatePicker();

        Button addButton = new Button("Add Task");
        addButton.setOnAction(e -> {
            if (titleField.getText().isEmpty() || datePicker.getValue() == null) return;
            Task task = new Task(titleField.getText(), prioritySpinner.getValue(), datePicker.getValue().atStartOfDay());
            manager.addTask(task);
            updateList();
            scheduleReminder(task);
        });

        Button deleteButton = new Button("Delete Selected");
        deleteButton.setOnAction(e -> {
            int index = taskListView.getSelectionModel().getSelectedIndex();
            if (index >= 0) {
                Task task = manager.getAllTasks().get(index);
                manager.getAllTasks().remove(task);
                updateList();
            }
        });

        Button saveButton = new Button("Save Tasks");
        saveButton.setOnAction(e -> {
            try { JsonUtil.saveTasks(manager, DATA_FILE); } catch (Exception ex) { ex.printStackTrace(); }
        });

        Button filterTodayButton = new Button("Today's Tasks");
        filterTodayButton.setOnAction(e -> {
            taskListView.getItems().clear();
            manager.getTodayTasks().forEach(t -> taskListView.getItems().add(t.toString()));
        });

        Button filterHighButton = new Button("High Priority");
        filterHighButton.setOnAction(e -> {
            taskListView.getItems().clear();
            manager.getHighPriorityTasks().forEach(t -> taskListView.getItems().add(t.toString()));
        });

        VBox layout = new VBox(10, titleField, prioritySpinner, datePicker, addButton, deleteButton,
                filterTodayButton, filterHighButton, saveButton, taskListView);
        Scene scene = new Scene(layout, 400, 500);

        stage.setTitle("Smart Task Scheduler");
        stage.setScene(scene);
        stage.show();

        // Show initial tasks
        updateList();
    }

    private void updateList() {
        taskListView.getItems().clear();
        manager.getAllTasks().forEach(t -> taskListView.getItems().add(t.toString()));
    }

    private void scheduleReminder(Task task) {
        long delay = java.time.Duration.between(LocalDateTime.now(), task.getDeadline()).toMillis();
        if (delay <= 0) return;

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("Reminder: " + task.getTitle() + " is due today!");
            }
        }, delay);
    }
    
}
