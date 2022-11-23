package com.example.demo.Validator;

import java.util.regex.Pattern;

public class Validator {
    private final String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

    public boolean patternMatches(String emailAddress) {
        return Pattern.compile(this.regexPattern)
                .matcher(emailAddress)
                .matches();
    }
}
