package manager;


import exeptions.ManagerSaveException;
import history.HistoryManager;
import service.TasksType;
import task.*;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    public void loadFromFile(File file) {

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {

//            boolean readTask = true;//ЧТО ЧИТАЕМ В ДАННЫЙ МОМЕНТ

            bufferedReader.readLine();
            String currentReadingLine;
            while (bufferedReader.ready() && !(currentReadingLine = bufferedReader.readLine()).isEmpty()) {

                AbstractTask currentTask = taskFromString(currentReadingLine);
                if (currentTask instanceof Task) {
                    this.taskHashMap.put(currentTask.getId(), (Task) currentTask);

                } else if (currentTask instanceof SubTask) {
                    this.subTaskHashMap.put(currentTask.getId(), (SubTask) currentTask);

                } else {
                    Epic epic = (Epic) currentTask;
                    this.subTaskHashMap.values().stream().filter(el -> el.getEpicId() == epic.getId()).forEach(el -> epic.getEpicSubTaskList().add(el));
                    this.epicHashMap.put(epic.getId(), epic);
                }
            }

            if (bufferedReader.ready()) {

                for (long id : historyFromString(bufferedReader.readLine())) {
                    if (this.taskHashMap.containsKey(id)) {
                        this.historyManager.add(this.taskHashMap.get(id));

                    } else if (this.epicHashMap.containsKey(id)) {
                        this.historyManager.add(this.epicHashMap.get(id));

                    } else if (this.subTaskHashMap.containsKey(id)) {
                        this.historyManager.add(this.subTaskHashMap.get(id));
                    }
                }
            }

//            while (bufferedReader.ready()) {
//                String currentReadingLine = bufferedReader.readLine();
//                if (!currentReadingLine.isEmpty() && Character.isDigit(currentReadingLine.charAt(0))) {
//                    if (readTask) {
//                        //TODO т.к. метод taskFromString вернет AbstractTask то этот код сдесь или перенести
//                        AbstractTask currentTask = taskFromString(currentReadingLine);
//                        if (currentTask instanceof Task) {
//                            this.taskHashMap.put(currentTask.getId(), (Task) currentTask);
//
//                        } else if (currentTask instanceof SubTask) {
//                            this.subTaskHashMap.put(currentTask.getId(), (SubTask) currentTask);
//
//                        } else {
//                            Epic epic = (Epic) currentTask;
//                            this.subTaskHashMap.values().stream().filter(el -> el.getEpicId() == epic.getId()).forEach(el -> epic.getEpicSubTaskList().add(el));
//                            this.epicHashMap.put(epic.getId(), epic);
//                        }
//                    } else {
//                        //TODO т.к. МЕТОД historyFromString по заданию static тогда этот код здесь или перенести
//
//                        for (long id : historyFromString(currentReadingLine)) {
//                            if (this.taskHashMap.containsKey(id)) {
//                                this.historyManager.add(this.taskHashMap.get(id));
//
//                            } else if (this.epicHashMap.containsKey(id)) {
//                                this.historyManager.add(this.epicHashMap.get(id));
//
//                            } else if (this.subTaskHashMap.containsKey(id)) {
//                                this.historyManager.add(this.subTaskHashMap.get(id));
//                            }
//                        }
//                    }
//                }
//                if (currentReadingLine.isEmpty()) {
//                    readTask = false;
//                }
//            }

        } catch (IOException e) {
            throw new ManagerSaveException("load manager error");
        }
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

        } else {
            return task.getId() + "," + TasksType.SUBTASK + "," + task.getName() + "," + task.getStatus() + "," + task.getDescription()
                    + "," + ((SubTask) task).getEpicId();
        }
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
        /*
         * valueStr[0] - ID
         * valueStr[1] - type (TaskType)
         * valueStr[2] - name
         * valueStr[3] - status
         * valueStr[4] - description
         * valueStr[5] - epicID
         */
        switch (valueStr[1]) {
            case "SUBTASK" -> {
                long epicId = Long.parseLong(valueStr[5]);
                SubTask subTask = new SubTask(epicId, valueStr[2], Status.valueOf(valueStr[3]), valueStr[4]);
                subTask.setId(Long.parseLong(valueStr[0]));
                return subTask;
            }
            case "TASK" -> {
                Task task = new Task(valueStr[2], Status.valueOf(valueStr[3]), valueStr[4]);
                task.setId(Long.parseLong(valueStr[0]));
                return task;
            }
            default -> {
                Epic epic = new Epic(valueStr[2], Status.valueOf(valueStr[3]), valueStr[4]);
                epic.setId(Long.parseLong(valueStr[0]));
                return epic;
            }
        }
    }

    private static List<Long> historyFromString(String value) {
        String[] valueStr = value.split(",");
        return Arrays.stream(valueStr).map(Long::parseLong).collect(Collectors.toList());
    }
}
