package test;


import manager.TaskManager;
import task.*;
import service.Managers;

public class RunTaskManager {
    public static void main(String[] args) {
        TaskManager taskManager = Managers.getDefault();

        Epic epic_1 = new Epic("Epic 1", Status.NEW, "Epic 1 test");
        System.out.println(taskManager.addEpic(epic_1));

        SubTask subTask_1_epic_1 = new SubTask(epic_1.getId(), "SubTask1 for Epic 1", Status.NEW, "SubTask1 for Epic 1 test");
        SubTask subTask_2_epic_1 = new SubTask(epic_1.getId(), "SubTask2 for Epic 1", Status.NEW, "SubTask2 for Epic 1 test");
        SubTask subTask_3_epic_1 = new SubTask(epic_1.getId(), "SubTask3 for Epic 1", Status.NEW, "SubTask3 for Epic 1 test");
        System.out.println(taskManager.addSubTask(subTask_1_epic_1));
        System.out.println(taskManager.addSubTask(subTask_2_epic_1));
        System.out.println(taskManager.addSubTask(subTask_3_epic_1));

        Epic epic_2 = new Epic("Epic 2", Status.NEW, "Epic 2 test");
        System.out.println(taskManager.addEpic(epic_2));


        System.out.println(taskManager.getEpicById(epic_1.getId()));
        System.out.println(taskManager.getHistory());

        System.out.println(taskManager.getSubTaskById(subTask_2_epic_1.getId()));
        System.out.println(taskManager.getHistory());


        System.out.println(taskManager.getEpicById(epic_2.getId()));
        System.out.println(taskManager.getHistory());

        System.out.println(taskManager.getSubTaskById(subTask_1_epic_1.getId()));
        System.out.println(taskManager.getHistory());

        System.out.println(taskManager.getSubTaskById(subTask_3_epic_1.getId()));
        System.out.println(taskManager.getHistory());

        SubTask subTask_3_epic_1_update = new SubTask(epic_1.getId(), "SubTask3 for Epic 1 update", Status.DONE, "SubTask3 for Epic 1 test update");
        subTask_3_epic_1_update.setId(subTask_3_epic_1.getId());
        System.out.println(taskManager.updateSubTask(subTask_3_epic_1_update));

        System.out.println(taskManager.removeSubTask(subTask_3_epic_1_update.getId()));
        System.out.println(taskManager.removeSubTask(subTask_1_epic_1.getId()));
        System.out.println(taskManager.removeEpic(epic_1.getId()));


        System.out.println(taskManager.getHistory());

    }
}
