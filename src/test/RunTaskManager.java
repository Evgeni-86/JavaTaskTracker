package test;


import manager.TaskManager;
import task.*;
import service.Managers;

public class RunTaskManager {
    public static void main(String[] args) {
        TaskManager taskManager = Managers.getDefault();
        System.out.println(taskManager.getHistory());


        Task task_1 = new Task("Task 1", Status.NEW, "Task 1 test");
        Task task_2 = new Task("Task 2", Status.NEW, "Task 2 test");
        System.out.println(taskManager.addTask(task_1));
        System.out.println(taskManager.addTask(task_2));

        Epic epic_1 = new Epic("Epic 1", Status.NEW, "Epic 1 test");
        Epic epic_2 = new Epic("Epic 2", Status.NEW, "Epic 2 test");
        Epic epic_2_update = new Epic("Epic 2 update", Status.NEW, "Epic 2 test update");
        System.out.println(taskManager.updateEpic(epic_2_update));
        System.out.println(taskManager.addEpic(epic_1));
        System.out.println(taskManager.addEpic(epic_2));

        SubTask subTask_1_epic_1 = new SubTask(epic_1.getId(), "SubTask1 for Epic 1", Status.NEW, "SubTask1 for Epic 1 test");
        SubTask subTask_2_epic_1 = new SubTask(epic_1.getId(), "SubTask2 for Epic 1", Status.NEW, "SubTask2 for Epic 1 test");
        SubTask subTask_1_epic_2 = new SubTask(epic_2.getId(), "SubTask1 for Epic 2", Status.NEW, "SubTask for Epic 2 test");
        System.out.println(taskManager.addSubTask(subTask_1_epic_1));
        System.out.println(taskManager.addSubTask(subTask_2_epic_1));
        System.out.println(taskManager.addSubTask(subTask_1_epic_2));

        System.out.println(taskManager.getAllTaskHashMap());
        System.out.println(taskManager.getAllEpicHashMap());
        System.out.println(taskManager.getAllSubTaskHashMap());

        taskManager.getSubTaskById(subTask_2_epic_1.getId());

        SubTask subTask_2_epic_1_update = new SubTask(subTask_2_epic_1.getEpicId(), "SubTask2 for Epic 1 update", Status.DONE, "SubTask2 for Epic 1 test update");
        subTask_2_epic_1_update.setId(subTask_2_epic_1.getId());
        System.out.println(taskManager.updateSubTask(subTask_2_epic_1_update));

        System.out.println(taskManager.getAllEpicHashMap());
        System.out.println(taskManager.getAllSubTaskHashMap());

        taskManager.getTaskById(task_1.getId());
        taskManager.getTaskById(task_2.getId());
        taskManager.getEpicById(epic_1.getId());
        taskManager.getEpicById(epic_2.getId());
        taskManager.getSubTaskById(subTask_1_epic_1.getId());
        taskManager.getSubTaskById(subTask_2_epic_1_update.getId());

        System.out.println("------------------------------------");
        taskManager.getHistory().forEach(System.out::println);
    }
}
