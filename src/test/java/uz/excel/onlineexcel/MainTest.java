package uz.excel.onlineexcel;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.Objects;

public class MainTest {

    static class User {
        private int age = 43;
        private String userName = "stupid";
    }

    @Test
    public static void main(String[] args) {

        User user = new User();
        Field field = null;
        Field field2 = null;
        try {
            field = user.getClass().getDeclaredField("age");
            field2 = user.getClass().getDeclaredField("userName");
            System.out.println(field);
            System.out.println(field2);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        Objects.requireNonNull(field).setAccessible(true);
        Objects.requireNonNull(field2).setAccessible(true);
        int age = 0;
        String userName = "adsfalse";
        try {
            age = (int) field.get(user);
            userName = (String) field2.get(user);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        System.out.println("age = " + age);
        System.out.println("userName = " + userName);

    }

}
