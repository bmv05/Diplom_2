package User;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

public class UserGenerator {
    public static User randomUser(){
      return new User(
              RandomStringUtils.randomAlphabetic(5, 10).toLowerCase() + "@stellar.ru",
              "Pas" + RandomUtils.nextInt(1, 1000),
              "Name1");
    }
    public static User randomUserWithoutLogin(){
      return new User(
              "",
              "Pas" + RandomUtils.nextInt(1, 1000),
              "Name1");
    }

    public static User randomUserWithoutPassword(){
        return new User(
                RandomStringUtils.randomAlphabetic(5, 10).toLowerCase() + "@stellar.ru",
                "",
                "Name1");
    }
    public static User randomUserWithoutName(){
        return new User(
                RandomStringUtils.randomAlphabetic(5, 10).toLowerCase() + "@stellar.ru",
                "Pas" + RandomUtils.nextInt(1, 1000),
                "");
    }
}
