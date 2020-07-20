package test.alexande.day6.validator;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import com.alexander.day6.validator.RequestValidator;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class RequestValidatorTest {
    RequestValidator validator;

    @BeforeTest
    public void setUp() {
        validator = new RequestValidator();
    }

    @Test
    public void addRequestValidationPositiveTest() {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("title", "Философия Java");
        parameters.put("pages", "1168");
        parameters.put("publicationYear", "1998");
        parameters.put("authors", "Брюс Эккель");
        Optional<Map<String, String>> requestParameters = Optional.of(parameters);
        boolean condition = validator.addRequestValidation(requestParameters);
        assertTrue(condition);
    }

    @Test
    public void addRequestValidationNegativeTest() {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("title", "Философия Java");
        parameters.put("publicationYear", "1998");
        parameters.put("authors", "Брюс Эккель");
        Optional<Map<String, String>> requestParameters = Optional.of(parameters);
        boolean condition = validator.addRequestValidation(requestParameters);
        assertFalse(condition);
    }

    @Test
    public void removeRequestValidationPositiveTest() {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("id", "2");
        Optional<Map<String, String>> requestParameters = Optional.of(parameters);
        boolean condition = validator.removeRequestValidation(requestParameters);
        assertTrue(condition);
    }

    @Test
    public void removeRequestValidationNegativeTest() {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("id12", "2");
        Optional<Map<String, String>> requestParameters = Optional.of(parameters);
        boolean condition = validator.removeRequestValidation(requestParameters);
        assertFalse(condition);
    }

    @Test
    public void findRequestValidationPositiveTest() {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("tagValue", "347");
        Optional<Map<String, String>> requestParameters = Optional.of(parameters);
        boolean condition = validator.findRequestValidation(requestParameters);
        assertTrue(condition);
    }

    @Test
    public void findRequestValidationNegativeTest() {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("tag", "347");
        Optional<Map<String, String>> requestParameters = Optional.of(parameters);
        boolean condition = validator.findRequestValidation(requestParameters);
        assertFalse(condition);
    }

    @Test
    public void sortRequestValidationPositiveTest() {
        Optional<Map<String, String>> requestParameters = Optional.empty();
        boolean condition = validator.sortRequestValidation(requestParameters);
        assertTrue(condition);
    }

    @Test
    public void sortRequestValidationNegativeTest() {
        boolean condition = validator.sortRequestValidation(null);
        assertFalse(condition);
    }
}
