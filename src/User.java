import java.time.LocalDateTime;
import java.util.Objects;

public class User {
    private UserIdentifier identifier;
    private String password;
    private LocalDateTime lastLoginDate;
    private boolean isLoggedIn;
    private static int counter = 0;

    public User(String name, String password) {
        this.identifier = new UserIdentifier(counter++, name);
        this.password = password;
        this.isLoggedIn = false;
    }

    public int getId() { return identifier.getId(); }
    public String getName() { return identifier.getName(); }
    public UserIdentifier getIdentifier() { return identifier; }
    public String getPassword() { return password; }
    public boolean isLoggedIn() { return isLoggedIn; }
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
        return Objects.equals(identifier, user.identifier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identifier);
    }

    @Override
    public String toString() {
        return identifier.toString() + " | Онлайн: " + isLoggedIn;
    }
}