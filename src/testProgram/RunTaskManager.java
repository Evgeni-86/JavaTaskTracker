package testProgram;


import manager.FileBackedTaskManager;
import manager.TaskManager;
import service.Managers;
import service.Status;
import task.*;

import java.io.File;

public class RunTaskManager {
    public static void main(String[] args) {

//        //ЗАПИСЬ В ФАЙЛ
//        File file = new File("memory.csv");
//        FileBackedTaskManager fileBackedTaskManager = new FileBackedTaskManager(file);
//        Task task_1 = new Task("Task 1", Status.NEW,"Task 1 test");
//        Task task_2 = new Task(null,Status.NEW,null);
//        Task task_3 = new Task("Task 3",Status.NEW,"Task 3 test");
//        fileBackedTaskManager.addTask(task_1);//1
//        fileBackedTaskManager.addTask(task_2);//2
//        fileBackedTaskManager.addTask(task_3);//3
//        Epic epic_1 = new Epic("Epic 1", Status.NEW, "Epic 1 test");
//        fileBackedTaskManager.addEpic(epic_1);//4
//        SubTask subTask_1_epic_1 = new SubTask(epic_1.getId(), "SubTask1 for Epic 1", Status.NEW, "SubTask1 for Epic 1 test");
//        SubTask subTask_2_epic_1 = new SubTask(epic_1.getId(), "SubTask2 for Epic 1", Status.NEW, "SubTask2 for Epic 1 test");
//        fileBackedTaskManager.addSubTask(subTask_1_epic_1);//5
//        fileBackedTaskManager.addSubTask(subTask_2_epic_1);//6
//        fileBackedTaskManager.getTaskById(task_1.getId());//1
//        fileBackedTaskManager.getTaskById(task_3.getId());//3
//        fileBackedTaskManager.getEpicById(epic_1.getId());//4
//        fileBackedTaskManager.getSubTaskById(subTask_1_epic_1.getId());//5


//        TaskManager taskManager = Managers.getDefault();
//
//        Task task_1 = new Task("Task 1",Status.NEW,"Task 1 test");
//        System.out.println(taskManager.addTask(task_1));
//        System.out.println(taskManager.getTaskById(task_1.getId()));
//        Epic epic_1 = new Epic("Epic 1", Status.NEW, "Epic 1 test");
//        System.out.println(taskManager.addEpic(epic_1));
//
//        SubTask subTask_1_epic_1 = new SubTask(epic_1.getId(), "SubTask1 for Epic 1", Status.NEW, "SubTask1 for Epic 1 test");
//        SubTask subTask_2_epic_1 = new SubTask(epic_1.getId(), "SubTask2 for Epic 1", Status.NEW, "SubTask2 for Epic 1 test");
//        SubTask subTask_3_epic_1 = new SubTask(epic_1.getId(), "SubTask3 for Epic 1", Status.NEW, "SubTask3 for Epic 1 test");
//        System.out.println(taskManager.addSubTask(subTask_1_epic_1));
//        System.out.println(taskManager.addSubTask(subTask_2_epic_1));
//        System.out.println(taskManager.addSubTask(subTask_3_epic_1));
//
//        Epic epic_2 = new Epic("Epic 2", Status.NEW, "Epic 2 test");
//        System.out.println(taskManager.addEpic(epic_2));
//
//
//        System.out.println(taskManager.getEpicById(epic_1.getId()));
//        System.out.println(taskManager.getHistory());
//
//        System.out.println(taskManager.getSubTaskById(subTask_2_epic_1.getId()));
//        System.out.println(taskManager.getHistory());
//
//
//        System.out.println(taskManager.getEpicById(epic_2.getId()));
//        System.out.println(taskManager.getHistory());
//
//        System.out.println(taskManager.getSubTaskById(subTask_1_epic_1.getId()));
//        System.out.println(taskManager.getHistory());
//
//        System.out.println(taskManager.getSubTaskById(subTask_3_epic_1.getId()));
//        System.out.println(taskManager.getHistory());
//
//        SubTask subTask_3_epic_1_update = new SubTask(epic_1.getId(), "SubTask3 for Epic 1 update", Status.DONE, "SubTask3 for Epic 1 test update");
//        subTask_3_epic_1_update.setId(subTask_3_epic_1.getId());
//        System.out.println(taskManager.updateSubTask(subTask_3_epic_1_update));
//
////        System.out.println(taskManager.removeSubTask(subTask_3_epic_1_update.getId()));
////        System.out.println(taskManager.removeSubTask(subTask_1_epic_1.getId()));
////        System.out.println(taskManager.removeEpic(epic_1.getId()));
////        System.out.println(taskManager.removeEpic(epic_2.getId()));
//
//
//        System.out.println(taskManager.getHistory());
//
//        taskManager.clearEpicHashMap();
//
//        System.out.println(taskManager.getHistory());



//        //ВОССТАНОВЛЕНИЕ ИЗ ФАЙЛА
//        File file = new File("memory.csv");
//        FileBackedTaskManager fileBackedTaskManager1 = FileBackedTaskManager.loadFromFile(file);
//        System.out.println(fileBackedTaskManager1.getHistory());
//        System.out.println(fileBackedTaskManager1.getAllTaskHashMap());
//        System.out.println(fileBackedTaskManager1.getAllEpicHashMap());
//        System.out.println(fileBackedTaskManager1.getAllSubTaskHashMap());
//        System.out.println(fileBackedTaskManager1.getEpicById(4).getEpicSubTaskList());
    }
}
