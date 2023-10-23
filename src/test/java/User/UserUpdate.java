package User;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

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
        return new UserUpdate("New" + RandomUtils.nextInt(1, 1000), user.getName());
    }

    public static UserUpdate changeName(User user) {
        return new UserUpdate(user.getPassword(), "New" + RandomStringUtils.randomAlphabetic(5, 15));
    }

    public static UserUpdate changeNameAndPassword(User user) {
        return new UserUpdate("New" + RandomUtils.nextInt(1, 1000), "New" + RandomStringUtils.randomAlphabetic(5, 15));
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
