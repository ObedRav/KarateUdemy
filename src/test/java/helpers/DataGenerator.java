package helpers;

import com.github.javafaker.Faker;

import net.minidev.json.JSONObject;

/**
 * The DataGenerator class provides methods to generate random email addresses, usernames, and article
 * values using the Faker library in Java.
 */
public class DataGenerator {

    /**
     * The getRandomEmail function generates a random email address using a combination of a random
     * first name, a random three-digit number, and the domain "@test.com".
     * 
     * @return The method is returning a randomly generated email address in the format of "firstName +
     * randomNumber + @test.com".
     */
    public static String getRandomEmail() {
        Faker faker = new Faker();
        String email = faker.name().firstName() + faker.number().randomNumber(3, true) + "@test.com";

        return email;
    }

    /**
     * The getRandomUsername function generates a random username using the Faker library in Java.
     * 
     * @return The method is returning a randomly generated username.
     */
    public static String getRandomUsername() {
        Faker faker = new Faker();
        String username = faker.name().username() + faker.number().randomNumber(3, true);

        return username;
    }

    /**
     * The function getRandomArticleValues generates random values for a title, description, and body
     * of an article and returns them as a JSONObject.
     * 
     * @return The method is returning a JSONObject that contains randomly generated values for the
     * title, description, and body of an article.
     */
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
