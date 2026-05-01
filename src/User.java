import java.time.LocalDateTime;
import java.util.Objects;

public class User {
    private int id;
    private String name;
    private String password;
    private LocalDateTime lastLoginDate;
    private boolean isLoggedIn;
    private static int counter = 0;

    public User(String name, String password) {
        this.id = counter;
        this.name = name;
        this.password = password;
        this.isLoggedIn = false;
        counter++;
    }


    public int getId() { return id; }
    public String getName() { return name; }
    public String getPassword() { return password; }
    public void setLoggedIn(boolean loggedIn) {
        this.isLoggedIn = loggedIn;
    }
    public void setLastLoginDate(LocalDateTime lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "ID: " + id + " | Логін: " + name + " | Онлайн: " + isLoggedIn;
    }
}