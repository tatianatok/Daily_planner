package AllTasks;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.stream.Collectors;
import Enum.Frequency;
import Enum.Type;
import Exceptions.IncorrectArgumentException;
import Exceptions.TaskNotFoundException;
import java.time.format.DateTimeFormatter;

public class TaskService {

    public static Map<Integer, Task> taskMap = new HashMap<>();
    private static Set<Task> setTask = new HashSet<>();

    private static Scanner scanner = new Scanner(System.in).useDelimiter("\\n");
    private static Task task;
    static LocalDate localDate;

    private static Collection<Task> getALlTaskMap() {
        Collection<Task> values = taskMap.values();
        return values;
    }

    static Collection<Task> getTaskMapForDate() {
        TreeSet<Task> taskForDate = new TreeSet<>();
        for (Task task : taskMap.values()) {
            if (task.appearsln(dateFor())) {
                taskForDate.add(task);
            }
            if (taskForDate.isEmpty()) {
                System.out.println("На сегодня заданий нет.");
            }
        }
        return taskForDate;
    }

    public static void getAllByDate(LocalDate localDate) throws TaskNotFoundException {
        for (Map.Entry<Integer, Task> taskMap : taskMap.entrySet()) {
            LocalDate date = taskMap.getValue().getDateTime().toLocalDate();
            try {
                if (localDate.equals(localDate)) {
                    System.out.println(taskMap.getKey() + " " + taskMap.getValue());
                }
            } catch (NullPointerException e) {
                System.out.println("Нет заданий на указанную дату: ");
            }
            if (localDate.isAfter(date) && taskMap.getValue().appearsln(localDate)) {
                System.out.println(taskMap.getKey() + " " + taskMap.getValue());
            }
        }
    }

    static void printMenu() {
        System.out.println();
        System.out.println("Доступные команды: " +
                "\n 1 - Добавить задание " +
                "\n 2 - Удалить задание " +
                "\n 3 - Получить задание на указанную дату " +
                "\n 4 - Показать список заданий на указанную дату " +
                "\n 5 - Показать удаленные задания " +
                "\n 6 - Изменить название задания " +
                "\n 7 - Изменить описание задания" +
                "\n 8 - Найти задание " +
                "\n 0 - Выйти из меню");
    }

    static void remove(Scanner scanner) throws IncorrectArgumentException {
        System.out.println("Введите id задания для удаления: ");
        for (Task task : getALlTaskMap()) {
        }
        while (true) {
            try {
                System.out.println("Выберите задание для удаления: ");
                String idline = scanner.nextLine();
                int id = Integer.parseInt(idline);
                TaskService.removeId(id);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Неверный id");
            } catch (TaskNotFoundException e) {
                System.out.println("Задание для удаления не найдено!");
            }
        }
    }

    private static void removeId(int id) throws TaskNotFoundException {
        if (taskMap.containsKey(id)) {
            taskMap.remove(id);
            TaskService.setTask.add(taskMap.remove(id));
        } else {
            throw new TaskNotFoundException("id не найден");
        }
    }

    static void ofDeletedTasks() {
        if (setTask.size() > 0) {
            System.out.println("В архиве количество удаленных заданий: " + setTask.size());
            for (Task task : setTask) {
                System.out.println("Список удаленных заданий: " + task);
            }
        } else {
            System.out.println("В архиве нет удаленных заданий");
        }
    }

    private static void MM(int id) {
        Task task = taskMap.get(id);
        setTask.add(task);
        taskMap.remove(id);
    }

    static void findTaskOnDate() {
        for (Map.Entry<Integer, Task> taskMap : taskMap.entrySet()) {
            System.out.println(taskMap.getKey() + " " + taskMap.getValue());
        }
    }

    static void addTask() throws IncorrectArgumentException {
        while (true) {
            try {
                int id = task.getId();
                taskMap.put(task.getId(), task);
            } catch (NullPointerException e) {
                System.out.println();
            }
            TaskService.selectType(scanner);
            TaskService.selectFrequency(scanner);
            TaskService.comeUpTitle(scanner);
            TaskService.comeUpDescription(scanner);
            TaskService.askDateTime(scanner);
            try {
                System.out.println("Задание добавлено!");
                System.out.println(task.toString());
            } catch (NullPointerException e) {
                System.out.println();
            }
        }
    }

    public void add(Task task) {
        taskMap.put(task.getId(), task);
    }

    private static void selectType(Scanner scanner) {
        try {
            System.out.print("Выберите тип задания: \n 1 - Рабочее \n 2 - Личное");
            Type type;
            int option = scanner.nextInt();
            switch (option) {
                case 1:
                    type = Type.WORK;
                    System.out.println("Выбрано рабочее задание!");
                    break;
                case 2:
                    type = Type.PERSONAL;
                    System.out.println("Выбрано личное задание!");
                    break;
                default:
                    System.out.println("Нет такого задания!");
                    break;
            }
            task.getType();
            taskMap.put(task.getId(), task);
        } catch (NullPointerException e) {
            System.out.println();
        }
    }

    private static void selectFrequency(Scanner scanner) {
        try {
            System.out.println("Выберите периодичность задания: " +
                    "\n 1 – однократное " +
                    "\n 2 - ежедневное " +
                    "\n 3 - еженедельное " +
                    "\n 4 - ежемесячое " +
                    "\n 5 - ежегодное ");
            Frequency frequency;
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    frequency = Frequency.FREQUENCY_ONCE;
                    System.out.println("Однократное задание!");
                    break;
                case 2:
                    frequency = Frequency.FREQUENCY_DAILY;
                    System.out.println("Повторение каждый день!");
                    break;
                case 3:
                    frequency = Frequency.FREQUENCY_WEEKLY;
                    System.out.println("Повторение каждую неделю!");
                    break;
                case 4:
                    frequency = Frequency.FREQUENCY_MONTHLY;
                    System.out.println("Повторение каждый месяц!");
                    break;
                case 5:
                    frequency = Frequency.FREQUENCY_ANNUALLY;
                    System.out.println("Повторение каждый год!");
                    break;
                default:
                    System.out.println("Укажите периодичность задания!");
                    break;
            }
            task.getFrequency();
            taskMap.put(task.getId(), task);
        } catch (NullPointerException e) {
            System.out.println();
        }
    }

    private static void comeUpTitle(Scanner scanner) throws IncorrectArgumentException {
        System.out.print("Добавьте название задачи! ");
        String title = scanner.next();
        if (title == null && title.isEmpty() && title.isBlank()) {
            throw new IncorrectArgumentException("Вы не ввели название: ");
        } else {
            System.out.println("Название задачи: " + title);
        }
        try {
            task.getTitle();
            taskMap.put(task.getId(), task);
        } catch (NullPointerException e) {
            System.out.println();
        }
    }

    private static void comeUpDescription(Scanner scanner) throws IncorrectArgumentException {
        System.out.print("Добавьте краткое описание задачи! ");
        String description = scanner.next();
        if (description == null && description.isEmpty() && description.isBlank()) {
            throw new IncorrectArgumentException("Вы не ввели описание задачи: ");
        } else {
            System.out.println("Введено краткое описание задачи: " + description);
        }
        try {
            task.getDescription();
            taskMap.put(task.getId(), task);
        } catch (NullPointerException e) {
            System.out.println();
        }
    }

    static void changeUpTitle() {
        System.out.print("Введите id, чтобы изменить название задачи: ");
        int id = scanner.nextInt();
        System.out.println("Введен id: " + id);
        try {
            System.out.print("Измените название: ");
            String title = scanner.next();
            taskMap.get(id).setTitle(title);
            System.out.println("Добавлено название задачи: " + id + taskMap.get(id).getTitle());
        } catch (IncorrectArgumentException e) {
            throw new RuntimeException(e);
        }
    }

    static void changeUpDescription() {
        System.out.print("Введите id, чтобы изменить описание задачи: ");
        int id = scanner.nextInt();
        System.out.println("Введен id: " + id);
        try {
            System.out.println("Измените описание задачи: ");
            String description = scanner.next();
            taskMap.get(id).setDescription(description);
            System.out.println("Добавлено описание задачи: " + id + taskMap.get(id).getDescription());
        } catch (IncorrectArgumentException e) {
            throw new RuntimeException(e);
        }
    }

    static void toFindTasks() {
        System.out.println("Найти задачу: ");
        Comparator<Map.Entry<Integer, Task>> comparator = Comparator.comparing(o -> o.getValue().getDateTime());
        taskMap = taskMap.entrySet()
                .stream()
                .sorted(comparator)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (a, b) -> {
                            throw new AssertionError();
                        },
                        LinkedHashMap::new
                ));
        taskMap.entrySet().forEach(System.out::println);
    }

    private static void askDateTime(Scanner scanner) {
        while (true) {
            try {
                System.out.println("Заполните дату в указанном формате: " + "dd.MM.yyyy HH:mm");
                Scanner scanner1 = new Scanner(System.in);
                String askDate = scanner1.nextLine();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
                LocalDateTime dateTime = LocalDateTime.parse(askDate, formatter);
                System.out.println("Введена дата:" + dateTime);
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Дата введена в неправильном формате! ");
            }
        }
    }

    private static LocalDate dateFor() {
        Scanner scanDateY = new Scanner(System.in);
        LocalDate dateForY = LocalDate.parse(scanDateY.next());
        return dateForY;
    }
}
