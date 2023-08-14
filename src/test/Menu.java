package test;

import task.*;

public class Menu {

    private Menu() {
    }

    ;

    public static int generalMenu() {
        System.out.println("1 - Создать задачу Task");
        System.out.println("2 - Создать задачу Epic");
        System.out.println("3 - Редактировать задачу Task");
        System.out.println("4 - Редактировать задачу Epic");
        System.out.println("5 - Показать все задачи");
        return Service.SC_int.nextInt();
    }

    public static Task addTaskMenu() {
        System.out.println("Создать Task");
        System.out.println("Введите название Task :");
        String nameTask = Service.SC_line.nextLine();
        System.out.println("Введите описание Task :");
        String descriptionTask = Service.SC_line.nextLine();
        return new Task(nameTask, Status.NEW, descriptionTask);
    }

    public static Epic addEpicMenu() {
        System.out.println("Создать Epic");
        System.out.println("Введите название Epic :");
        String nameEpic = Service.SC_line.nextLine();
        System.out.println("Введите описание Epic :");
        String descriptionTask = Service.SC_line.nextLine();
        return new Epic(nameEpic, Status.NEW, descriptionTask);
    }

    public static SubTask addSubTaskMenu(Epic epic) {
        System.out.println("Введите название SubTask :");
        String nameSubTask = Service.SC_line.nextLine();
        System.out.println("Введите описание SubTask :");
        String descriptionTask = Service.SC_line.nextLine();
        SubTask newSubTask = new SubTask(epic, nameSubTask, Status.NEW, descriptionTask);
        epic.addSubTaskInEpic(newSubTask);
        return newSubTask;
    }

    public static AbstractTask editTask(AbstractTask task) {
        System.out.println("Редактировать задачу");
        System.out.println("Введите новое название :");
        String newName = Service.SC_line.nextLine();
        System.out.println("Введите новое описание :");
        String newDescription = Service.SC_line.nextLine();
        task.setName(newName);
        task.setDescription(newDescription);
        return task;
    }


}
