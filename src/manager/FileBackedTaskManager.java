package manager;


import exeptions.*;
import history.HistoryManager;
import service.*;
import task.*;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FileBackedTaskManager extends InMemoryTaskManager {

    private final File saveFile;

    public FileBackedTaskManager(File saveFile) {
        this.saveFile = saveFile;
    }

    //----------------------------------------------------------------

    @Override
    public long addTask(Task task) {
        long id = super.addTask(task);
        if (id > 0) {
            save();
        }
        return id;
    }

    @Override
    public long addEpic(Epic epic) {
        long id = super.addEpic(epic);
        if (id > 0) {
            save();
        }
        return id;
    }

    @Override
    public long addSubTask(SubTask subTask) {
        long id = super.addSubTask(subTask);
        if (id > 0) {
            save();
        }
        return id;
    }

    @Override
    public void clearTaskHashMap() {
        super.clearTaskHashMap();
        save();
    }

    @Override
    public void clearEpicHashMap() {
        super.clearEpicHashMap();
        save();
    }

    @Override
    public void clearSubTaskHashMap() {
        super.clearSubTaskHashMap();
        save();
    }

    @Override
    public Task getTaskById(long id) {
        Task currenTask = super.getTaskById(id);
        if (currenTask != null) {
            save();
        }
        return currenTask;
    }

    @Override
    public Epic getEpicById(long id) {
        Epic currenEpic = super.getEpicById(id);
        if (currenEpic != null) {
            save();
        }
        return currenEpic;
    }

    @Override
    public SubTask getSubTaskById(long id) {
        SubTask currenSubtask = super.getSubTaskById(id);
        if (currenSubtask != null) {
            save();
        }
        return currenSubtask;
    }

    @Override
    public boolean updateTask(Task task) {
        boolean update = super.updateTask(task);
        if (update) {
            save();
        }
        return update;
    }

    @Override
    public boolean updateEpic(Epic epic) {
        boolean update = super.updateEpic(epic);
        if (update) {
            save();
        }
        return update;
    }

    @Override
    public boolean updateSubTask(SubTask subTask) {
        boolean update = super.updateSubTask(subTask);
        if (update) {
            save();
        }
        return update;
    }

    @Override
    public Task removeTask(long id) {
        Task currenTask = super.removeTask(id);
        if (currenTask != null) {
            save();
        }
        return currenTask;
    }

    @Override
    public Epic removeEpic(long id) {
        Epic currenEpic = super.removeEpic(id);
        if (currenEpic != null) {
            save();
        }
        return currenEpic;
    }

    @Override
    public SubTask removeSubTask(long id) {
        SubTask currenSubTask = super.removeSubTask(id);
        if (currenSubTask != null) {
            save();
        }
        return currenSubTask;
    }


    //--------SAVE and LOAD--------------------------------------------------------
    public static FileBackedTaskManager loadFromFile(File file) {

        FileBackedTaskManager fileBackedTaskManager = new FileBackedTaskManager(file);

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {

            bufferedReader.readLine();
            String currentReadingLine;
            while (bufferedReader.ready() && !(currentReadingLine = bufferedReader.readLine()).isEmpty()) {

                AbstractTask currentTask = fileBackedTaskManager.taskFromString(currentReadingLine);
                if (currentTask instanceof Task) {
                    fileBackedTaskManager.taskHashMap.put(currentTask.getId(), (Task) currentTask);

                } else if (currentTask instanceof SubTask) {
                    fileBackedTaskManager.subTaskHashMap.put(currentTask.getId(), (SubTask) currentTask);

                } else if (currentTask instanceof Epic) {
                    Epic epic = (Epic) currentTask;
                    fileBackedTaskManager.subTaskHashMap.values().stream().filter(el -> el.getEpicId() == epic.getId()).forEach(el -> epic.getEpicSubTaskList().add(el));
                    fileBackedTaskManager.epicHashMap.put(epic.getId(), epic);
                }

            }

            if (bufferedReader.ready()) {

                for (long id : historyFromString(bufferedReader.readLine())) {
                    if (fileBackedTaskManager.taskHashMap.containsKey(id)) {
                        fileBackedTaskManager.historyManager.add(fileBackedTaskManager.taskHashMap.get(id));

                    } else if (fileBackedTaskManager.epicHashMap.containsKey(id)) {
                        fileBackedTaskManager.historyManager.add(fileBackedTaskManager.epicHashMap.get(id));

                    } else if (fileBackedTaskManager.subTaskHashMap.containsKey(id)) {
                        fileBackedTaskManager.historyManager.add(fileBackedTaskManager.subTaskHashMap.get(id));
                    }
                }
            }

        } catch (IOException e) {
            throw new ManagerLoadException("load manager error");
        }

        return fileBackedTaskManager;
    }

    private void save() {

        try (PrintWriter printWriter = new PrintWriter(new FileWriter(saveFile))) {

            printWriter.println("id,type,name,status,description,epic");
            for (Task task : this.taskHashMap.values()) {
                printWriter.println(taskToString(task));
            }
            for (SubTask subTask : this.subTaskHashMap.values()) {
                printWriter.println(taskToString(subTask));
            }
            for (Epic epic : this.epicHashMap.values()) {
                printWriter.println(taskToString(epic));
            }
            if (historyToString(this.historyManager).length() > 0) {
                printWriter.println();
                printWriter.print(historyToString(this.historyManager));
            }

        } catch (IOException e) {
            throw new ManagerSaveException("save manager error");
        }
    }

    private String taskToString(AbstractTask task) {
        if (task instanceof Task) {
            return task.getId() + "," + TasksType.TASK + "," + task.getName() + "," + task.getStatus() + "," + task.getDescription();

        } else if (task instanceof Epic) {
            return task.getId() + "," + TasksType.EPIC + "," + task.getName() + "," + task.getStatus() + "," + task.getDescription();

        } else if (task instanceof SubTask) {
            return task.getId() + "," + TasksType.SUBTASK + "," + task.getName() + "," + task.getStatus() + "," + task.getDescription()
                    + "," + ((SubTask) task).getEpicId();
        }
        throw new ManagerSaveException("невозможно перевести обьект задачи в строку");
    }

    private static String historyToString(HistoryManager historyManager) {
        List<AbstractTask> taskHistory = historyManager.getHistory();
        StringBuilder stringTaskHistory = new StringBuilder();

        for (int i = 0; i < taskHistory.size(); i++) {
            stringTaskHistory.append(taskHistory.get(i).getId());
            if (i < taskHistory.size() - 1) {
                stringTaskHistory.append(",");
            }
        }
        return stringTaskHistory.toString();
    }


    private AbstractTask taskFromString(String value) {

        String[] valueStr = value.split(",");

        long id = Long.parseLong(valueStr[0]);
        String name = valueStr[2];
        Status status = Status.valueOf(valueStr[3]);
        String description = valueStr[4];

        AbstractTask newTask;
        switch (valueStr[1]) {
            case "SUBTASK":
                long epicId = Long.parseLong(valueStr[5]);
                newTask = new SubTask(epicId, name, status, description, id);
                break;
            case "TASK":
                newTask = new Task(name, status, description, id);
                break;
            case "EPIC":
                newTask = new Epic(name, status, description, id);
                break;
            default:
                throw new ManagerLoadException("невозможно получить обьект задачи из строки");
        }
        return newTask;
    }

    private static List<Long> historyFromString(String value) {
        String[] valueStr = value.split(",");
        return Arrays.stream(valueStr).map(Long::parseLong).collect(Collectors.toList());
    }
}
