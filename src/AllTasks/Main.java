package AllTasks;

import Enum.Frequency;
import Enum.Type;
import Exceptions.IncorrectArgumentException;

import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public TaskService taskService = new TaskService();

        public void main(String[] args) {

            Task.currentLocalDateAndTime();

            OneTimeTask oneTask1 = new OneTimeTask("прием у педиатра", "получить справку в бассейн", LocalDateTime.now(), Type.PERSONAL, Frequency.FREQUENCY_ONCE);
            OneTimeTask oneTask2 = new OneTimeTask("купить цветы", "в подарок учителю", LocalDateTime.now(), Type.PERSONAL, Frequency.FREQUENCY_ONCE);
            DailyTask dailyTask1 = new DailyTask("проверить почту", "ежедневная рассылка для персонала", LocalDateTime.now(), Type.WORK, Frequency.FREQUENCY_DAILY);
            WeeklyTask weeklyTask1 = new WeeklyTask("подготовить отчет", "сведения о сотрудниках в отпусках и на больничных", LocalDateTime.now(), Type.WORK, Frequency.FREQUENCY_WEEKLY);
            WeeklyTask weeklyTask2 = new WeeklyTask("сделать уборку", "разложить вещи по местам, протереть пыль, вымыть пол", LocalDateTime.now(), Type.PERSONAL, Frequency.FREQUENCY_WEEKLY);
            MonthlyTask monthlyTask1 = new MonthlyTask("сдать табель рабочего времени", "учет рабочего времени сменного персонала", LocalDateTime.now(), Type.WORK, Frequency.FREQUENCY_MONTHLY);
            MonthlyTask monthlyTask2 = new MonthlyTask("сходить в кино", "выбрать интересный фильм", LocalDateTime.now(), Type.PERSONAL, Frequency.FREQUENCY_MONTHLY);
            YearlyTask yearlyTask1 = new YearlyTask("день рождения у А", "не забыть поздравить", LocalDateTime.now(), Type.PERSONAL, Frequency.FREQUENCY_ANNUALLY);

            System.out.println("Добавлены задачи: ");
            taskService.addTask1(oneTask2);
            taskService.addTask1(monthlyTask1);

            taskService.findTaskOnDate();
            System.out.println();

            System.out.println("Остались задачи: ");
            taskService.findTaskOnDate();
            System.out.println();

        Set<Integer> keys = taskService.keySet();
        System.out.println("Ключи: " + keys );

        System.out.println();
        try (Scanner scanner = new Scanner(System.in)) {
            label:
            while (true) {
                System.out.println("Пожалйста, выберите команду из пунта меню: ");
                taskService.printMenu();
                if (scanner.hasNext()) {
                    int menu = scanner.nextInt();
                    switch (menu) {
                        case 1:
                            taskService.addTask();
                            break;
                        case 2:
                            taskService.remove(scanner);
                            break;
                        case 3:
                            taskService.getAllByDate(localDate);
                            break;
                        case 4:
                            taskService.getTaskMapForDate();
                            break;
                        case 5:
                            taskService.ofDeletedTasks();
                            break;
                        case 6:
                            taskService.changeUpTitle();
                            break;
                        case 7:
                            taskService.changeUpDescription();
                            break;
                        case 8:
                            taskService.toFindTasks();
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

        } catch (IncorrectArgumentException e) {
            throw new RuntimeException(e);
        } catch (NullPointerException e) {
            throw new RuntimeException(e);
        } catch (TaskNotFoundException e) {
           throw new RuntimeException(e);
        }
    }
}