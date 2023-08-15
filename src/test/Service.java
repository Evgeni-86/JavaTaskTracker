package test;

import task.*;

import java.util.Scanner;

public class Service {
    public static Scanner SC_int = new Scanner(System.in);
    public static Scanner SC_line = new Scanner(System.in);


    public static int generalMenu() {
        System.out.println("1 - Создать задачу Task");
        System.out.println("2 - Создать задачу Epic");
        System.out.println("3 - Редактировать задачу Task");
        System.out.println("4 - Редактировать задачу Epic");
        System.out.println("5 - Показать все задачи");
        return Service.SC_int.nextInt();
    }

    public static <T> T createAnyTask(Class<T> clazz, Epic epic) {
        System.out.println("Создать " + clazz.getName());
        System.out.println("Введите название " + clazz.getName() + " :");
        String name = Service.SC_line.nextLine();
        System.out.println("Введите описание " + clazz.getName() + " :");
        String description = Service.SC_line.nextLine();

        if (clazz == Task.class) {
            System.out.println(Task.class);
            return clazz.cast(new Task(name, Status.NEW, description));
        }
        if (clazz == Epic.class) {
            return clazz.cast(new Epic(name, Status.NEW, description));
        }
        if (clazz == SubTask.class) {
            SubTask newSubTask = new SubTask(epic, name, Status.NEW, description);
            epic.getEpicSubTaskList().add(newSubTask);
            return clazz.cast(newSubTask);
        }
        return null;
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

    public static int options(AbstractTask currentTask) {
        System.out.println("1 Редактировать задачу");
        if (currentTask instanceof Epic) {
            System.out.println("11 Добавить Subtask");
            if (((Epic) currentTask).getEpicSubTaskList().size() > 0) {
                System.out.println("12 Редактировать Subtask");
            }
        }
        System.out.println("2 Выполнено");
        System.out.println("3 Удаление задачи");
        return Service.SC_int.nextInt();
    }

}
