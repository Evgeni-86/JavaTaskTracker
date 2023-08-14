package test;

import manager.Manager;
import task.*;


public class Main {
    public static void main(String[] args) {
        Manager manager = new Manager();

        int inputGeneralMenu = Menu.generalMenu();
        while (inputGeneralMenu != 0) {
            switch (inputGeneralMenu) {
                case 1:
                    Task newTask = Menu.addTaskMenu();
                    manager.addTask(newTask);
                    System.out.println(manager.getAllTaskHashMap());
                    break;
                case 2:
                    Epic newEpic = Menu.addEpicMenu();
                    manager.addEpic(newEpic);
                    System.out.println(manager.getAllEpicHashMap());
                    break;
                case 3://РЕДАТИРОВАТЬ ЗАДАЧУ TASK
                    System.out.println(manager.getAllTaskHashMap());
                    System.out.println("Введите id задачи Task");
                    int idTask = Service.SC_int.nextInt();
                    Task currentTask = manager.getTaskById(idTask);
                    System.out.println(currentTask);
                    int input = options(currentTask);
                    switch (input) {
                        case 1:
                            Menu.editTask(currentTask);
                            manager.updateTask(currentTask);
                            break;
                        case 4:
                            currentTask.setStatus(Status.DONE);
                            break;
                        case 5:
                            manager.removeTask(currentTask);
                            break;
                    }
                    break;
                case 4: //РЕДАКТИРОВАТЬ ЗАДАЧУ EPIC
                    System.out.println(manager.getAllEpicHashMap());
                    System.out.println("Введите id задачи Task");
                    int idEpic = Service.SC_int.nextInt();
                    Epic currentEpic = manager.getEpicById(idEpic);
                    System.out.println(currentEpic);
                    int inputEpic = options(currentEpic);
                    switch (inputEpic) {
                        case 1:
                            Menu.editTask(currentEpic);
                            manager.updateEpic(currentEpic);
                            break;
                        case 2:
                            SubTask newSubTask = Menu.addSubTaskMenu(currentEpic);
                            manager.addSubTask(newSubTask);
                            break;
                        case 3://РЕДАКТИРОВАТЬ SUBTASK
                            System.out.println(currentEpic.getEpicSubTaskList());
                            System.out.println("Введите Id SubTask");
                            int idSubTask = Service.SC_int.nextInt();
                            SubTask currentSubTask = manager.getSubTaskById(idSubTask);
                            int inputSubTask = options(currentSubTask);
                            switch (inputSubTask) {
                                case 1:
                                    Menu.editTask(manager.getSubTaskById(idSubTask));
                                    break;
                                case 4:
                                    currentSubTask.setStatus(Status.DONE);
                                    manager.updateSubTask(currentSubTask);
                                    break;
                                case 5:
                                    manager.removeSubTask(currentSubTask);
                                    manager.updateSubTask(currentSubTask);
                                    break;
                                default:
                                    break;
                            }
                            break;
                        case 4:
                            currentEpic.setStatus(Status.DONE);
                            break;
                        case 5:
                            manager.removeEpic(currentEpic);
                            break;
                        default:
                            break;
                    }
                    break;
                case 5:
                    System.out.println(manager.getAllTaskHashMap());
                    System.out.println(manager.getAllEpicHashMap());
                    System.out.println(manager.getAllSubTaskHashMap());
                default:
                    break;
            }
            inputGeneralMenu = Menu.generalMenu();
        }

    }


    //--ОПЕРАЦИИ НАД ЗАДАЧЕЙ
    public static int options(AbstractTask task) {
        System.out.println("1 Редактировать задачу");
        if (task instanceof Epic) {
            System.out.println("2 Добавить Subtask");
            if (((Epic) task).getEpicSubTaskList().size() > 0) {
                System.out.println("3 Редактировать Subtask");
            }
        }
        System.out.println("4 Выполнено");
        System.out.println("5 Удаление задачи");
        return Service.SC_int.nextInt();

//        switch (input) {
//            case 1:
//                Menu.editTask(task);
//                if (task instanceof Task) {
//                    manager.updateTask((Task) task);
//                }
//                if (task instanceof Epic) {
//                    manager.updateEpic((Epic) task);
//                }
//                if (task instanceof SubTask) {
//                    manager.updateSubTask((SubTask) task);
//                }
//                break;
//            case 2:
//                if (task instanceof Epic) {
//                    SubTask newSubTask = Menu.addSubTaskMenu((Epic) task);
//                    manager.addSubTask(newSubTask);
//                }
//                break;
//            case 3:
//                task.setStatus(Status.DONE);
//                break;
//            case 4:
//                if (task instanceof Task){
//                    manager.removeTask((Task) task);
//                }
//                if (task instanceof Epic){
//                    manager.removeEpic((Epic) task);
//                }
//                if (task instanceof SubTask){
//                    manager.removeSubTask((SubTask) task);
//                }
//                break;
//            default:
//                break;
//        }
    }

}
