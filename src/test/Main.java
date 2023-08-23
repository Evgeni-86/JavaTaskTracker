package test;

import manager.Manager;
import task.Epic;
import task.Status;
import task.SubTask;
import task.Task;


public class Main {
    public static void main(String[] args) {
        Manager manager = new Manager();

        Task task1 = new Task("Task1", Status.NEW, "Task1Test");
        Task task2 = new Task("Task2", Status.NEW, "Task2Test");
        Epic epic1 = new Epic("Epic1", Status.NEW, "Epic1Test");


        manager.addTask(task1);
        manager.addTask(task2);
        manager.addEpic(epic1);

        SubTask subTask = new SubTask(epic1.getId(), "SubTask1", Status.NEW, "SubTaskTest1");

        System.out.println(manager.addSubTask(subTask));
        subTask.setStatus(Status.DONE);
        manager.updateSubTask(subTask);
        System.out.println(manager.getAllEpicHashMap());
        System.out.println(manager.removeSubTask(subTask));

        System.out.println(manager.getSubTaskById(subTask.getId()));

        System.out.println(manager.getAllEpicHashMap());
        System.out.println(manager.getAllSubTaskHashMap());

        SubTask subTask1 = new SubTask(epic1.getId(),"SubTask2", Status.NEW, "SubTaskTest2");
        subTask1.setId(subTask.getId());
        System.out.println(manager.updateSubTask(subTask1));

        Task task3 = new Task("Task2", Status.NEW, "Task2Test");
        task3.setId(task2.getId());
        System.out.println(manager.updateTask(task3));

        System.out.println(manager.getAllEpicHashMap());
        System.out.println(manager.getAllSubTaskHashMap());

//        manager.removeEpic(epic1);

        System.out.println(manager.getAllEpicHashMap());
        System.out.println(manager.getAllSubTaskHashMap());

        System.out.println(manager.getTaskById(task1.getId()));
        System.out.println(manager.getEpicById(epic1.getId()));

        Epic epicUpdate = new Epic("Epic1", Status.NEW, "Epic1Test");
        epicUpdate.setId(epic1.getId());
        System.out.println(manager.updateEpic(epicUpdate));


    }
}
