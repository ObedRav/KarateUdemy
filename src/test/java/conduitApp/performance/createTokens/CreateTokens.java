package conduitApp.performance.createTokens;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import com.intuit.karate.Runner;

/**
 * The CreateTokens class creates access tokens for a list of emails using a feature file called
 * "CreateToken.feature".
 */
public class CreateTokens {

    private static final ArrayList<String> tokens = new ArrayList<String>();
    private static final AtomicInteger counter = new AtomicInteger();

    private static String[] emails = {
        "kardemo1@test.com",
        "kardemo2@test.com",
        "kardemo3@test.com"
    };

    /**
     * The function getNextToken returns the next token from a list of tokens in a circular manner.
     * 
     * @return The method is returning the next token from a list of tokens.
     */
    public static String getNextToken() {
        return tokens.get(counter.getAndIncrement() % tokens.size());
    }

    /**
     * The function creates access tokens for a list of emails using a feature file called
     * "CreateToken.feature".
     */
    public static void createAccessTokens() {
        for (String email : emails) {
            Map<String, Object> account = new HashMap<String, Object>();

            account.put("userEmail", email);
            account.put("userPassword", "Welcome1");

            Map<String, Object> result = Runner.runFeature("classpath:helpers/CreateToken.feature", account, true);

            tokens.add(result.get("authToken").toString());
        }
    }


}
