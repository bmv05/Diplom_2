package User;

public class UserUpdate {
    private String password;
    private String name;

    public UserUpdate(String password, String name) {
        this.password = password;
        this.name = name;
    }

    public UserUpdate() {
    }

    public static UserUpdate changePassword(User user) {
        return new UserUpdate("Новый пароль", user.getName());
    }

    public static UserUpdate changeName(User user) {
        return new UserUpdate(user.getPassword(), "Новое имя");
    }

    public static UserUpdate changeNameAndPassword(User user) {
        return new UserUpdate("Новый пароль", "Новое имя");
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
