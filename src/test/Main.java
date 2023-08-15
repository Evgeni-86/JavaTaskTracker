package test;

import manager.Manager;
import task.*;


public class Main {

    static Manager manager;

    public static void main(String[] args) {

        Task newTask1 = new Task()



        manager = new Manager();
        int inputGeneralMenu = Service.generalMenu();

        while (inputGeneralMenu != 0) {

            switch (inputGeneralMenu) {
                case 1:
                    Task newTask  = Service.createAnyTask(Task.class, null);
                    if (newTask != null){
                        manager.addTask(newTask);
                    }
                    System.out.println(manager.getAllTaskHashMap());
                    break;
                case 2:
                    Epic newEpic = Service.createAnyTask(Epic.class, null);
                    if (newEpic != null){
                        manager.addEpic(newEpic);
                    }
                    System.out.println(manager.getAllEpicHashMap());
                    break;
                case 3://РЕДАТИРОВАТЬ ЗАДАЧУ TASK
                    AbstractTask currentTask = choiceTask("task", null);
                    System.out.println(currentTask);
                    int choice = Service.options(currentTask);
                    taskFunctions(choice, currentTask);
                    break;
                case 4: //РЕДАКТИРОВАТЬ ЗАДАЧУ EPIC
                    AbstractTask currentEpic = choiceTask("epic", null);
                    System.out.println(currentEpic);
                    int choice2 = Service.options(currentEpic);

                    if (choice2 == 11) {
                        SubTask newSubTask = Service.createAnyTask(SubTask.class, (Epic) currentEpic);
                        if (newSubTask != null){
                            manager.addSubTask(newSubTask);
                        }
                    } else if (choice2 == 12) {
                        AbstractTask currentSubTask = choiceTask("subtask", (Epic) currentEpic);
                        int choice3 = Service.options(currentSubTask);
                        taskFunctions(choice3, currentSubTask);
                    } else {
                        taskFunctions(choice2, currentEpic);
                    }

                    break;
                case 5:
                    System.out.println(manager.getAllTaskHashMap());
                    System.out.println(manager.getAllEpicHashMap());
                    System.out.println(manager.getAllSubTaskHashMap());
                default:
                    break;
            }
            inputGeneralMenu = Service.generalMenu();
        }

    }

    //--ВЫБОР ЗАДАЧИ ИЗ СПИСКА ПО ID
    public static AbstractTask choiceTask(String typeTask, Epic currentEpic) {

        if (typeTask.equalsIgnoreCase("task")) {
            System.out.println(manager.getAllTaskHashMap());
            System.out.println("Введите id задачи Task");
            int idTask = Service.SC_int.nextInt();
            return manager.getTaskById(idTask);
        }
        if (typeTask.equalsIgnoreCase("epic")) {
            System.out.println(manager.getAllEpicHashMap());
            System.out.println("Введите id задачи Epic");
            int idEpic = Service.SC_int.nextInt();
            return manager.getEpicById(idEpic);
        }
        if (typeTask.equalsIgnoreCase("subtask")) {
            System.out.println(currentEpic.getEpicSubTaskList());
            System.out.println("Введите id задачи SubTask");
            int idSubTask = Service.SC_int.nextInt();
            return manager.getSubTaskById(idSubTask);
        }
        return null;
    }

    //--ОПЕРАЦИИ НАД ЗАДАЧЕЙ
    public static void taskFunctions(int choice, AbstractTask task) {

        switch (choice) {
            case 1 -> {
                Service.editTask(task);
                if (task instanceof Task) {
                    manager.updateTask((Task) task);
                }
                if (task instanceof Epic) {
                    manager.updateEpic((Epic) task);
                }
                if (task instanceof SubTask) {
                    manager.updateSubTask((SubTask) task);
                }
            }
            case 2 -> {
                task.setStatus(Status.DONE);
                if (task instanceof SubTask) {
                    manager.updateSubTask((SubTask) task);
                }
            }
            case 3 -> {
                if (task instanceof Task) {
                    manager.removeTask((Task) task);
                }
                if (task instanceof Epic) {
                    manager.removeEpic((Epic) task);
                }
                if (task instanceof SubTask) {
                    manager.removeSubTask((SubTask) task);
                }
            }
            default -> {
            }
        }

    }

}
