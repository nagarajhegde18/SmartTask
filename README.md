Smart Task Scheduler
Overview
Smart Task Scheduler is a JavaFX-based desktop application that allows users to manage tasks efficiently using priority queues. Tasks can be added with a title, priority, and deadline. The app supports sorting by priority, filtering, reminders, and saving/loading tasks using JSON.

Features
Add tasks with title, priority (1-10), and deadline.
Priority-based sorting using PriorityQueue.
View all tasks in a list.

Filter tasks:
High priority tasks (priority ≥ 8)
Today’s tasks
Delete tasks from the list.
Reminders for tasks printed in the console.
Save and load tasks to/from tasks.json using Gson.
Built with JavaFX for a modern GUI.

Project Structure
SmartTaskScheduler/
├─ src/
│  ├─ model/Task.java
│  ├─ manager/TaskManager.java
│  ├─ util/JsonUtil.java
│  └─ app/TaskSchedulerApp.java
├─ lib/
│  └─ gson-2.11.0.jar
├─ javafx-sdk-21.0.8/ (JavaFX SDK)
└─ README.md

Prerequisites
Java 17+ installed
JavaFX SDK 21.0.8
Gson 2.11.0

Setup & Run (Windows, PowerShell)
Open PowerShell in the project folder.

Compile the project:
javac --module-path "C:\Users\hegdn\OneDrive\Desktop\SmartTaskScheduler\openjfx-21.0.8_windows-x64_bin-sdk\javafx-sdk-21.0.8\lib" --add-modules javafx.controls -cp ".;lib\gson-2.11.0.jar" -d out src\model\Task.java src\manager\TaskManager.java src\util\JsonUtil.java src\app\TaskSchedulerApp.java


Run the application:
java --module-path "C:\Users\hegdn\OneDrive\Desktop\SmartTaskScheduler\openjfx-21.0.8_windows-x64_bin-sdk\javafx-sdk-21.0.8\lib" --add-modules javafx.controls -cp "out;lib\gson-2.11.0.jar" app.TaskSchedulerApp

How to Use
Add Task: Enter title, select priority and deadline, then click Add Task.
View Tasks: Tasks are displayed sorted by priority and deadline.
Delete Task: Select a task in the list and click Delete Selected.
Filter Tasks: Use High Priority or Today’s Tasks buttons.
Save Tasks: Click Save Tasks to write tasks to tasks.json.
Load Tasks: Tasks are automatically loaded when the app starts.

Reminders: Console displays reminders when a task reaches its deadline.

Dependencies
JavaFX SDK 21.0.8
Gson 2.11.0

Future Enhancements
Desktop notification pop-ups for reminders.
Edit tasks functionality.
Color-coded priorities for better visualization.
Persistent database integration (SQLite/MySQL).

Author
Nagaraj Krishna Hegde
