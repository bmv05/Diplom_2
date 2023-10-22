package User;

public class UserCredentials {
    private String email;
    private String password;

    public UserCredentials(String login, String password) {
        this.email = login;
        this.password = password;
    }


    public static UserCredentials from(User user) {
        return new UserCredentials(user.getEmail(), user.getPassword());
    }
    public static UserCredentials wrongPassword(User user) {
        return new UserCredentials(user.getEmail(), "Password");
    }
    public static UserCredentials wrongEmail(User user) {
        return new UserCredentials("Email", user.getPassword());
    }
    public static UserCredentials wrongEmailAndPassword(User user) {
        return new UserCredentials("Email", "Password");
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
