import java.io.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.function.Predicate;
import java.util.Comparator;

public class UserRegistry {
    private Map<UserIdentifier, User> users;

    public UserRegistry() {
        this.users = new HashMap<>();
    }

    public void saveToFile(String filePath) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(users);
            System.out.println("Базу користувачів успішно збережено у файл: " + filePath);
        } catch (IOException e) {
            System.out.println("Помилка при збереженні у файл: " + e.getMessage());
        }
    }


    public void loadFromFile(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("Файл не знайдено. Створено порожню базу.");
            return;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            users = (Map<UserIdentifier, User>) ois.readObject();
            System.out.println("Базу користувачів успішно відновлено з файлу: " + filePath);


            int maxId = -1;
            for (UserIdentifier ui : users.keySet()) {
                if (ui.getId() > maxId) {
                    maxId = ui.getId();
                }
            }
            User.setCounter(maxId + 1);

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Помилка при читанні з файлу: " + e.getMessage());
        }
    }

    public void registerUser(String login, String password) {
        if (isUserRegistered(login)) {
            System.out.println("Користувач [" + login + "] вже є у списку!");
            return;
        }

        User newUser = new User(login, password);
        users.put(newUser.getIdentifier(), newUser);
        System.out.println("Користувача [" + login + "] успішно зареєстровано!");
    }

    public void loginUser(String login, String password) {
        for (User user : users.values()) {
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
        for (User user : users.values()) {
            if (user.getName().equals(username)) {
                user.setLoggedIn(false);
                System.out.println("Користувач " + username + " вийшов з системи!");
                return;
            }
        }
        System.out.println("Користувача з таким логіном не знайдено!");
    }

    public boolean isUserRegistered(String login) {
        for (User user : users.values()) {
            if (user.getName().equals(login)) {
                return true;
            }
        }
        return false;
    }

    public void removeUser(int id) {
        UserIdentifier keyToRemove = null;
        for (UserIdentifier key : users.keySet()) {
            if (key.getId() == id) {
                keyToRemove = key;
                break;
            }
        }

        if (keyToRemove != null) {
            users.remove(keyToRemove);
            System.out.println("Користувача з ID " + id + " видалено!");
        } else {
            System.out.println("Користувача з таким ID не знайдено!");
        }
    }

    public void printTotalUniqueUsers() {
        System.out.println("Загальна кількість унікальних користувачів: " + users.size());
    }

    public LinkedList<User> getUserList() {
        return new LinkedList<>(users.values());
    }

    public void displayAllUsers() {
        LinkedList<User> list = getUserList();
        if (list.isEmpty()) {
            System.out.println("Список користувачів порожній!");
            return;
        }
        System.out.println("Список користувачів");
        for (User user : list) {
            System.out.println(user.toString());
        }
    }

    public LinkedList<User> getInOrder(Comparator<User> comparator) {
        LinkedList<User> list = getUserList();
        list.sort(comparator);
        return list;
    }

    public LinkedList<User> getFiltered(Predicate<User> predicate) {
        LinkedList<User> filteredList = new LinkedList<>();
        for (User user : users.values()) {
            if (predicate.test(user)) {
                filteredList.add(user);
            }
        }
        return filteredList;
    }
}