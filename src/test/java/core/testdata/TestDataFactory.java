package core.testdata;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TestDataFactory {

    private static final Faker faker =
            new Faker();

    public static String randomUsername() {

        return "user_" + faker.number().digits(6);
    }

    public static String randomEmail() {

        return randomUsername() + "@test.com";
    }

    public static String randomFirstName() {

        return faker.name().firstName();
    }

    public static String randomLastName() {

        return faker.name().lastName();
    }

    public static String randomPhone() {

        return "06" + faker.number().digits(8);
    }

    public static String randomPassword() {

        return "Test@" + faker.number().digits(5);
    }

    public static String todayDate() {

        return LocalDate.now()
                .format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public static String futureDate(int days) {

        return LocalDate.now()
                .plusDays(days)
                .format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
}