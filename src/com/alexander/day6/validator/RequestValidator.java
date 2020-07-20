package com.alexander.day6.validator;

import java.util.Map;
import java.util.Optional;

public class RequestValidator {
    public boolean addRequestValidation(Optional<Map<String, String>> parameters) {
        if (parameters == null || parameters.isEmpty()) {
            return false;
        }
        Map<String, String> requestParameters = parameters.get();
        return requestParameters.containsKey("title") && requestParameters.containsKey("pages") &&
                requestParameters.containsKey("publicationYear") && requestParameters.containsKey("authors");
    }

    public boolean removeRequestValidation(Optional<Map<String, String>> parameters) {
        if (parameters == null || parameters.isEmpty()) {
            return false;
        }
        return parameters.get().containsKey("id");
    }

    public boolean sortRequestValidation(Optional<Map<String, String>> parameters) {
        if (parameters == null) {
            return false;
        }
        return parameters.isEmpty();
    }

    public boolean findRequestValidation(Optional<Map<String, String>> parameters) {
        if (parameters == null || parameters.isEmpty()) {
            return false;
        }
        return parameters.get().containsKey("tagValue");
    }
}
