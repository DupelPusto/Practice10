import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class UserRegistry {
    private Set<User> users;


    public UserRegistry() {
        this.users = new HashSet<>();
    }

    public void registerUser(String login, String password) {
        User newUser = new User(login, password);
        if (!users.add(newUser)) {
            System.out.println("Користувач [" + login + "] вже є у списку!");
        } else {
            System.out.println("Користувача [" + login + "] успішно зареєстровано!");
        }
    }

    public void loginUser(String login, String password) {
        for (User user : users) {
            if (user.getName().equals(login) && user.getPassword().equals(password)) {
                user.setLoggedIn(true);
                user.setLastLoginDate(LocalDateTime.now());
                System.out.println("Вхід виконано успішно. Вітаємо, " + login + "!");
                return;
            }
        }
        System.out.println("Помилка при аутентифікації користувача!");
    }

    public void logoutUser(String username) {
        for (User user : users) {
            if (user.getName().equals(username)) {
                user.setLoggedIn(false);
                System.out.println("Користувач " + username + " вийшов з системи!");
                return;
            }
        }
        System.out.println("Користувача з таким логіном не знайдено!");
    }

    public boolean isUserRegistered(String login) {
        for (User user : users) {
            if (user.getName().equals(login)) {
                return true;
            }
        }
        return false;
    }

    public void removeUser(int id) {
        Iterator<User> iterator = users.iterator();
        while (iterator.hasNext()) {
            User user = iterator.next();
            if (user.getId() == id) {
                iterator.remove();
                System.out.println("Користувача з ID " + id + " видалено!");
                return;
            }
        }
        System.out.println("Користувача з таким ID не знайдено!");
    }

    public void printTotalUniqueUsers() {
        System.out.println("Загальна кількість унікальних користувачів: " + users.size());
    }


    public void displayAllUsers() {
        if (users.isEmpty()) {
            System.out.println("Список користувачів порожній!");
            return;
        }
        System.out.println("--- Список користувачів");
        for (User user : users) {
            System.out.println(user.toString());
        }
    }
}