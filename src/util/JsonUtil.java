package util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.Task;
import manager.TaskManager;

import java.io.*;
import java.util.List;

public class JsonUtil {
    private static final Gson gson = new Gson();

    public static void saveTasks(TaskManager manager, String filename) throws IOException {
        List<Task> tasks = manager.getAllTasks();
        try (Writer writer = new FileWriter(filename)) {
            gson.toJson(tasks, writer);
        }
    }

    public static void loadTasks(TaskManager manager, String filename) throws IOException {
        try (Reader reader = new FileReader(filename)) {
            List<Task> tasks = gson.fromJson(reader, new TypeToken<List<Task>>(){}.getType());
            if (tasks != null) {
                for (Task task : tasks) manager.addTask(task);
            }
        }
    }
}
