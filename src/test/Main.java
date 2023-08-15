package test;

import manager.Manager;
import task.Epic;
import task.Status;
import task.SubTask;
import task.Task;

import java.security.Key;
import java.util.Iterator;
import java.util.Map;

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
        manager.addSubTask(subTask);
        manager.removeSubTask(subTask);

        System.out.println(manager.getAllEpicHashMap());
        System.out.println(manager.getAllSubTaskHashMap());

        SubTask subTask1 = new SubTask(epic1.getId(),"SubTask2", Status.NEW, "SubTaskTest2");
        subTask1.setId(subTask.getId());
        System.out.println(manager.updateSubTask(subTask1));

        System.out.println(manager.getAllEpicHashMap());
        System.out.println(manager.getAllSubTaskHashMap());

        manager.removeEpic(epic1);

        System.out.println(manager.getAllEpicHashMap());
        System.out.println(manager.getAllSubTaskHashMap());


    }
}
