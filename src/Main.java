import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        UserRegistry registry = new UserRegistry();
        Scanner s = new Scanner(System.in);

        System.out.print("Бажаєте відновити базу користувачів з файлу? (1 - Так, 0 - Ні): ");
        String loadChoice = s.nextLine();
        if ("1".equals(loadChoice)) {
            System.out.print("Введіть шлях до файлу (наприклад, users.dat): ");
            String path = s.nextLine();
            registry.loadFromFile(path);
        }

        int choice = -1;

        while (choice != 0) {
            System.out.println("\nМЕНЮ");
            System.out.println("1. Зареєструвати користувача");
            System.out.println("2. Увійти в систему");
            System.out.println("3. Вийти з системи");
            System.out.println("4. Перевірити наявність користувача (за логіном)");
            System.out.println("5. Видалити користувача (за ID)");
            System.out.println("6. Показати загальну кількість унікальних користувачів");
            System.out.println("7. Вивести список усіх користувачів");
            System.out.println("8. Вивести відсортований список (за логіном)");
            System.out.println("9. Вивести відфільтрований список (тільки онлайн)");
            System.out.println("0. Вихід");
            System.out.print("Оберіть дію: ");

            try {
                choice = Integer.parseInt(s.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Будь ласка, введіть число.");
                continue;
            }

            try {
                switch (choice) {
                    case 1:
                        System.out.print("Введіть логін: ");
                        String regLogin = s.nextLine();
                        System.out.print("Введіть пароль: ");
                        String regPass = s.nextLine();
                        registry.registerUser(regLogin, regPass);
                        break;
                    case 2:
                        System.out.print("Введіть логін: ");
                        String login = s.nextLine();
                        System.out.print("Введіть пароль: ");
                        String pass = s.nextLine();
                        registry.loginUser(login, pass);
                        break;
                    case 3:
                        System.out.print("Введіть логін користувача: ");
                        String nameToLogout = s.nextLine();
                        registry.logoutUser(nameToLogout);
                        break;
                    case 4:
                        System.out.print("Введіть логін для пошуку: ");
                        String searchLogin = s.nextLine();
                        boolean exists = registry.isUserRegistered(searchLogin);
                        System.out.println("Користувач зареєстрований: " + exists);
                        break;
                    case 5:
                        System.out.print("Введіть ID для видалення: ");
                        int idToRemove = Integer.parseInt(s.nextLine());
                        registry.removeUser(idToRemove);
                        break;
                    case 6:
                        registry.printTotalUniqueUsers();
                        break;
                    case 7:
                        registry.displayAllUsers();
                        break;
                    case 8:
                        System.out.println("Сортування за логіном:");
                        LinkedList<User> sortedList = registry.getInOrder((u1, u2) -> u1.getName().compareTo(u2.getName()));
                        for (User u : sortedList) System.out.println(u);
                        break;
                    case 9:
                        System.out.println("Список користувачів онлайн:");
                        LinkedList<User> onlineUsers = registry.getFiltered(u -> u.isLoggedIn());
                        if (onlineUsers.isEmpty()) {
                            System.out.println("Зараз нікого немає онлайн.");
                        } else {
                            for (User u : onlineUsers) System.out.println(u);
                        }
                        break;
                    case 0:

                        System.out.print("Бажаєте зберегти базу користувачів у файл перед виходом? (1 - Так, 0 - Ні): ");
                        String saveChoice = s.nextLine();
                        if ("1".equals(saveChoice)) {
                            System.out.print("Введіть шлях до файлу (наприклад, users.dat): ");
                            String path = s.nextLine();
                            registry.saveToFile(path);
                        }
                        System.out.println("Роботу завершено.");
                        break;
                    default:
                        System.out.println("Невірний вибір. Спробуйте ще раз.");
                }
            } catch (Exception e) {
                System.out.println("Сталася помилка. Спробуйте ще раз!");
            }
        }
        s.close();
    }
}