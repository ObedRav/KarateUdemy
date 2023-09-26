package helpers;

import com.github.javafaker.Faker;

import net.minidev.json.JSONObject;

public class DataGenerator {

    public static String getRandomEmail() {
        Faker faker = new Faker();
        String email = faker.name().firstName() + faker.number().randomNumber(3, true) + "@test.com";

        return email;
    }

    public static String getRandomUsername() {
        Faker faker = new Faker();
        String username = faker.name().username() + faker.number().randomNumber(3, true);

        return username;
    }

    public static JSONObject getRandomArticleValues() {
        Faker faker = new Faker();
        String titleString = faker.name().title();
        String description = faker.lorem().paragraph(1);
        String body = faker.lorem().paragraph(3);

        JSONObject json = new JSONObject();
        json.put("title", titleString);
        json.put("description", description);
        json.put("body", body);

        return json;
    }

}
