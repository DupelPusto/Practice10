import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        UserRegistry registry = new UserRegistry();
        Scanner s = new Scanner(System.in);
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
                    case 0:
                        System.out.println("Роботу завершено.");
                        break;
                    default:
                        System.out.println("Невірний вибір. Спробуйте ще раз.");
                }
            } catch (NumberFormatException e){
                System.out.println("Помилка при введенні числового значення. Спробуйте ще раз!");
            }

        }
        s.close();
    }
}