package AllTasks;
import Enum.Frequency;
import Enum.Type;
import Exceptions.IncorrectArgumentException;
import Exceptions.TaskNotFoundException;
import java.util.Scanner;
import java.util.*;
import java.time.LocalDateTime;
import static AllTasks.TaskService.*;
public class Main {
    private static final TaskService planner = new TaskService();
    public static void main(String[] args) {
        Task.currentLocalDateAndTime();

        OneTimeTask oneTask1 = new OneTimeTask("прием у педиатра", "получить справку в бассейн", LocalDateTime.now(), Type.PERSONAL, Frequency.FREQUENCY_ONCE);
        OneTimeTask oneTask2 = new OneTimeTask("купить цветы", "в подарок учителю", LocalDateTime.now(), Type.PERSONAL, Frequency.FREQUENCY_ONCE);
        DailyTask dailyTask1 = new DailyTask("проверить почту", "ежедневная рассылка для персонала", LocalDateTime.now(), Type.WORK, Frequency.FREQUENCY_DAILY);
        WeeklyTask weeklyTask1 = new WeeklyTask("подготовить отчет", "сведения о сотрудниках в отпусках и на больничных", LocalDateTime.now(), Type.WORK, Frequency.FREQUENCY_WEEKLY);
        WeeklyTask weeklyTask2 =new WeeklyTask("сделать уборку", "разложить вещи по местам, протереть пыль, вымыть пол", LocalDateTime.now(), Type.PERSONAL, Frequency.FREQUENCY_WEEKLY);
        MonthlyTask monthlyTask1 = new MonthlyTask("сдать табель рабочего времени", "учет рабочего времени сменного персонала", LocalDateTime.now(), Type.WORK, Frequency.FREQUENCY_MONTHLY);
        MonthlyTask monthlyTask2 = new MonthlyTask("сходить в кино", "выбрать интересный фильм", LocalDateTime.now(), Type.PERSONAL, Frequency.FREQUENCY_MONTHLY);
        YearlyTask yearlyTask1 = new YearlyTask("день рождения у А", "не забыть поздравить", LocalDateTime.now(), Type.PERSONAL, Frequency.FREQUENCY_ANNUALLY);

        System.out.println("Добавлены задачи: ");
        TaskService.taskMap.put(oneTask1.getId(), oneTask1);
        TaskService.taskMap.put(oneTask2.getId(), oneTask2);
        TaskService.taskMap.put(dailyTask1.getId(), dailyTask1);
        TaskService.taskMap.put(weeklyTask1.getId(), weeklyTask1);
        TaskService.taskMap.put(weeklyTask2.getId(), weeklyTask2);
        TaskService.taskMap.put(monthlyTask1.getId(), monthlyTask1);
        TaskService.taskMap.put(monthlyTask2.getId(), monthlyTask2);
        TaskService.taskMap.put(yearlyTask1.getId(), yearlyTask1);
        TaskService.findTaskOnDate();
        System.out.println();

        TaskService.taskMap.remove(oneTask2.getId(), oneTask2);
        TaskService.taskMap.remove(weeklyTask2.getId(), weeklyTask2);
        TaskService.taskMap.remove(monthlyTask2.getId(), monthlyTask2);
        System.out.println("Остались задачи: ");
        TaskService.findTaskOnDate();
        System.out.println();

        Set<Integer> keys = TaskService.taskMap.keySet();
        System.out.println("Ключи: " + keys );

        System.out.println();
        try (Scanner scanner = new Scanner(System.in)) {
            label:
            while (true) {
                System.out.println("Пожалйста, выберите команду из пунта меню: ");
                printMenu();
                if (scanner.hasNext()) {
                    int menu = scanner.nextInt();
                    switch (menu) {
                        case 1:
                            addTask();
                            break;
                        case 2:
                            remove(scanner);
                            break;
                        case 3:
                            getAllByDate(localDate);
                            break;
                        case 4:
                            getTaskMapForDate();
                            break;
                        case 5:
                            ofDeletedTasks();
                            break;
                        case 6:
                            changeUpTitle();
                            break;
                        case 7:
                            changeUpDescription();
                            break;
                        case 8:
                            toFindTasks();
                            break;
                        case 0:
                            break label;
                    }
                } else {
                    scanner.next();
                    System.out.println("Выберите команды меню: ");
                }
            }
            System.out.println("Итоговый список задач");

        }
        catch (IncorrectArgumentException e) {
            throw new RuntimeException(e);
        } catch (NullPointerException e) {
            throw new RuntimeException(e);
        }
        catch (TaskNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}